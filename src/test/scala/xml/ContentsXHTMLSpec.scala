package xml

import java.io.File

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import scala.io.Source

class ContentsXHTMLSpec extends FlatSpec with DiagrammedAssertions {

  private val xhtml = new ContentsXHTML(Source.fromURL(getClass.getResource("/contents001.xhtml")).mkString)

  "XHTML" should "本文をルビ抜きで取得できる" in {
    assert(xhtml.text == "\nヘッダ1\nヘッダ2\n　\n　　　1\n　\n　テストああああ試験\n「台詞」\n　\n")
  }
}
