using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;

namespace QL_Parser.Tests
{
    public abstract class QLTest
    {
        [TestCleanup]
        public void CleanUp()
        {
            SymbolTable.Reset();
            Analyser.Reset();
        }
    }
}