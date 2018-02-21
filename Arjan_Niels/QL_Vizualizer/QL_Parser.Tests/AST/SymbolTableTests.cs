using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;
using QL_Parser.AST.Nodes;
using System.Linq;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class SymbolTableTests
    {
        private FormNode SimpleForm;
        private readonly string _simpleFormRaw = "form SimpleForm { }";

        private FormNode OneVarForm;
        private readonly string _oneVarFormRaw = "form OneVar { " +
            "\"Have you bought a house in 2010?\"" +
            "       boughtAHouse: boolean" +
            "}";

        [TestCleanup]
        public void CleanUp() => SymbolTable.Reset();

        [TestInitialize]
        public void Initialize() => SymbolTable.Reset();

        [TestMethod]
        public void NoVariablesInSymbolTable()
        {
            SimpleForm = QLParserHelper.Parse(_simpleFormRaw);
            Assert.AreEqual(0, SymbolTable.Instance.TypeMap.Count);
        }

        [TestMethod]
        public void OneVariableInSymbolTable()
        {
            OneVarForm = QLParserHelper.Parse(_oneVarFormRaw);
            Assert.AreEqual(1, SymbolTable.Instance.TypeMap.Count);
        }

        [TestMethod]
        public void CheckNameOneVarInSymbolTable()
        {
            OneVarForm = QLParserHelper.Parse(_oneVarFormRaw);
            Assert.AreEqual("boughtAHouse", SymbolTable.Instance.TypeMap.Keys.ToArray()[0]);
        }

        [TestMethod]
        public void CheckTypeInSymbolTable()
        {
            OneVarForm = QLParserHelper.Parse(_oneVarFormRaw);
            Assert.AreEqual(QuestionType.BOOLEAN, SymbolTable.Instance.TypeMap["boughtAHouse"]);
        }
    }
}
