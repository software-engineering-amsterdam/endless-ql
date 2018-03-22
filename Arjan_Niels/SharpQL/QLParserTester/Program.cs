using QLParser;
using QLParser.Analysis;
using QLParser.AST.QL;
using QLParser.AST.QLS;
using System;

namespace QLParserTester
{
    class Program
    {
        private const string QLFile = @"..\..\..\QLParserTester\Questionnaire.ql";
        private const string QLSFile = @"..\..\..\QLParserTester\QLStyling.qls";

        static void Main(string[] args)
        {
            Console.WriteLine("---- Start parsing the QL ----");
            FormNode ql = QLParserHelper.Parse(Util.FileToString(QLFile));
            QLPrinter.Print(ql);

            Console.WriteLine();
            Console.WriteLine("---- Start parsing QLS ----");
            QLSNode qls = QLSParserHelper.Parse(Util.FileToString(QLSFile));
            QLSPrinter.Print(qls);

            Analyser.Analyse(ql, qls);

            var errors = Analyser.GetErrors();
            Console.WriteLine("\n\n---- Errors and/or warnings: {0} ----", errors.Count);
            foreach (string error in errors)
                Console.WriteLine(error);

            Console.ReadLine();
        }
    }
}
