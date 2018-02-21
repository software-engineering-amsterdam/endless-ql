using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis
{
    public interface IAnalyser
    {
        bool Analyse(Node node, bool logErrors = true);
    }
}
