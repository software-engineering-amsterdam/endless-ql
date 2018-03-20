using QLParser.AST.Nodes;
using System.Collections.Generic;

namespace QLParser.Analysis
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

        /// <summary>
        /// This function adds a variable to the SymbolTable. In case of an error a message will
        /// be added to the analyser.
        /// </summary>
        /// <param name="id"></param>
        /// <param name="type"></param>
        public static bool Add(string id, QValueType type)
        {
            if (!Instance.TypeMap.ContainsKey(id))
            {
                Instance.TypeMap.Add(id, type);
                return true;
            }

            return false;
        }

        public static QValueType Get(string identifier)
        {
            if (Instance.TypeMap.ContainsKey(identifier))
                return Instance.TypeMap[identifier];
            else
                return QValueType.UNKNOWN;
        }

        /// <summary>
        /// This function clears the TypeMap with all its values and should only
        /// be used for testing purposes.
        /// </summary>
        public static void Reset()
        {
            Instance.TypeMap.Clear();
        }
    }
}