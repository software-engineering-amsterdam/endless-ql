using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;

namespace QLParser.Tests
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