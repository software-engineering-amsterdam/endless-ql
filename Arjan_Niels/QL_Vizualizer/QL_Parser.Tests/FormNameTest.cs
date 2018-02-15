using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Models;

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
            Form form = QLParserHelper.Parse(_simpleForm);
            Assert.AreEqual("SimpleForm", form.Name);
        }

        [TestMethod]
        [ExpectedException(typeof(NullReferenceException))]
        public void IncorrectFormSyntexTest()
        {
            Form form = QLParserHelper.Parse(_incorrectSimpleForm);
        }
    }
}
