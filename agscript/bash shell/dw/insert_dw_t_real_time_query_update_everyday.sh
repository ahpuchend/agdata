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
