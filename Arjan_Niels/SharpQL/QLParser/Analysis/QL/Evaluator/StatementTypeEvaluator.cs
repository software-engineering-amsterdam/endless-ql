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
                    return StatementType.BOOLEAN;
                case QValueType.TEXT:
                    return StatementType.TEXT;
                case QValueType.MONEY:
                case QValueType.INTEGER:
                case QValueType.DOUBLE:
                case QValueType.HEX:
                    return StatementType.NUMERIC;
                case QValueType.UNKNOWN:
                    return StatementType.UNKNOWN;
            }

            throw new UnknownQValueTypeException("We don't know what to do with this QValueType.");
        }

        public static QValueType GetStatementResultType(IExpressionNode lhs, IExpressionNode rhs)
        {
            if (lhs.GetQValueType() == QValueType.TEXT || rhs.GetQValueType() == QValueType.TEXT)
                return QValueType.TEXT;

            if (lhs.GetQValueType() == QValueType.BOOLEAN || rhs.GetQValueType() == QValueType.BOOLEAN)
                return QValueType.BOOLEAN;

            if (lhs.GetQValueType() == QValueType.MONEY || rhs.GetQValueType() == QValueType.MONEY)
                return QValueType.MONEY;

            if (lhs.GetQValueType() == QValueType.DOUBLE || rhs.GetQValueType() == QValueType.DOUBLE)
                return QValueType.DOUBLE;

            return QValueType.INTEGER;
        }
    }
}
