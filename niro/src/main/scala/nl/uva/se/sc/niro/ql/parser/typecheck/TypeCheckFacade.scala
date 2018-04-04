package nl.uva.se.sc.niro.ql.parser.typecheck

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import org.apache.logging.log4j.scala.Logging

object TypeCheckFacade extends Logging {

  /** Performs all type checking phases. Early aborts when one of the tasks returns a TypeCheckError
    * */
  // Order of execution is important here to avoid infinite loops in subsequent phases
  def pipeline(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] =
    for {
      _ <- References.check(qLForm)
      _ <- CyclicDependency.check(qLForm)
      _ <- StaticTypes.check(qLForm)
      _ <- Predicates.check(qLForm)
      _ <- DuplicateQuestions.check(qLForm)
      qlFormWithPossibleWarnings = DuplicateLabels.check(qLForm)
    } yield qlFormWithPossibleWarnings
}
