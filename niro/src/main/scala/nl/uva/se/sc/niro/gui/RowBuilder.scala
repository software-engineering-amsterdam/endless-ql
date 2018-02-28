package nl.uva.se.sc.niro.gui

import java.lang

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.layout.{ GridPane, Region, RowConstraints }

object RowBuilder {
  def buildDoubleColumnRow(grid: GridPane, rowNr: Int): GridPane = {
    // A row constraint is needed because other rows depend on in.
    grid.getRowConstraints.add(new RowConstraints())
    grid
  }

  def buildSingleColumnRow(grid: GridPane, rowNr: Int): GridPane = {
    // A row constraint is needed for hiding and showing entire rows.
    val rowConstraint = new RowConstraints()
    grid.getRowConstraints.add(rowConstraint)

    val innerGrid: GridPane = new GridPane()
    grid.add(innerGrid, 0, rowNr, 2, 1)
    // When invisible we don't occupy any space
    innerGrid.managedProperty().bind(innerGrid.visibleProperty())
    bindConstraintToVisiblity(rowConstraint, innerGrid)
    innerGrid
  }

  private def bindConstraintToVisiblity(constraint: RowConstraints, pane: GridPane): Unit = {
    pane
      .visibleProperty()
      .addListener(new ChangeListener[lang.Boolean] {
        override def changed(observable: ObservableValue[_ <: lang.Boolean],
                             oldValue: lang.Boolean,
                             visible: lang.Boolean): Unit = {
          constraint.setPrefHeight(if (visible) { Region.USE_COMPUTED_SIZE } else { 0.0 })
        }
      })
  }

}
