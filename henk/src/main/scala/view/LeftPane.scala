package view

import scalafx.scene.layout.{HBox, BorderPane}
import scalafx.scene.control.{Button, TextArea}
import scalafx.geometry.{Pos, Insets}
import scalafx.scene.text.Text
import scalafx.scene.paint.Color.Red

import scalafx.scene.paint.Color._

class CodeSection extends TextArea {
  margin = Insets(15, 0, 15, 0)
  minHeight = 600
  maxHeight = 600
  wrapText = true
}

class ErrorField extends Text {
  text = "Error: "
  fill = Red
  visible = false
}

class LeftPane extends BorderPane {
  var codeSection = new CodeSection

  top = new ErrorField
  center = codeSection
  bottom = new Submit(codeSection)
}

class Submit(codeSection: TextArea) extends Button {
  text = "Generate"
  onAction = { ae => println(codeSection.text()) }
}
