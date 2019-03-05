package xml

import java.nio.file.Paths

import org.scalatest.{DiagrammedAssertions, FlatSpec}

import scala.io.Source

class OpenPackageFormatSpec extends FlatSpec with DiagrammedAssertions {

  private val opf = new OpenPackageFormat(Source.fromURL(getClass.getResource("/test.opf")).mkString)

  "OPF" should "タイトルを取得できる" in {
    assert(opf.title == "テストタイトル")
  }

  "OPF" should "著者リストを取得できる" in {
    assert(opf.authors == Seq.apply("テスト著者1", "テスト著者2"))
  }

  "OPF" should "メインのXHTMLパスを読み込み順に取得できる" in {
    assert(opf.sequentialXHTMLPaths(Paths.get("")) == Seq.apply(
      Paths.get("cover.xhtml"),
      Paths.get("nav.xhtml"),
      Paths.get("bodymatter_0_0.xhtml")))
  }
}
