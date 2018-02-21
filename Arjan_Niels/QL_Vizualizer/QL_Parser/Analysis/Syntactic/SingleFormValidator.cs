using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Syntactic
{
    public class SingleFormValidator : IAnalyser
    {
        private readonly string errorMessage = "This AST contains multiple 'FormNode'.";

        public bool Analyse(Node node, bool logErrors = true)
        {
            return NodeContainsForm(node, logErrors);
        }

        /// <summary>
        /// A recursive function to find out if the AST contains multiple FormNode
        /// </summary>
        /// <param name="parent"></param>
        /// <param name="logErrors"></param>
        /// <returns></returns>
        private bool NodeContainsForm(Node parent, bool logErrors)
        {
            var childValue = true;
            foreach (Node child in parent.Children)
            {
                if (child.Type == NodeType.FORM)
                {
                    if (logErrors)
                    {
                        Analyser.AddMessage(errorMessage, MessageType.ERROR);
                    }
                    return false;
                }
                else
                    childValue = NodeContainsForm(child, logErrors);
            }

            return childValue;
        }
    }
}
