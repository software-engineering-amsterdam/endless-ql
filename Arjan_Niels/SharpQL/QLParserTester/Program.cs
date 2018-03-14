using QLParser;
using QLParser.Analysis;
using QLParser.AST;
using QLParser.AST.Nodes;
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
            FormNode form = QLParserHelper.Parse(Util.FileToString(QLFile));
            QLPrinter.Print(form);

            Analyser.Analyse(form);
            var errors = Analyser.GetErrors();
            Console.WriteLine("\n\n---- Errors: {0} ----", errors.Count);
            foreach (string error in errors)
                Console.WriteLine(error);


            Console.WriteLine();
            Console.WriteLine("---- Start parsing QLS ----");
            QLSNode qls = QLSParserHelper.Parse(Util.FileToString(QLSFile));
            QLSPrinter.Print(qls);

            Console.ReadLine();
        }
    }
}
