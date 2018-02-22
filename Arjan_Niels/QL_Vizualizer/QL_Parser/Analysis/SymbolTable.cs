using QL_Parser.AST.Nodes;
using System.Collections.Generic;

namespace QL_Parser.Analysis
{
    /// <summary>
    /// The SymbolTable keeps track of all the variables in the parse tree.
    /// </summary>
    public class SymbolTable
    {
        #region Singleton
        private static SymbolTable _instance;
        public static SymbolTable Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new SymbolTable();

                return _instance;
            }
        }

        private SymbolTable()
        {
            this.TypeMap = new Dictionary<string, QValueType>();
        }
        #endregion

        public Dictionary<string, QValueType> TypeMap { get; set; }

        public QValueType GetType(string id)
        {
            if (TypeMap.ContainsKey(id))
                return TypeMap[id];

            throw new KeyNotFoundException(string.Format("Unknown variable {0}", id));
        }

        public static void Add(string id, QValueType type)
        {
            if (!Instance.TypeMap.ContainsKey(id))
                Instance.TypeMap.Add(id, type);
        }

        /// <summary>
        /// This function clears the TypeMap with all its values and should only
        /// be used for testing purposes.
        /// </summary>
        public static void Reset()
        {
            Instance.TypeMap.Clear();
        }

        public static QValueType Get(string identifier)
        {
            return Instance.TypeMap[identifier];
        }
    }
}