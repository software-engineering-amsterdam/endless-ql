using QLParser.AST.QL;
using QLParser.Exceptions;

namespace QLParser.Analysis.QL.Evaluator
{
    public class StatementTypeEvaluator
    {
        /// <summary>
        /// Translate QValueType to StatementType.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public static StatementType GetStatementType(IExpressionNode node)
        {
            switch (node.GetQValueType())
            {
                case QValueType.Boolean:
                    return StatementType.Boolean;
                case QValueType.Text:
                    return StatementType.Text;
                case QValueType.Money:
                case QValueType.Integer:
                case QValueType.Double:
                case QValueType.Hex:
                    return StatementType.Numeric;
                case QValueType.Unknown:
                    return StatementType.Unknown;
            }

            throw new UnknownQValueTypeException("We don't know what to do with this QValueType.");
        }

        public static QValueType GetStatementResultType(IExpressionNode left, IExpressionNode right)
        {
            if (left.GetQValueType() == QValueType.Text || right.GetQValueType() == QValueType.Text)
                return QValueType.Text;

            if (left.GetQValueType() == QValueType.Boolean || right.GetQValueType() == QValueType.Boolean)
                return QValueType.Boolean;

            if (left.GetQValueType() == QValueType.Money || right.GetQValueType() == QValueType.Money)
                return QValueType.Money;

            if (left.GetQValueType() == QValueType.Double || right.GetQValueType() == QValueType.Double)
                return QValueType.Double;

            return QValueType.Integer;
        }
    }
}
