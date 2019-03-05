package xml

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import scala.io.Source

class XMLInputSpec extends FlatSpec with DiagrammedAssertions {

  private val data = Source.fromURL(getClass.getResource("/container.xml")).mkString

  "XML" should "ファイルを読み込む" in {
    new XMLInput {
      override protected val xmlString: String = data
    }
  }

  "BOM付きXML" should "ファイルを読み込む" in {
    new XMLInput {
      override protected val xmlString: String = "\uFEFF" + data
    }
  }

  ""
}
