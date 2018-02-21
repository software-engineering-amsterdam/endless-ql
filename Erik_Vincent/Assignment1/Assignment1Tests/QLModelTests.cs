using System;
using System.Collections.Generic;
using Assignment1;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Assignment1Tests
{
    [TestClass]
    public class QLModelTests
    {
        [TestMethod]
        public void TestCreateForm()
        {
            QuestionForm testF = new QuestionForm(TestFormId, new List<Content>());

            Assert.AreEqual(TestFormId, testF.Id);
        }

        [TestMethod]
        public void TestCreateQuestion()
        {
            Question testQ = new Question(TestQuestionId, TestQuestionLabel);

            Assert.AreEqual(TestQuestionId, testQ.Id);
            Assert.AreEqual(TestQuestionLabel, testQ.Label);
        }

        [TestMethod]
        public void TestAddQuestionToForm()
        {
            Question testQ = new Question(TestQuestionId, TestQuestionLabel);
            List<Content> contentList = new List<Content>() { testQ };
            QuestionForm testF = new QuestionForm(TestFormId, contentList);

            Assert.IsNotNull(testF.Content);
            Assert.AreEqual(1, testF.Content.Count);
            Assert.AreSame(testQ, testF.Content[0]);
        }

        [TestMethod]
        public void TestBinaryExpression()
        {
            // Create expression with + operator

            // Run evaluator
            
            // Left and right operands should be integers

            // Sum should be the sum of the left and right operands
        }

        [TestMethod]
        public void TestLogicalExpressionBothTrue()
        {
            // Create epression with && operator with left and right as true

            // Run evaluator
            
            // Left and right operands should be boolean

            // Result should be true
        }

        [TestMethod]
        public void TestLogicalExpressionLeftFalse()
        {
            // Create epression with && operator with left as false and right as true

            // Run evaluator
            
            // Left and right operands should be boolean

            // Result should be false
        }

        [TestMethod]
        public void TestLogicalExpressionRightFalse()
        {
            // Create epression with && operator with left as true and right as false

            // Run evaluator
            
            // Left and right operands should be boolean

            // Result should be false
        }

        [TestMethod]
        public void TestLogicalExpressionInvalidOperand()
        {
            // Create epression with && operator with left as true and right as integer

            // Run evaluator

            // Error should be thrown
        }

        [TestMethod]
        public void TestComparisonExpression()
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
