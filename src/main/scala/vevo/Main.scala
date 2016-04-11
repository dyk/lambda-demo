package vevo


import java.util

import scala.collection.JavaConverters._
import java.net.URLDecoder
import com.amazonaws.services.lambda.runtime.events.S3Event
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

class Main {
  def decodeS3Key(key: String): String = URLDecoder.decode(key.replace("+", " "), "utf-8")

  def getSourceBuckets(event: S3Event): java.util.List[String] = {
    val f = Future {
      val result = event.getRecords.asScala.map(record => decodeS3Key(record.getS3.getObject.getKey)).asJava
      println(s"hejka ${result}")
    }
    println(Await.result(f, 200.seconds))
    println("ok " + f.isCompleted)
    val l = new util.ArrayList[String]()
    l.add("test")
    return l
  }
}
