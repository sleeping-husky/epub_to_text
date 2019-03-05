package xml

object ContentsXHTML{

  private def deleteRuby(value: String): String =
    value.replaceAll("<ruby>", "").replaceAll("</ruby>", "").replaceAll("<rt>.*?</rt>", "")
}

class ContentsXHTML(xmlStringOrg: String) extends XMLInput{
  override val xmlString = ContentsXHTML.deleteRuby(xmlStringOrg)
  def text: String = xml.\("body").text
}
