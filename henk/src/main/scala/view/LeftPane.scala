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

class ErrorText extends Text {
  fill = Red
  visible = true
  wrappingWidth = 400
}

class LeftPane extends BorderPane {
  var codeSection = new CodeSection
  var error       = new ErrorText

  top    = error
  center = codeSection
  bottom = new Submit(codeSection, error)
}

class Submit(codeSection: TextArea, error: ErrorText) extends Button {
  text = "Generate"
  onAction = {
    error.text = "Error: Error: Error: Error: Error: Error:"
    _ => println(codeSection.text())
  }
}
