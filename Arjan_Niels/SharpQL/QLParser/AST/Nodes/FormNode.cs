namespace QLParser.AST.Nodes
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
            return base.ToString() + string.Format("\t\t{0}", FormName);
        }
    }
}