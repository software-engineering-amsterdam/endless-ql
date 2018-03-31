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
                case QValueType.BOOLEAN:
                    return StatementType.Boolean;
                case QValueType.TEXT:
                    return StatementType.Text;
                case QValueType.MONEY:
                case QValueType.INTEGER:
                case QValueType.DOUBLE:
                case QValueType.HEX:
                    return StatementType.Numeric;
                case QValueType.UNKNOWN:
                    return StatementType.Unknown;
            }

            throw new UnknownQValueTypeException("We don't know what to do with this QValueType.");
        }

        public static QValueType GetStatementResultType(IExpressionNode left, IExpressionNode right)
        {
            if (left.GetQValueType() == QValueType.TEXT || right.GetQValueType() == QValueType.TEXT)
                return QValueType.TEXT;

            if (left.GetQValueType() == QValueType.BOOLEAN || right.GetQValueType() == QValueType.BOOLEAN)
                return QValueType.BOOLEAN;

            if (left.GetQValueType() == QValueType.MONEY || right.GetQValueType() == QValueType.MONEY)
                return QValueType.MONEY;

            if (left.GetQValueType() == QValueType.DOUBLE || right.GetQValueType() == QValueType.DOUBLE)
                return QValueType.DOUBLE;

            return QValueType.INTEGER;
        }
    }
}
