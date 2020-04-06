#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert overwrite table dw_t_cloud
select type, count(type) typesum
from ods.ods_sourcedata
group by type;
EOF
