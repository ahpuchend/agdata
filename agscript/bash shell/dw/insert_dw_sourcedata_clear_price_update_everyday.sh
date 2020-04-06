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
