﻿namespace QLParser.AST.QL
{
    public enum NodeType
    {
        FORM,
        QUESTION,
        CONDITIONAL,
        COMPUTED,
        IDENTIFIER,
        LITERAL,

        // Expression
        LOGICAL_EXPRESSION,
        COMPARISON_EXPRESSION,
        ARTHIMETRIC_EXPRESSION,
        TEXT_CONCATINATION
    }
}
