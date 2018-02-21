using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Syntactic
{
    public class QuestionHasNoChildrenValidator : IAnalyser
    {
        private readonly string errorMessage = "Questions can't have child nodes.";

        public bool Analyse(Node node, bool logErrors = true)
        {
            return QuestionHasNoChildren(node, logErrors);
        }

        private bool QuestionHasNoChildren(Node parent, bool logErrors)
        {
            var childValue = true;
            foreach (Node child in parent.Children)
            {
                if (child.Type == NodeType.QUESTION && child.Children.Count != 0)
                {
                    if (logErrors)
                    {
                        Analyser.AddMessage(errorMessage, MessageType.ERROR);
                    }
                    return false;
                }
                else
                    childValue = QuestionHasNoChildren(child, logErrors);
            }

            return childValue;
        }
    }
}
