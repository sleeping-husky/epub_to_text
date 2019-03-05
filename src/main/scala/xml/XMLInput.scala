package xml

import java.nio.file.{Files, Path}

import scala.xml.{Elem, XML}


object XMLInput{

  private def deleteBOM(xmlString: String): String = {
    val bomPrefix = "\uFEFF";
    xmlString match {
      case a if a.startsWith(bomPrefix) => a.replaceFirst(bomPrefix, "")
      case a => a
    }
  }
}


trait XMLInput {
  protected val xmlString: String
  protected[xml] def xml: Elem = XML.loadString(XMLInput.deleteBOM(xmlString))
}
