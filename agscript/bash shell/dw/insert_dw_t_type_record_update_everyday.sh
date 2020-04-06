#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into table dw_t_type_record
select uuid() id,provincename,marketname,type,crawltime
from  dw_sourcedata_clear_price where data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
