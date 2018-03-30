package qls.visitor

import qls.model.*

interface QlsVisitor<out T>{

    fun visit(styleSheet: StyleSheet): T
    fun visit(page: Page): T
    fun visit(style: Style): T
    fun visit(section: Section): T
    fun visit(defaultAttributes: DefaultAttributes): T
    fun visit(question: Question): T
    fun visit(element: Element): T

}