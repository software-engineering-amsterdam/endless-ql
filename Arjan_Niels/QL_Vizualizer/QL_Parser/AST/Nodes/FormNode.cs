namespace QL_Parser.AST.Nodes
{
    public class FormNode : Node
    {
        public string FormName { get; set; }

        public FormNode(string formName) : base(NodeType.FORM)
        {
            this.FormName = formName;
        }

        public override string ToString()
        {
            return base.ToString() + string.Format("\t\tThis form is called {0}", FormName);
        }
    }
}
