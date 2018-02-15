using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Tests
{
    class TestHelper
    {
        public static QuestionForm ParseInputFile(string fileLocation)
        {
            return ParseInputString(System.IO.File.ReadAllText(fileLocation));
        }

        public static QuestionForm ParseInputString(string input)
        {
            ICharStream stream = CharStreams.fromstring(input);
            ITokenSource lexer = new QLLexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            QLParser.FContext context = parser.f();
            QLListener listener = new QLListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(listener, context);
            return listener.Form;
        }

        public static void LogQuestionCountFail(string testName, int questionCount, int desiredQuestionCount)
        {
            LogToConsole("Test " + testName + " FAILED");
            LogToConsole(testName + " test trace: " + questionCount + " question(s) found instead of " + desiredQuestionCount);
        }

        public static void LogToConsole(List<string> messages)
        {
            foreach (string message in messages)
            {
                Console.WriteLine(message);
            }
        }

        public static void LogToConsole(string message)
        {
            Console.WriteLine(message);
        }
    }
}
