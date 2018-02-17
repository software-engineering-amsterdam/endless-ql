using QL_Parser.AST.Nodes;

namespace QL_Parser.AST.Validators
{
    public class SingleFormValidator : IASTValidator
    {
        private readonly string errorMessage = "This AST contains multiple 'FormNode'.";

        public bool IsValid(Node node, bool logErrors = true)
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
                        ASTValidator.AddError(errorMessage);
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
