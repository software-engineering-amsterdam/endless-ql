namespace Assignment1.Model.QLS.AST
{
    public interface IQLSASTVisitor
    {
        void Visit(StyleSheet styleSheet);
        void Visit(Page page);
        void Visit(Section section);
        void Visit(QuestionStyle questionStyle);
        void Visit(DefaultStyle defaultStyle);
    }
}
