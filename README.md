Spark with instrumentation
==========================

Initial setup
-------------
```
# docker-grafana-graphite
git clone https://github.com/kamon-io/docker-grafana-graphite.git
pushd docker-grafana-graphite
git checkout 2a02df6289141ecf273503775e9c21c75a310128  # optional
docker build -t kamon/grafana_graphite .
popd

# dirs for docker-grafana-graphite
rm -rf data log
mkdir -p data/whisper data/elasticsearch data/grafana data/spark-events log/graphite log/graphite/webapp log/elasticsearch # NOTE: CRITICAL, graphite won't start otherwise!!!

# mock job
pushd mock-job && sbt package && popd
```

Start `grafana-graphite`, `spark-master`, `spark-worker`, `spark-history-server`:
```
docker-compose up grafana-graphite spark-master spark-history-server spark-worker
```

Run mock job
------------
```
docker-compose run spark-client
```

Setup grafana dashboard
-----------------------
```
open localhost:80
# login as admin/admin
# import spark-runtime-dashboard.json, hook it up to 'local graphite'
```
![grafana](raw/docs/image/grafana.png)

View Spark history server
-------------------------
```
open localhost:18080
```
![history-server](raw/docs/image/history-server.png)

View Graphite
-------------
```
open localhost:81
```
![history-server](raw/docs/image/graphite.png)
