using QLParser.AST.QL;

namespace QLParser.Analysis.QL.Syntactic
{
    public class SingleFormValidator : IQLAnalyser
    {
        private readonly string errorMessage = "This AST contains multiple 'FormNode'.";

        /// <summary>
        /// A recursive function to find out if the AST contains multiple FormNode
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public bool Analyse(QLNode node)
        {
            var childValue = true;
            foreach (QLNode child in node.Children)
            {
                if (child.Type == NodeType.FORM)
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