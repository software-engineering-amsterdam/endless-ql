using System.IO;
using System.Text;

namespace QLParserTester
{
    class Util
    {
        public static string FileToString(string file)
        {
            string[] lines = File.ReadAllLines(file);
            StringBuilder builder = new StringBuilder();
            foreach (string line in lines)
                builder.AppendLine(line);

            return builder.ToString();
        }
    }
}
