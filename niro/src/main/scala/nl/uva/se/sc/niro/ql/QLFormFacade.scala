package nl.uva.se.sc.niro.ql

import java.io.File

import com.github.tototoshi.csv._
import nl.uva.se.sc.niro.errors.Errors._
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.ql.model.ast.evaluation.QLFormEvaluator.ValueStore
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.Answer
import nl.uva.se.sc.niro.ql.parser.QLFormParser
import nl.uva.se.sc.niro.ql.parser.typecheck.TypeCheckFacade
import org.antlr.v4.runtime.CharStreams

import scala.io.Source

object QLFormFacade {

  def importQLSpecification(file: File): Either[Seq[Error], QLForm] = {
    importQLSpecification(Source.fromFile(file).mkString)
  }

  def importQLSpecification(newQLInput: String): Either[Seq[Error], QLForm] = {
    val qlFormAst: QLForm = QLFormParser.parse(CharStreams.fromString(newQLInput))
    val parseErrors: Seq[Error] = QLFormParser.getParseErrors.toList

    if (parseErrors.isEmpty) {
      TypeCheckFacade.pipeline(qlFormAst)
    } else {
      Left(parseErrors)
    }
  }

  def saveMemoryTableToCSV(valueStore: ValueStore, file: File): Unit = {
    val table: List[List[String]] = valueStore.mapValues(answerToString).map(tuple2ToList).toList

    implicit object CSVFormat extends DefaultCSVFormat {
      override val quoting: Quoting = QUOTE_ALL
    }

    val headerRow = List("QuestionId", "Answer")
    val writer = CSVWriter.open(file)
    writer.writeAll(headerRow :: table)
    writer.close()
  }

  // To retain type-information when converting tuples to lists
  def tuple2ToList[T](t: (T, T)): List[T] = List(t._1, t._2)

  def answerToString(answer: Answer): String = {
    answer.value.toString
  }

}
