using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Semantic
{
    /// <summary>
    /// The TypeAnalyser walks over the tree and looks if all the variable type
    /// are in compliance with the language.
    /// </summary>
    public class VariableAnalyser : IAnalyser
    {
        /// <summary>
        /// This function will return true if it doesn't encounter any problems. 
        /// </summary>
        /// <param name="node"></param>
        /// <param name="logErrors"></param>
        /// <returns></returns>
        public bool Analyse(Node node, bool logErrors = true)
        {
            // Traverse tree to check the types.

            traverse(node);
            return true;
        }

        private bool traverse(Node parent, bool logErrors = true)
        {
            if (parent.Type == NodeType.QUESTION)
            {
                var questionNode = (QuestionNode)parent;
                if (!SymbolTable.Add(questionNode.ID, questionNode.ValueType) && logErrors)
                    Analyser.AddMessage(string.Format("Duplicate identifier: {0} {1}", questionNode.ID, questionNode.ValueType), MessageType.ERROR);
            }

            foreach (Node node in parent.Children)
                traverse(node);

            return false;
        }
    }
}