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
