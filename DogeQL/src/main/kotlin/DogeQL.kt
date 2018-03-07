import ast.DogeParser

class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            Application.launch(DogeApp::class.java, *args)
            DogeParser().parse()
        }
    }

}