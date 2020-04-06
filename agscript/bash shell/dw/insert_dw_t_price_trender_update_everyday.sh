#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_t_price_trender
select uuid() id,variety,minprice,maxprice,avgprice,month(productdate) productmonth
from dw_sourcedata_clear_price where year(productdate) = year(to_date(current_timestamp())) and data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
