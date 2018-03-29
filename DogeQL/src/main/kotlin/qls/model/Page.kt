package qls.model

import qls.ast.node.QlsNode

data class Page(val styles : List<Style>, val title : String) : QlsNode