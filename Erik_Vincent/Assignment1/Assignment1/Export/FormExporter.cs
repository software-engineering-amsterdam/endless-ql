using System;
using System.IO;
using System.Linq;
using Assignment1.Execution;

namespace Assignment1.Export
{
    public static class FormExporter
    {
        public static void ExportToCSV(QLExecutor executor, string path)
        {
            var lines = executor.VisibleQuestions.Select(question =>
                EscapeCSV(question.Id) + ";" + EscapeCSV(executor.GetAnswer(question.Id).ToString()));
            var contents = string.Join("\n", lines);
            File.WriteAllText(path, contents);
        }

        private static string EscapeCSV(string s) => "\"" + s.Replace("\"", "\"\"") + "\"";
    }
}
