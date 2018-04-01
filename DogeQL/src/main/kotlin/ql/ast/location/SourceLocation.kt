package ql.ast.location

class SourceLocation(val line: Int, val column: Int, val begin: Int, val end: Int) {

    override fun toString() = "$line:${column + 1}"

}