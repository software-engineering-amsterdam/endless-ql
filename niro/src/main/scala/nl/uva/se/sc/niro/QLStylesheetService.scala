package nl.uva.se.sc.niro

import java.io.File

import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.errors.Errors.Error
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import nl.uva.se.sc.niro.parser.QLStylesheetParser
import nl.uva.se.sc.niro.typechecking.QLSTypeChecker
import org.antlr.v4.runtime.CharStreams

// TODO check if this is the correct name, maybe facade is better
object QLStylesheetService {
  def importQLStylesheetSpecification(form: QLForm, qlsFile: File): Either[Seq[Errors.Error], Option[QLStylesheet]] = {
    if (qlsFile.exists()) {
      val stylesheet: QLStylesheet = QLStylesheetParser.parse(CharStreams.fromFileName(qlsFile.getAbsolutePath))
      val parseErrors: Seq[Error] = QLStylesheetParser.getParseErrors.toList
      if (parseErrors.isEmpty) {
        QLSTypeChecker
          .pipeline(form, stylesheet)
          .left
          .map(error => Seq(error))
          .right
          .map(stylesheet => Some(stylesheet))
      } else {
        Left(parseErrors)
      }
    } else {
      Right(None)
    }

  }
}
