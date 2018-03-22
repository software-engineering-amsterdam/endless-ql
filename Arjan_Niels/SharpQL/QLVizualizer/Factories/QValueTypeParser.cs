using System;

namespace QLVisualizer.Factories
{
    public static class QValueTypeParser
    {
        public static string ParseHexadecimal(string value)
        {  
            if (value.StartsWith("#") && value.Length == 7)
                return value;

            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, "Hexadecimal"));
        }

        public static bool ParseBoolean(string value)
        {
            bool result = false;
            if (bool.TryParse(value, out result))
                return result;
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, "Boolean"));
        }

        public static double ParseDouble(string value)
        {
            double result = 0;
            if (double.TryParse(value, out result))
                return result;
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, "Double"));
        }

        public static double ParseMoney(string value)
        {
            return ParseDouble(value);
        }

        public static string ParseText(string value)
        {
            return value;
        }

        public static int ParseInteger(string value)
        {
            int result = 0;
            if (int.TryParse(value, out result))
                return result;
            throw new InvalidOperationException(UserMessages.ExceptionCannotParse(value, "Integer"));
        }
    }
}
