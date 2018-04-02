using QLParser.AST.QL;
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
                return QValueType.Unknown;
        }

        public static void Reset()
        {
            Instance.TypeMap.Clear();
        }
    }
}