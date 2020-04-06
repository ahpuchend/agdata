# flume config
```
#name the components on this agent
hdfs_agent.sources = r1
hdfs_agent.sinks = k1
hdfs_agent.channels = c1

# Describe/configure the source
hdfs_agent.sources.r1.type = TAILDIR
hdfs_agent.sources.r1.filegroups = f1
hdfs_agent.sources.r1.filegroups.f1 = /home/hadoop/vegnetspider/data/vegnet/.*\.log
hdfs_agent.sources.r1.positionFile = /home/hadoop/vegnetspider/data/vegnet/.flume/taildir_position.json

# Describe the sink
hdfs_agent.sinks.k1.type = hdfs
hdfs_agent.sinks.k1.hdfs.path = hdfs://hadoop:9000/vegnet/hdfs_filegroups_source/data=%Y-%m-%d/
hdfs_agent.sinks.k1.hdfs.rollInterval = 3600
hdfs_agent.sinks.k1.hdfs.rollSize = 1048576
hdfs_agent.sinks.k1.hdfs.rollCount = 0
hdfs_agent.sinks.k1.hdfs.filePrefix = log_file_%H
hdfs_agent.sinks.k1.hdfs.fileSuffix = .log
hdfs_agent.sinks.k1.hdfs.fileType = DataStream
hdfs_agent.sinks.k1.hdfs.useLocalTimeStamp = true

# Use a channel which buffers events in memory
hdfs_agent.channels.c1.type = memory
hdfs_agent.channels.c1.capacity = 1000
hdfs_agent.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
hdfs_agent.sources.r1.channels = c1
hdfs_agent.sinks.k1.channel = c1
```

# flume bin

```
#!/bin/bash

ROOT_PATH=$(dirname $(dirname $(readlink -f $0)))
cd $ROOT_PATH

bin/flume-ng agent --conf ./conf/ -f myconf/vegnet-flume-taildir-memory-hdfs.properties -Dflume.root.logger=INFO,console -n hdfs_agent
```
