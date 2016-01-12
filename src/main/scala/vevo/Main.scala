package vevo


import scala.collection.JavaConverters._
import java.net.URLDecoder
import com.amazonaws.services.lambda.runtime.events.S3Event
import play.api.libs.json._

class Main {
  def decodeS3Key(key: String): String = URLDecoder.decode(key.replace("+", " "), "utf-8")

  def getSourceBuckets(event: S3Event): java.util.List[String] = {
    val result = event.getRecords.asScala.map(record => decodeS3Key(
      record.getS3.getObject.getKey)).asJava
    val js: List[JsObject] = result.toArray.map( x => Json.parse(
      x.asInstanceOf[String]).as[JsObject]).toList

    val jsResult = JsObject(" " -> JsArray(result))
    println(result)
    return result
  }
}