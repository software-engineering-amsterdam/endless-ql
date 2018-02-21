using QL_Parser.AST.Nodes;

namespace QL_Parser.AST.Validators
{
    public interface IASTValidator
    {
        bool IsValid(Node node, bool logErrors = true);
    }
}
