using QLParser.AST.Nodes;

namespace QLParser.Analysis.Syntactic
{
    public class QuestionHasNoChildrenValidator : IAnalyser
    {
        private readonly string errorMessage = "Questions can't have child nodes.";

        public bool Analyse(Node node)
        {
            var childValue = true;
            foreach (Node child in node.Children)
            {
                if (child.Type == NodeType.QUESTION && child.Children.Count != 0)
                {
                    Analyser.AddMessage(errorMessage, MessageType.ERROR);
                    return false;
                }
                else
                    childValue = Analyse(child);
            }

            return childValue;
        }
    }
}