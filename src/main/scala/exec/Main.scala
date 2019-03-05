package exec

import java.nio.file._

import epub.EPUBInputter
import epub.EPUBInputter.getClass
import scala.collection.JavaConverters._


object Main {


  def main(args: Array[String]): Unit = {
    println("-- start ---------------------------------")
//    check()
    translate()



    println("-- end ---------------------------------")
  }

  def translate(): Unit ={

    // TODO この辺りは仮に動かしたときのものを埋めただけなので
    // TODO ここにディレクトリなどを正しく指定する必要がある
    val parentPath = Paths.get("parentDirPath")
    val epubDirPath = parentPath.resolve("epubDirName")
    val textDirPath = parentPath.resolve("textDirName")

    val epupExtension = "epub"
    val textExtension = "txt"

    val epubPaths = epubDirPath.toFile.listFiles().map(f => f.toPath).toSeq
    for{
      epubPath <- epubPaths
      textPath = textDirPath.resolve(epubPath.getFileName.toString.replaceAll(epupExtension, textExtension))
      text = new EPUBInputter(epubPath).readText
      path = outputFile(textPath, text)
    } yield path
  }

  def outputFile(path: Path, text: String) = Files.write(path, text.split("\t").toList.asJava)

  def check(): Unit ={

    // TODO この辺りは仮に動かしたときのものを埋めただけなので
    // TODO ここにディレクトリなどを正しく指定する必要がある
    val parentPath = Paths.get("parentPath")
    val epubFilePath = parentPath.resolve("epubFileName");

    val epubInputter = EPUBInputter(epubFilePath)
    println(epubInputter.readText)
  }
}
