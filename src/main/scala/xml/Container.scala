package xml

import java.nio.file.{Path, Paths}

object Container{
  val PATH: Path = Paths.get("META-INF", "container.xml")
}

class Container(val xmlString: String) extends XMLInput{
  val opfFilePath = Paths.get(xml.\("rootfiles").\("rootfile").head.\@("full-path"))
}
