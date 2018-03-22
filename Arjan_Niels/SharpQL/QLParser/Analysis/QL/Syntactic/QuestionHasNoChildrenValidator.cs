using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;

namespace QLParser.Analysis.QL.Syntactic
{
    public class QuestionHasNoChildrenValidator : IQLAnalyser, IQLVisitor
    {
        private readonly string errorMessage = "Questions can't have child nodes.";
        private bool isValid = false;

        public bool Analyse(QLNode node)
        {
            isValid = true;
            this.Visit(node);
            return isValid;
        }

        public void Visit(QLNode node)
        {
            VisitChildren(node);
        }

        public void Visit(QuestionNode node)
        {
            if (node.Children.Count != 0)
            {
                isValid = false;
                Analyser.AddMessage(errorMessage, MessageType.ERROR);
            }

            VisitChildren(node);
        }

        public void Visit(ComputedNode node)
        {
            VisitChildren(node);
        }

        public void Visit(FormNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ExpressionNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ConditionalNode node)
        {
            VisitChildren(node);
        }

        private void VisitChildren(QLNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }
    }
}