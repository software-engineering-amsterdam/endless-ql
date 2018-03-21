namespace QLParser
{
    class Util
    {
        public static string RemoveQuotes(string text)
        {
            return text.Substring(1, text.Length - 2);
        }
    }
}
