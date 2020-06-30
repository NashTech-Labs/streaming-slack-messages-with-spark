# streaming-slack-messages-with-spark

Steps to run this application:
- Create your Slack oauth token from https://slack.com/create.
- Start Apache Spark in your system in standalone mode. 
    1. Run command: ~path/to/spark/directory/$ sbin/start-master.sh
    2. Start a worker node: ~path/to/spark/directory/$ sbin/start-slave.sh spark://your-system-name:7077
- Run the Spark-Streaming app using command: sbt run local[5] your-oauth-token name-of-output-file 

Thats all, you'll be able to see messages in the form of JSON retrieved from Slack in the output file.
