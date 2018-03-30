package qls.model

import qls.ast.node.QlsNode

data class StyleSheet(val pages : List<Page>, val name : String) : QlsNode