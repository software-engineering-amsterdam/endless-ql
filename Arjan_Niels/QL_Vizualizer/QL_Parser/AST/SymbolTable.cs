using QL_Parser.AST.Nodes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Parser.AST
{
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
            this.TypeMap = new Dictionary<string, NodeType>();
        }
        #endregion

        public Dictionary<string, NodeType> TypeMap { get; set; }

        public NodeType GetType(string id)
        {
            if (TypeMap.ContainsKey(id))
                return TypeMap[id];

            throw new KeyNotFoundException(string.Format("Unknown variable {0}", id));
        }

        public void Add(string id, NodeType type)
        {
            if (!TypeMap.ContainsKey(id))
                TypeMap.Add(id, type);
        }
    }
}
