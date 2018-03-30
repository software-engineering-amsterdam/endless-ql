package doge.typechecker.scope

import doge.ast.location.Identifier
import doge.typechecker.ErrorContext

class ScopeErrorContext: ErrorContext {

    val errors = mutableListOf<Identifier>()

    override fun collect(): List<String> {
        return errors.map {
            "Undefined reference to \"${it.text}\" at ${it.location}"
        }
    }

    override fun hasErrors() = errors.isNotEmpty()

}