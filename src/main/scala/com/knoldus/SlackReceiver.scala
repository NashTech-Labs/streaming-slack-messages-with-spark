package com.knoldus

import org.apache.spark.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.jfarcand.wcs.{TextListener, WebSocket}
import scala.util.parsing.json.JSON
import scalaj.http.Http

class SlackReceiver(oauthToken: String)
    extends Receiver[String](StorageLevel.MEMORY_ONLY)
    with Runnable
    with Logging {

  private val slackUrl = "https://slack.com/api/rtm.connect"

  @transient
  private var thread: Thread = _

  override def onStart(): Unit = {
    thread = new Thread(this)
    thread.start()
  }

  override def onStop(): Unit = {
    thread.interrupt()
  }

  override def run(): Unit = {
    receive()
  }

  private def receive(): Unit = {
    val webSocket = WebSocket().open(webSocketUrl())
    webSocket.listener(new TextListener {
      override def onMessage(message: String) {
        store(message)
      }
    })
  }

  private def webSocketUrl(): String = {
    val response = Http(slackUrl).param("token", oauthToken).asString.body
    JSON.parseFull(response).get.asInstanceOf[Map[String, Any]]("url").toString
  }

}
