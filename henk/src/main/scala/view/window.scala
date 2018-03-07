import scala.swing._

class CodeSection extends TextArea {
  lineWrap = true;
  wordWrap = true;
  tabSize = 2;
}

class LeftPanel extends BoxPanel(Orientation.Vertical) {
  border = Swing.EmptyBorder(10, 10, 10, 10)

  val codeSection = new CodeSection
  contents += new ScrollPane(codeSection)
  contents += Swing.VStrut(10)

  contents += new BoxPanel(Orientation.Horizontal) {
    contents += Swing.HGlue
    contents += Button("Generate") { println(codeSection.text) }
  }
}

class RightPanel extends BoxPanel(Orientation.Vertical) {
  border = Swing.EmptyBorder(10, 10, 10, 10)

  List("Did you sell a house in 2010", "Did you sell a house in 2010", "Did you enter a loan for maintenance/reconstruction?").map(
    labels =>
      this.contents += new BoxPanel(Orientation.Horizontal) {
        contents += Swing.HGlue
        contents += new Label(labels ++ "  ")
        contents += new CheckBox("Yes")
        contents += Swing.HGlue
      }
    )

    contents += new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HGlue
      contents += Button("Report") { println("Report") }
    }
}

class Window extends MainFrame {
  title = "Henk GUI"

  contents = new GridPanel(1,1) {
    contents += new LeftPanel
    contents += new RightPanel
  }
}

// TODO: Main trigger, move to other file
object GuiProgramOne {
  def main(args: Array[String]) {
    val mainWindow = new Window
    mainWindow.visible = true
  }
}
