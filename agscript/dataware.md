# 说明

<h3 style="color :pink ">
由于我们是定时爬虫，数据来源于：一次性爬取的历史数据和定时爬虫爬取的更新数据

所以我们的一些关于时间的字段设计略显繁杂

这里我们有三个字段都表示时间

crawltime : 这个是我们爬虫抓取网页字段时的系统时间；

productdate : 是网页上的表格时间；

data : 这个是分区时间

三者含义不同

比如: 网页上的时间是有一条记录时间为2020-03-01,我们在2020-03-10抓取到的,在2020-03-11存入hdfs中

那么productdate='2020-03-01';
crawltime = '2020-03-10';
data = '2020-03-11' 

所以后面的一些hive中的内外表字段我们可能一会是data,一会是productdate,一会是data
都是为了我们业务的合理性考虑的
<h3>

# ods层设计
```
create database ods;

use ods;
```

```
create external table ods_sourcedata(
crawltime string,
provincename string,
marketname string,
type string,
variety string,
minprice double,
maxprice double,
avgprice double,
productdate string
)
partitioned by(data string)
row format delimited fields terminated by '\t'
stored as textfile
location '/vegnet/hdfs_filegroups_source/';
```

历史数据分区：

```
alter table ods_sourcedata add partition(data = '2020-03-28');
alter table ods_sourcedata add partition(data = '2020-03-29');
```

定时shell: 

路径 ：/home/hadoop/hive3/ods

新建： vi add_aprtition.sh

内容：

```
#!/bin/bash
source /home/hadoop/.bash_profile
TODAY_STR=$(date '+%Y-%m-%d')
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use ods;
alter table ods_sourcedata add partition(data='$TODAY_STR');
EOF
```

# dw层设计

```
create database dw;

use dw;
```

## 1. 从ods层导入数据

```
create table dw_sourcedata like ods.ods_sourcedata;
```

导入历史数据: 
```
insert into dw_sourcedata
select * from ods.ods_sourcedata;
```

定时shell:

路径 : /home/hadoop/hive3/dw

新建 : vi insert_dw_sorcedata_update_everyday.sh

内容 :

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_sourcedata
select * from ods.ods_sourcedata where data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
```

## 2. 去掉任一字段为空的值的记录

```
create table dw_sourcedata_without_null like dw_sourcedata;
```

历史数据导入：

```
insert into dw_sourcedata_without_null 
select crawltime,provincename,marketname,type,variety,minprice,maxprice,avgprice,productdate,data from dw_sourcedata where 
crawltime is not null and
provincename is not null and
marketname is not null and
type is not null and
variety is not null and
minprice is not null and
maxprice is not null and
avgprice is not null and
productdate  is not null;

```

定时shell:

路径 : /home/hadoop/hive3/dw

新建 : vi insert_dw_sourcedata_without_null_update_everyday.sh

内容 :
```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_sourcedata_without_null 
select crawltime,provincename,marketname,type,variety,minprice,maxprice,avgprice,productdate,data from dw_sourcedata where 
crawltime is not null and
provincename is not null and
marketname is not null and
type is not null and
variety is not null and
minprice is not null and
maxprice is not null and
avgprice is not null and
productdate  is not null
and data =  from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
```

## 3. 去掉 省份名 == “其他”的记录

```
create table dw_sourcedata_clear_province like dw_sourcedata;
```

导入历史数据:
```
insert into dw_sourcedata_clear_province
select crawltime,provincename,marketname,type,variety,minprice,maxprice,avgprice,productdate,data 
from dw_sourcedata_without_null 
where provincename != "其他";
```

定时shell:

路径 : /home/hadoop/hive3/dw

新建 : vi insert_dw_sourcedata_clear_province_update_everyday.sh

内容 :

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_sourcedata_clear_province
select crawltime,provincename,marketname,type,variety,minprice,maxprice,avgprice,productdate,data 
from dw_sourcedata_without_null 
where provincename != "其他" and 
data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
```

