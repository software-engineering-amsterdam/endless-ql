using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Syntactic
{
    public class SingleFormValidator : IAnalyser
    {
        private readonly string errorMessage = "This AST contains multiple 'FormNode'.";

        /// <summary>
        /// A recursive function to find out if the AST contains multiple FormNode
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public bool Analyse(Node node)
        {
            var childValue = true;
            foreach (Node child in node.Children)
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
