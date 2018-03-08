// package ql.validators

// import ql.models.ast._
// import ql.parsers._
// import ql.visitors._

// import scala.collection.JavaConversions._

// // case class IdentifierNotDeclared(label: String) extends Exception(label)

// object ConditionalValidator {
  // def validate(node: ASTNode) {
    // node match {
      // case binOp: ASTBinary => validateBinOp(binOp)
      // case id: ASTIdentifier => validateIdentifier(id)
      // case other => true
    // }
  // }

  // def validateIdentifier(node: ASTIdentifier): Boolean = {
    // val forms = collectFormBody(ast)
    // val formVarDecls = forms.map(QlFormParser.retrieveVarDecls).flatten
    // val varDecl = formVarDecls.filter(vd => vd.id == node)
    // // val varDecl = formVarDecls.collectFirst{ case vdc if vdc.id == node => vdc }
    // varDecl(0).typeDecl match {
      // case ASTBoolean() => true
      // case other => false
    // }
  // }

  // def validateBinOp(binOp: ASTNode): Boolean = {
    // binOp match {
      // case ASTBinary(_,_,_) => true
      // case other => true
    // }
  // }

  // def validateExpr(node: ASTNode): Boolean = {
  // }
// }
