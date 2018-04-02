package view

import scalafx.scene.layout.{ColumnConstraints, FlowPane}
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.control.ScrollPane.ScrollBarPolicy
import scalafx.geometry.{HPos, Insets}

class RightPane extends FlowPane {
  val data = Map(
    "1" -> (1, new Label("hoi")),
    "2" -> (2, new Label("DOEI")),
    "3" -> (3, new Label("hoi")),
    "4" -> (4, new Label("DOEI")),
    "5" -> (5, new Label("hoi")),
    "6" -> (6, new Label("DOEI")),
    "7" -> (7, new Label("hoi")),
    "8" -> (8, new Label("DOEI")),
    "9" -> (9, new Label("hoi")),
    "10" -> (10, new Label("DOEI")),
    "11" -> (11, new Label("hoi")),
    "12" -> (12, new Label("DOEI")),
    "13" -> (13, new Label("hoi")),
    "14" -> (14, new Label("DOEI")),
    "15" -> (15, new Label("hoi")),
    "16" -> (16, new Label("DOEI")),
    "17" -> (17, new Label("hoi")),
    "18" -> (18, new Label("DOEI")),
    "19" -> (19, new Label("hoi")),
    "20" -> (20, new Label("DOEI")),
    "21" -> (21, new Label("hoi")),
    "22" -> (22, new Label("DOEI")),
    "23" -> (23, new Label("hoi")),
    "24" -> (24, new Label("DOEI")),
    "25" -> (25, new Label("hoi")),
    "26" -> (26, new Label("DOEI")),
    "27" -> (27, new Label("hoi")),
    "28" -> (28, new Label("DOEI")),
    "29" -> (29, new Label("hoi")),
    "30" -> (30, new Label("DOEI")),
    "31" -> (31, new Label("hoi")),
    "32" -> (32, new Label("DOEI")),
    "33" -> (33, new Label("hoi")),
    "34" -> (34, new Label("DOEI")),
    "35" -> (35, new Label("hoi")),
    "36" -> (36, new Label("DOEI"))

  )

  // columnConstraints = List.fill(data.size)(
  //   new ColumnConstraints {
  //     halignment = HPos.Center
  //     hgap = 10.0
  //   }
  // )

  println(data.values sort { case(a, b, c) => a < a })
  //
  // new ScrollPane {
  //   layoutX = 50
  //   layoutY = 180
  //   prefWidth = 440
  //   prefHeight = 85
  //   hbarPolicy = ScrollBarPolicy.Never
  //   vbarPolicy = ScrollBarPolicy.Never
  //   pannable = true
  //   content = textRef
  //   style = "-fx-background-color: transparent;"
  // }

  // for(elem <- data) {
  //   add(elem._2._2, 0, elem._2._1)
  // }
}
