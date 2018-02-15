namespace QL_Parser.AST.Nodes
{
    public class FormNode : Node
    {
        public string FormName { get; set; }

        public FormNode(string formName) : base(NodeType.FORM)
        {
            this.FormName = formName;
        }
    }
}