## 4. 价格清洗
去掉价格< = 0的记录
去掉 minprice > maxprice || avgprice > maxprice || minprice >avgprice

```
create table dw_sourcedata_clear_price like dw_sourcedata;
```

导入历史数据 ：

```
insert into dw_sourcedata_clear_price
select crawltime,provincename,marketname,type,variety,minprice,maxprice,avgprice,productdate,data
from dw_sourcedata_clear_province  where 
avgprice>0 and minprice>0 and maxprice>0
and avgprice <= maxprice and minprice <= avgprice and minprice <= maxprice;

```


定时shell : 

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_sourcedata_clear_price_update_everyday.sh

内容 ：
```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_sourcedata_clear_price
select crawltime,provincename,marketname,type,variety,minprice,maxprice,avgprice,productdate,data
from dw_sourcedata_clear_province  where 
avgprice>0 and minprice>0 and maxprice>0
and avgprice <= maxprice and minprice <= avgprice and minprice <= maxprice and
data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
```


# 5 主题表

<b>以 dw_sourcedata_clear_price 为事实表,生成我们业务需要得系列主题表,
实时监控主题表除外</b>

## 5.1 联动下拉框主题表
功能目的： 为了加快联动下拉框的速度,清洗一个（省份-市场-品类-品种主题表）

```
use dw;
drop table dw_t_pmtype_variety;
create table dw_t_pmtype_variety (
provincename string,
marketname string,
type string,
variety string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;
```

//定时shell

shell定时：

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_t_pmtype_variety_update_everyday.sh

