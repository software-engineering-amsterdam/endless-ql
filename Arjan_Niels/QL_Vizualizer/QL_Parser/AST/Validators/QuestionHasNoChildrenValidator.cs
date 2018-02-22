using QL_Parser.AST.Nodes;

namespace QL_Parser.AST.Validators
{
    public class QuestionHasNoChildrenValidator : IASTValidator
    {
        private readonly string errorMessage = "Questions can't have child nodes.";

        public bool IsValid(Node node, bool logErrors = true)
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
                        ASTValidator.AddError(errorMessage);
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
