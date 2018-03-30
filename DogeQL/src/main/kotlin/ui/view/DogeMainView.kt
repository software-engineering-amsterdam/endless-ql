package ui.view

import javafx.stage.FileChooser
import tornadofx.*
import ui.controller.DogeController
import ui.view.fragment.FormFragment
import ui.view.fragment.StylizedFormFragment

class DogeMainView : View() {

    private val model: DogeController by inject()

    private val minHeight = 400.0
    private val minWidth = 400.0

    private val fragment = FormFragment()

    override val root = vbox {
        minHeight = this@DogeMainView.minHeight
        minWidth = this@DogeMainView.minWidth

        menubar {
            menu("Import"){
                item("Language").action { loadDogeQl() }
                item("Style").action { loadStyle() }
            }
        }

        add(fragment)
    }


    fun loadDogeQl(){
        chooseFile("Select file", arrayOf(FileChooser.ExtensionFilter("Doge questionnaire file", ".doge")))

        model.load()
    }

    fun loadStyle(){
        chooseFile("Select file", arrayOf(FileChooser.ExtensionFilter("Doge questionnaire style file", ".shiba")))

        fragment.replaceWith(StylizedFormFragment())

    }

}
