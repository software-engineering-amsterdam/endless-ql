using QLParser.AST.Nodes;
using System;

namespace QLVisualizer.Factories
{
    public static class QValueTypeParser
    {
        public static string ParseHexadecimal(QValueType qValueType, string value)
        {  
            if (qValueType == QValueType.HEX && value.StartsWith("#") && value.Length == 7)
                return value;

            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, qValueType.ToString(), "Hexadecimal"));
        }

        public static bool ParseBoolean(QValueType qValueType, string value)
        {
            bool result = false;
            if (qValueType == QValueType.BOOLEAN && bool.TryParse(value, out result))
                return result;
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, qValueType.ToString(), "Boolean"));
        }

        public static double ParseDouble(QValueType qValueType, string value)
        {
            double result = 0;
            if (qValueType == QValueType.DOUBLE && double.TryParse(value, out result))
                return result;
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, qValueType.ToString(), "Double"));
        }

        public static double ParseMoney(QValueType qValueType, string value)
        {
            if (qValueType == QValueType.MONEY)
                return ParseDouble(QValueType.DOUBLE, value);
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, qValueType.ToString(), "Money"));
        }

        public static string ParseText(QValueType qValueType, string value)
        {
            return value;
        }

        public static int ParseInteger(QValueType qValueType, string value)
        {
            int result = 0;
            if (qValueType == QValueType.INTEGER && int.TryParse(value, out result))
                return result;
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, qValueType.ToString(), "Integer"));
        }
    }
}
