package xml

import java.nio.file.Paths

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import scala.io.Source

class ContainerSpec extends FlatSpec with DiagrammedAssertions {

  private val container = new Container(Source.fromURL(getClass.getResource("/container.xml")).mkString)

  "container" should  "opfFilePathが取得できる" in {
    assert(container.opfFilePath == Paths.get("item/aaaaa11111.opf"))
  }
}
