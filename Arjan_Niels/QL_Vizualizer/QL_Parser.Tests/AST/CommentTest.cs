using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class CommentTest : QLTest
    {
        private FormNode _commentForm;
        private readonly string _simpleFormWithCommentsRaw = "" +
            "// Comment \r\n" +
            "form TestForm { // Second comment \r\n" +
            "   // Another line with comments " +
            "}";

        [TestInitialize]
        public void Initialize()
        {
            _commentForm = QLParserHelper.Parse(_simpleFormWithCommentsRaw);
        }

        [TestMethod]
        public void IgnoreCommentsTest()
        {
            // Make sure everything has been parsed well.
            Assert.AreEqual("TestForm", _commentForm.FormName);
        }
    }
}