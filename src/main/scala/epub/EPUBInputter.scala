package epub

import java.io.File
import java.net.URI
import java.nio.file.{FileSystem, FileSystems, Files, Path, Paths}

import scala.collection.JavaConverters._
import xml.{Container, ContentsXHTML, OpenPackageFormat, XMLInput}


object EPUBInputter{

  def apply(path: Path): EPUBInputter = new EPUBInputter(path)

  // TODO あとでeitherとかにする?
  private def withFileSystems[A](path: Path)(to: FileSystem => A) = {
    val fs = FileSystems.newFileSystem(path, getClass().getClassLoader())
    try to(fs)
    finally fs.close()
  }


  // TODO 最後の改行を区切り文字にすることでxhtmlのpタグの改行を現状表しているがpタグで改行がない場合に困る
  private def readXMLFileInZIP(fs: FileSystem)(path: Path): String =
    Files.readAllLines(fs.getPath(path.toString)).asScala.mkString("\n")



}

class EPUBInputter(val path: Path) {

  def readText: String = {
    EPUBInputter.withFileSystems(path) {fs =>
      val readXMLFile = EPUBInputter.readXMLFileInZIP(fs)(_: Path)
      val contaienr = new Container(readXMLFile(Container.PATH))
      val opf = new OpenPackageFormat(readXMLFile(contaienr.opfFilePath))
      val texts = for{
        path <- opf.sequentialXHTMLPaths(contaienr.opfFilePath.getParent)
        contents = new ContentsXHTML(readXMLFile(path))
        text = contents.text
      } yield text

      String.join("\n",
        opf.title,
        opf.authors.mkString(", "),
        "--------------------------------------------------------",
        texts.mkString("\n")
      )
    }
  }
}
