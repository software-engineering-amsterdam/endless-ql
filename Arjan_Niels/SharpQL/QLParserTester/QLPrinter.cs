using QLParser.AST.Nodes;
using System;

namespace QLParserTester
{
    static class QLPrinter
    {
        public static void Print(FormNode form)
        {
            Console.WriteLine(form);
            foreach (Node section in form.Children)
                switch (section.GetNodeType())
                {
                    case NodeType.QUESTION:
                        PrintSection(section as QuestionNode);
                        break;
                    case NodeType.COMPUTED:
                        PrintSection(section as ComputedNode);
                        break;
                    case NodeType.CONDITIONAL:
                        PrintSection(section as ConditionalNode);
                        break;
                    default:
                        return;
                }
        }

        public static void PrintSection(QuestionNode question)
        {
            Console.WriteLine(question);
        }

        public static void PrintSection(ComputedNode computed)
        {
            Console.WriteLine(computed);
        }

        public static void PrintSection(ConditionalNode conditional)
        {
            Console.WriteLine("\n" + conditional);
            foreach (Node section in conditional.Children)
                if (section.GetType() == typeof(QuestionNode))
                    PrintSection(section as QuestionNode);
                else if (section.GetType() == typeof(ComputedNode))
                    PrintSection(section as ComputedNode);
                else
                    PrintSection(section as ConditionalNode);
        }
    }
}
