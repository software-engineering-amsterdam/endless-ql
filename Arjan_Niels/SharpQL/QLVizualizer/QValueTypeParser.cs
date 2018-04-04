using System;

namespace QLVisualizer
{
    public static class QValueTypeParser
    {
        public static Hexadecimal ParseHexadecimal(string value)
        {
            return Hexadecimal.FromString(value);
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
