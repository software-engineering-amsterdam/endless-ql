using QL_Parser.AST.Nodes;
using QL_Vizualizer.Expression;

namespace QL_Vizualizer.Factories
{
    public static class ExpressionFactory
    {
        /// <summary>
        /// Transorms ConditionalNode into a boolean Expression
        /// DUMMY METHOD, IGNORES INPUT, RETURNS TRUE
        /// </summary>
        /// <param name="conditionalNode">Node to parse</param>
        /// <returns>Boolean Expression</returns>
        public static IExpression<bool> GetCondition(ConditionalNode conditionalNode)
        {
            // TODO: implement method
            return new ExpressionLeaf<bool>(() => { return true; });
        }
    }
}
