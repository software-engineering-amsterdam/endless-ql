using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Parser.Tests
{
    [TestClass]
    public class QuestionTest
    {
        private readonly string formRaw = "form simpleForm { " +
            "   \"Have you sold a house in 2010?\"" +
            "       hasSoldAHouse: boolean" +
            "}";


        [TestMethod]
        public void CountQuestionsTest()
        {
            var form = QLParserHelper.Parse(formRaw);
            Assert.AreEqual(1, form.Sections.Count);
        }

        [TestMethod]
        public void QuestionTextTest()
        {
            var form = QLParserHelper.Parse(formRaw);
            var firstQuestion = form.Sections[0] as Question;
            Assert.AreEqual("\"Have you sold a house in 2010?\"", firstQuestion.Text);
        }

        [TestMethod]
        public void QuestionIDTest()
        {
            var form = QLParserHelper.Parse(formRaw);
            var firstQuestion = form.Sections[0] as Question;
            Assert.AreEqual("hasSoldAHouse", firstQuestion.ID);
        }

        [TestMethod]
        public void QuestionTypeTest()
        {
            var form = QLParserHelper.Parse(formRaw);
            var firstQuestion = form.Sections[0] as Question;
            Assert.AreEqual("boolean", firstQuestion.QType);
        }
    }
}
