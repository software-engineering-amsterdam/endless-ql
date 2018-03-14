import ast.DogeParser

class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DogeParser().parse().getEnabledQuestions()
            //Application.launch(DogeApp::class.java, *args)
        }
    }

}