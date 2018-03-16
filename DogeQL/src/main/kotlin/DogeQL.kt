import ast.DogeParser
import javafx.application.Application
import ui.DogeApp

class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            DogeParser().parse().getEnabledQuestions()
            Application.launch(DogeApp::class.java, *args)
        }
    }

}