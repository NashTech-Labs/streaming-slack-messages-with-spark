package com.knoldus

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SlackStreamingApp extends App {

  val conf = new SparkConf().setMaster(args(0)).setAppName("SlackStreaming")
  val ssc = new StreamingContext(conf, Seconds(30))
  val stream = ssc.receiverStream(new SlackReceiver(args(1)))
  stream.print()
  if (args.length > 2) {
    stream.saveAsTextFiles(args(2))
  }
  ssc.start()
  ssc.awaitTermination()

}
