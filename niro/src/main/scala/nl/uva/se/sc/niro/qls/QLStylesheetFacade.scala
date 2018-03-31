package nl.uva.se.sc.niro.qls

import java.io.File

import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.errors.Errors.Error
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet
import nl.uva.se.sc.niro.qls.parser.QLStylesheetParser
import nl.uva.se.sc.niro.qls.parser.typecheck.TypeCheckFacade
import org.antlr.v4.runtime.CharStreams

object QLStylesheetFacade {

  def importQLStylesheetSpecification(form: QLForm, qlsFile: File): Either[Seq[Errors.Error], Option[QLStylesheet]] = {
    if (qlsFile.exists()) {
      val stylesheet: QLStylesheet = QLStylesheetParser.parse(CharStreams.fromFileName(qlsFile.getAbsolutePath))
      val parseErrors: Seq[Error] = QLStylesheetParser.getParseErrors.toList
      if (parseErrors.isEmpty) {
        TypeCheckFacade.check(form, stylesheet).right.map(stylesheet => Some(stylesheet))
      } else {
        Left(parseErrors)
      }
    } else {
      Right(None)
    }
  }

}
