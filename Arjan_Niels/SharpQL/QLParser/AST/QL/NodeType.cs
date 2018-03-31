namespace QLParser.AST.QL
{
    public enum NodeType
    {
        Form,
        Question,
        Conditional,
        Computed,
        Identifier,
        Literal,

        // Expression
        LogicalExpression,
        ComparisonExpression,
        ArthimetricExpression,
        TextConcatination
    }
}
