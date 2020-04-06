#!/bin/bash
source /home/hadoop/.bash_profile
TODAY_STR=$(date '+%Y-%m-%d')
/usr/apache-hive-3.1.2-bin/bin/hive<<EOF 
use ods;
alter table ods_sourcedata add partition(data='$TODAY_STR');
EOF
