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
