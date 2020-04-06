#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_type_compare
select uuid() id,provincename,marketname,type,variety,avgprice,productdate
from  dw_sourcedata_clear_price where datediff(to_date(CURRENT_TIMESTAMP()) ,productdate)< 30; 
EOF
