package ql.spec.helpers

import ql.models.ast._
import ql.parsers._

import scala.io.Source
import java.net.URL

object FormHelper {
  def getForm(location: URL): ASTNode = {
    QlFormParser.parseFromURL(location)
  }
}
