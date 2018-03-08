using QLParser.AST.Nodes;

namespace QLParser.Analysis
{
    public interface IAnalyser
    {
        bool Analyse(Node node);
    }
}