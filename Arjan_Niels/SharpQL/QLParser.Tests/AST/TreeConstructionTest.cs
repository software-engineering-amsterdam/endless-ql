using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST;
using QLParser.AST.Nodes;
using System;

namespace QLParser.Tests.AST
{
    [TestClass]
    public class TreeConstructionTest : QLTest
    {
        public Node AST { get; set; }

        [TestInitialize]
        public void Initialize()
        {
            AST = new FormNode(new Location(0, 0), "TestForm");
            var firstQuestion = new QuestionNode(new Location(0, 0), "Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode(new Location(0, 0), "Q2", "Do you like kittens?", QValueType.BOOLEAN);

            AST.AddNode(firstQuestion);
            AST.AddNode(secondQuestion);
        }

        [TestMethod]
        public void RootIsFormTest()
        {
            Assert.AreEqual(NodeType.FORM, AST.Type);
        }

        [TestMethod]
        public void FormNameTest()
        {
            var formNode = AST as FormNode;
            Assert.AreEqual("TestForm", formNode.FormName);
        }

        [TestMethod]
        public void AddNodeTest()
        {
            Assert.AreEqual(2, AST.Children.Count);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void AddNullNodeTest()
        {
            AST.AddNode(null);
        }

        [TestMethod]
        public void GitHubExampleTest()
        {
            FormNode node = QLParserHelper.Parse(FormExamples.GitHubExampleForm);
            Assert.IsNotNull(node);
        }
    }
}