内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_pmtype_variety
select distinct provincename,marketname,type,variety
from ods.ods_sourcedata;
EOF
```

## 5.2 实时监控主题表

//为了得到此表的数据,我们为了方便直接全表在ods层上扫描
stage-1：

//建立实时监控表，其中id 即为mysql 表中的PK

```
create table dw_t_real_time_query (
id string,
marketsum int,
typesum int,
varietysum int,
countsum bigint,
dailysum int,
crawltime string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;
```

stage-2：

// 往 dw_t_real_time_query中插值
这个没有历史数据，我们每天凌晨做一个全表扫描<待优化>

shell定时：

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_t_real_time_query_update_everyday.sh

内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_real_time_query
select id,marketsum,typesum,varietysum,countsum,dailycount,crawltime
from
(select uuid() id,count(distinct marketname) marketsum,count(distinct type) typesum,count(distinct variety) varietysum,count(1) countsum  
from ods.ods_sourcedata) aanum
left join
(select count(data) dailycount from ods.ods_sourcedata where data = from_unixtime(unix_timestamp(),'yyyy-MM-dd')) bbnum
left join
(select crawltime from ods.ods_sourcedata order by crawltime desc  limit 1) ccnum;
EOF
```
<b style ='color :red'> 测试：187079条记录,用时151.665s,待优化</b>

## 5.3 词云表
stage1:
//建立 
```
create table dw_t_cloud (
type string,
typesum int
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;
```
stage2:
// 往 dw_t_real_time_query中插值
这个没有历史数据，我们每天凌晨做一个全表扫描<待优化>

shell定时：

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_t_cloud_update_everyday.sh

内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_cloud
select type, count(type) typesum
from ods.ods_sourcedata
group by type;
EOF
```
<b style="color :red">测试：187079条记录,用时73.095s,待优化</b>


## 5.4 抓取量查询主题表

stage1: 建立抓取量查询表，其中id 即为mysql 表中的PK

```
create table dw_t_type_record (
id string,
provincename string, 
marketname string,
type string,
crawltime string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;
```

stage2:历史数据导入

```
insert into table dw_t_type_record
select uuid() id,provincename,marketname,type,crawltime
from  dw_sourcedata_clear_price;
```

定时shell：

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_t_type_record_update_everyday.sh

内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into table dw_t_type_record
select uuid() id,provincename,marketname,type,crawltime
from  dw_sourcedata_clear_price where data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
```

## 5.5  区域行情主题表

stage1 : 建立 dw_t_regional_market 

```
create table dw_t_regional_market(
id string,
provincename string,
type string,
variety string,
avgprice double,
productdate string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;
```

stage2:

向 dw_t_regional_market插值,我们只考虑"当天"的区域行情,不考虑历史数据。
也就是说dw_t_regional_market 只存 "当天" 数据,过天后数据就会覆盖掉。

定时shell：

路径 ：/home/hadoop/hive3/scripts

创建 ：vi insert_dw_t_regional_market_update_everyday.sh

内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_regional_market
select uuid() id,provincename,type,variety,avgprice,productdate
from  dw_sourcedata_clear_price where productdate in
(select distinct(productdate) 
from dw_sourcedata_clear_price
where datediff(to_date(current_timestamp()),productdate)< 10  order by productdate desc limit 1);
EOF
```

## 5.6 品种对比

stage1:
建立 dw_t_type_compare

```
create table dw_t_type_compare(
id string,
provincename string,   
marketname string,
type string,
variety string,
avgprice double,
productdate string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;

```

stage2:
此表我们也不需要有历史数据
往dw_t_type_compare中插值,由于我们可能当前好几天都没有数据
故我们取前20天时间,所以需要在springboot中确定一下具体的天数。
具体展示多少天的日期大家自己在springboot中控制。
建议只展现 7-10 天 ,不要超过10天

定时shell：

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_t_type_compare_update_everyday.sh

内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_type_compare
select uuid() id,provincename,marketname,type,variety,avgprice,productdate
from  dw_sourcedata_clear_price where datediff(to_date(CURRENT_TIMESTAMP()) ,productdate)< 30; 
EOF
```

## 5.7 数据查询
//功能：查看一个品种农产品的一段时间的每天平均价格
和 和dw_t_type_compare共用一个表 


## 5.8 价格走势
stage1:
建立 dw_t_price_trender
功能描述: 刻画一个省份下市场的品类的一个品种的一个月价格平均价格
展示多少天的日期大家自己在springboot中控制
建议只展现 7-10 天 ,不要超过10天

```
create table dw_t_price_trender (
id string,
provincename string,
marketname string,
type string,
variety string,
avgprice double,
maxprice double,
minprice double,
productdate string,
productmonth string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;

```

stage-2:导入历史数据 

// 只要当年数据
```
insert into  dw_t_price_trender
select uuid() id,provincename,marketname,type,variety,avgprice,maxprice,minprice,productdate,month(productdate) productmonth
from dw_sourcedata_clear_price where year(productdate) = year(to_date(current_timestamp()));
```

stage3:

// 往dw_t_price_trender中插值,由于我们可能当前（就是现在now）往前推好几天都没有数据
//故我们取从现在往前推30天时间,所以日期的控制自己再springboot中做
//具体展示多少天的日期大家自己在springboot中控制.
//建议只展现 7-10 天 ,不要超过10天

定时shell：

路径 ：/home/hadoop/hive3/dw

创建 ：vi insert_dw_t_price_trender_update_everyday.sh
内容 ：

```
#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_t_price_trender
select uuid() id,variety,minprice,maxprice,avgprice,month(productdate) productmonth
from dw_sourcedata_clear_price where year(productdate) = year(to_date(current_timestamp())) and data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
```

## 5.9 价格预测

// 功能定义：直接利用历史数据,跑一模型（日期-价格函数）
// 预测一个省份下的一个市场的某品类下的某品种的平均价格走势
// 这里我们只提供 一个具体的省份 市场的某品类品种的价格预测。
(所有的预测方法模型都是一样的)
```
create table dw_t_price_prediction(
id string,    
provincename string,
marketname string,
type string,
variety string,
avgprice double,
productdate string
)
row format serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
with serdeproperties (
'field.delim'=',',
'serialization.null.format'='NULL' )
stored as textfile;

```

导入历史数据:

```
insert into dw_t_price_prediction
select uuid() id,provincename,marketname,type,variety,avgprice,productdate
from  dw_sourcedata_clear_price;
```

