package ql.ui

import ql.models.ast._
import ql.collectors._
import ql.evaluators._

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.input._
import scalafx.event.ActionEvent

class ComputationFactory(symbolTable: collection.mutable.Map[Identifier, ExpressionValue], evaluator: SymbolTableEvaluator) {

  def create(computation: Computation, cb: () => Unit): HBox = {
    val identifiers = ExpressionCollector.getIdentifiers(computation.valAssign)
    val values = identifiers.map(symbolTable.get(_))
    if(values.exists(x => x.isEmpty)) {
      createEmptyComputation(computation)
    } else {
      createEvaluatedComputation(computation, cb)
    }
  }

  def createEvaluatedComputation(computation: Computation, cb: () => Unit): HBox = {
    val box = new HBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
    }
    val outcome = evaluator.evaluateComputation(computation, symbolTable)
    symbolTable.update(computation.varDecl.id, outcome)
    val inputField = new TextField {
      disable=true
      text=s"${outcome.value}"
    }
    box.children = List(new Label(computation.label), inputField)
    box
  }

  def createEmptyComputation(computation: Computation): HBox = {
    val box = new HBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
    }
    val inputField = new TextField {
      disable=true
    }
    box.children = List(new Label(computation.label), inputField)
    box
  }
}
