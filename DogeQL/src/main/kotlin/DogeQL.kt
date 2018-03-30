import doge.ast.DogeParser

class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DogeParser().parse()
//            Application.launch(DogeApp::class.java, *args)
        }
    }

}