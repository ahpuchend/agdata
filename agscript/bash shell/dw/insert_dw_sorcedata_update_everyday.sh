#!/bin/bash
source /home/hadoop/.bash_profile
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use dw;
insert into dw_sourcedata
select * from ods.ods_sourcedata where data = from_unixtime(unix_timestamp(),'yyyy-MM-dd');
EOF
