using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using System;

namespace QL_Parser.Tests
{
    [TestClass]
    public class FormNameTest
    {
        private readonly string _simpleForm = "form SimpleForm { }";
        private readonly string _incorrectSimpleForm = "if SimpleForm { }";

        [TestMethod]
        public void SimpleFormNameTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleForm);
            Assert.AreEqual("SimpleForm", form.FormName);
        }

        [TestMethod]
        [ExpectedException(typeof(NullReferenceException))]
        public void IncorrectFormSyntexTest()
        {
            FormNode form = QLParserHelper.Parse(_incorrectSimpleForm);
        }
    }
}
