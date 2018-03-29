package doge.visitor.error

interface ErrorContext {

    fun collect(): List<String>

    fun hasErrors(): Boolean

}