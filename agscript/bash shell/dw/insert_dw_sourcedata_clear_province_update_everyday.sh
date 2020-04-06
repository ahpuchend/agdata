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
