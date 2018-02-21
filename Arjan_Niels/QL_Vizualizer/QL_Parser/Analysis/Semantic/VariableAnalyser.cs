using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Semantic
{
    /// <summary>
    /// The TypeAnalyser walks over the tree and looks if all the variable type
    /// are in compliance with the language.
    /// </summary>
    public class VariableAnalyser : IAnalyser
    {
        public bool Analyse(Node node, bool logErrors = true)
        {
            return true;
            // throw new System.NotImplementedException();
        }
    }
}
