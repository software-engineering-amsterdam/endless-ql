using QLParser.AST;
using System;

namespace QLParserTester
{
    static class QLSPrinter
    {
        public static void Print(QLSNode node)
        {
            Console.WriteLine(node.ToString());
        }
    }
}
