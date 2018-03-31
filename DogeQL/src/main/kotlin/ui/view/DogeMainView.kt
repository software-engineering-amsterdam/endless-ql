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


    private fun loadDogeQl(){
        val files = chooseFile("Select file", arrayOf(FileChooser.ExtensionFilter("Doge questionnaire file", "*.doge")))

        if (files.isNotEmpty()){
            model.loadQuestionnaire(files.first())
        }
    }

    private fun loadStyle(){
        val files = chooseFile("Select file", arrayOf(FileChooser.ExtensionFilter("Doge questionnaire style file", "*.shiba")))

        if (files.isNotEmpty()){
            model.loadStyle(files.first())
            fragment.replaceWith(StylizedFormFragment())
        }
    }

}
