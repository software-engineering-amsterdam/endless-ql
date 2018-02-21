using System;
using Assignment1;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Assignment1Tests
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestCreateForm()
        {
            QuestionForm testF = new QuestionForm(TestFormId);

            Assert.AreEqual(testF.Id, TestFormId);
        }

        [TestMethod]
        public void TestCreateQuestion()
        {
            Question testQ = new Question(TestQuestionId, TestQuestionLabel);

            Assert.AreEqual(testQ.Id, TestQuestionId);
            Assert.AreEqual(testQ.Label, TestQuestionLabel);
        }

        [TestMethod]
        public void TestAddQuestionToForm()
        {
            QuestionForm testF = new QuestionForm(TestFormId);
            Question testQ = new Question(TestQuestionId, TestQuestionLabel);

            Assert.AreEqual(testF.Content.Count, 0);
            testF.AddQuestion(testQ);
            Assert.IsNotNull(testF.Content);
            Assert.AreEqual(testF.Content.Count, 1);
            Assert.AreSame(testF.Content[0], testQ);
        }

        [TestMethod]
        public void TestExpression()
        {

        }

        [TestMethod]
        public void TestIfStatement()
        {

        }

        private static string TestFormId = "testForm";
        private static string TestQuestionId = "testQuestion";
        private static string TestQuestionLabel = "Programming, ****, do you speak it?";
    }
}
