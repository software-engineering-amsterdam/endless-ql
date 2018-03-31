package view

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.{ColumnConstraints, GridPane}
import scalafx.geometry.{HPos, Insets}

import view._

object Window extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title.value = "Henk GUI"
    width = 600
    height = 600

    scene = new Scene {
      root = new GridPane {
        columnConstraints = Seq(
          new ColumnConstraints {
            padding = Insets(10)
            percentWidth = 50
            halignment = HPos.Left
            hgap = 10.0
          },
          new ColumnConstraints {
            padding = Insets(10)
            percentWidth = 50
            halignment = HPos.Left
          }
        )

        add(new LeftPane, 0, 0)
        add(new RightPane, 1, 0)
      }
    }
  }
}
