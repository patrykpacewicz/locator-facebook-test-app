package pl.ppac.cityfinder.infrastructure.geonames

import com.typesafe.scalalogging.LazyLogging

import scala.io.{BufferedSource, Source}

private [cityfinder] object FileLoader extends LazyLogging {
  def loadFromResourceFile(path: String): Iterator[Array[String]] = {
    logger.info(s"Loading resource file: $path")

    Source
      .fromResource(path)("UTF-8")
      .getLines()
      .filterNot(_.startsWith("#"))
      .map(_.split('\t'))
      .filterNot(_.isEmpty)
  }
}