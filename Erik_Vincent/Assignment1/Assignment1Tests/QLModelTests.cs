using System;
using System.Collections.Generic;
using Assignment1;
using Microsoft.CSharp;
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
            int leftInt = 1;
            int rightInt = 2;
            Expression leftExpr = new Expression(leftInt);
            Expression rightExpr = new Expression(rightInt);
            ExpressionAdd addExpr = new ExpressionAdd(leftExpr, rightExpr);

            // Left and right operands should be integers
            //Assert.AreEqual(leftInt, leftExpr.Evaluate());
            //Assert.AreEqual(rightInt, rightExpr.Evaluate());

            // Sum should be the sum of the left and right operands
            //Assert.AreEqual(leftInt + rightInt, addExpr.Evaluate());
        }

        [TestMethod]
        public void TestLogicalExpressionBothTrue()
        {
            // Create epression with && operator with left and right as true
            bool leftBool = true;
            bool rightBool = true;
            Expression leftExpr = new Expression(leftBool);
            Expression rightExpr = new Expression(rightBool);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);

            // Run evaluator
            
            // Left and right operands should be boolean

            // Result should be true
        }

        [TestMethod]
        public void TestLogicalExpressionLeftFalse()
        {
            // Create epression with && operator with left as false and right as true
            bool leftBool = false;
            bool rightBool = true;
            Expression leftExpr = new Expression(leftBool);
            Expression rightExpr = new Expression(rightBool);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);

            // Run evaluator

            // Left and right operands should be boolean

            // Result should be false
        }

        [TestMethod]
        public void TestLogicalExpressionRightFalse()
        {
            // Create epression with && operator with left as true and right as false
            bool leftBool = true;
            bool rightBool = false;
            Expression leftExpr = new Expression(leftBool);
            Expression rightExpr = new Expression(rightBool);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);

            // Run evaluator

            // Left and right operands should be boolean

            // Result should be false
        }

        [TestMethod]
        public void TestLogicalExpressionInvalidOperand()
        {
            // Create epression with && operator with left as true and right as integer
            int leftInt = 1;
            int rightInt = 2;
            Expression leftExpr = new Expression(leftInt);
            Expression rightExpr = new Expression(rightInt);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);

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
