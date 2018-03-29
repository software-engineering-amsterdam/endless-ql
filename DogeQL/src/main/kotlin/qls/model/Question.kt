package qls.model

import qls.ast.node.QlsNode

data class Question(val name : String) : Element, QlsNode