using QLParser.AST.QL;

namespace QLParser.Analysis.Syntactic
{
    public class QuestionHasNoChildrenValidator : IQLAnalyser
    {
        private readonly string errorMessage = "Questions can't have child nodes.";

        public bool Analyse(QLNode node)
        {
            var childValue = true;
            foreach (QLNode child in node.Children)
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