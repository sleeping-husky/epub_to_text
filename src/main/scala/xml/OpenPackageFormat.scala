package xml

import java.nio.file.{Path, Paths}

import scala.xml.{NodeSeq, XML}

class OpenPackageFormat(val xmlString: String) extends XMLInput{
  private def metadata: NodeSeq = xml \ "metadata"
  def title: String = metadata.\("title").head.text
  def authors: Seq[String] = metadata.\("creator").map(n => n.text).toSeq

  private def spine: NodeSeq = xml \ "spine"
  private def idrefs: Seq[String] = spine.\("itemref").map(n => n.\@("idref")).toSeq

  private def manifest: NodeSeq = xml \ "manifest"
  private def items: NodeSeq = manifest.\("item")
  private def xhtmlItems: NodeSeq = items.filter(n => n.\@("media-type") == "application/xhtml+xml")
  private def xhtmlPathMap: Map[String, Path] = xhtmlItems.map(n => (n.\@("id"), Paths.get(n.\@("href")))).toMap

  // TODO こいつが相対パスなので親がいる
  def sequentialXHTMLPaths(currentDirPath: Path): Seq[Path] =
    idrefs.flatMap(i => xhtmlPathMap.get(i)).map(p => currentDirPath.resolve(p)).toSeq
}
