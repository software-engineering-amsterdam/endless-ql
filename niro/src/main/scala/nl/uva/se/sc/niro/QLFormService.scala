package nl.uva.se.sc.niro

import java.io.File

import com.github.tototoshi.csv._
import nl.uva.se.sc.niro.errors.Errors._
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer
import nl.uva.se.sc.niro.parser.QLFormParser
import nl.uva.se.sc.niro.typechecking.TypeChecker
import org.antlr.v4.runtime.CharStreams

import scala.io.Source

object QLFormService {

  def importQLSpecification(file: File): Either[Seq[Error], QLForm] = {
    importQLSpecification(Source.fromFile(file).mkString)
  }

  def importQLSpecification(newQLInput: String): Either[Seq[Error], QLForm] = {
    val qlFormAst: QLForm = QLFormParser.parse(CharStreams.fromString(newQLInput))
    val parseErrors: Seq[Error] = QLFormParser.getParseErrors.toList

    if (parseErrors.isEmpty) {
      TypeChecker.pipeline(qlFormAst).left.map(error => Seq(error))
    } else {
      Left(parseErrors)
    }
  }

  def saveMemoryTableToCSV(memoryTable: Map[String, Answer], file: File): Unit = {
    val table: Seq[List[String]] = memoryTable.mapValues(answerToString).map(tuple2ToList).toList

    val writer = CSVWriter.open(file)
    writer.writeAll(table)
    writer.close()
  }

  // To retain type-information when converting tuples to lists
  def tuple2ToList[T](t: (T, T)): List[T] = List(t._1, t._2)

  def answerToString(answer: Answer): String = {
    answer.possibleValue.map(_.toString).getOrElse("")
  }
}
