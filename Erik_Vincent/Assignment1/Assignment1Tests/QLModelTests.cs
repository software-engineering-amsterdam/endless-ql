using System;
using System.Collections.Generic;
using Assignment1.Model;
using Microsoft.CSharp.RuntimeBinder;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Assignment1Tests
{
    [TestClass]
    public class QLModelTests
    {
        [TestMethod]
        public void TestCreateForm()
        {
            QuestionForm testF = new QuestionForm(TestFormId, new List<Question>());

            Assert.AreEqual(TestFormId, testF.Id);
        }

        [TestMethod]
        public void TestCreateQuestion()
        {
            Question testQ = new QuestionBool(TestQuestionId, TestQuestionLabel);

            Assert.AreEqual(TestQuestionId, testQ.Id);
            Assert.AreEqual(TestQuestionLabel, testQ.Label);
        }

        [TestMethod]
        public void TestAddQuestionToForm()
        {
            Question testQ = new QuestionBool(TestQuestionId, TestQuestionLabel);
            List<Question> questions = new List<Question>() { testQ };
            QuestionForm testF = new QuestionForm(TestFormId, questions);

            Assert.IsNotNull(testF.Questions);
            Assert.AreEqual(1, testF.Questions.Count);
            Assert.AreSame(testQ, testF.Questions[0]);
        }

        [TestMethod]
        public void TestAdditionExpression()
        {
            int leftInt = 1;
            int rightInt = 2;
            Expression leftExpr = new ExpressionValue(leftInt);
            Expression rightExpr = new ExpressionValue(rightInt);
            ExpressionAdd addExpr = new ExpressionAdd(leftExpr, rightExpr);
            
            Assert.AreEqual(leftInt, leftExpr.Evaluate());
            Assert.AreEqual(rightInt, rightExpr.Evaluate());
            Assert.AreEqual(leftInt + rightInt, addExpr.Evaluate());
        }

        [TestMethod]
        public void TestSubtractionExpression()
        {
            int leftInt = 4;
            int rightInt = 2;
            Expression leftExpr = new ExpressionValue(leftInt);
            Expression rightExpr = new ExpressionValue(rightInt);
            ExpressionSub addExpr = new ExpressionSub(leftExpr, rightExpr);
            
            Assert.AreEqual(leftInt, leftExpr.Evaluate());
            Assert.AreEqual(rightInt, rightExpr.Evaluate());
            Assert.AreEqual(leftInt - rightInt, addExpr.Evaluate());
        }

        [TestMethod]
        public void TestMultiplyExpression()
        {
            int leftInt = 1;
            int rightInt = 2;
            Expression leftExpr = new ExpressionValue(leftInt);
            Expression rightExpr = new ExpressionValue(rightInt);
            ExpressionMult addExpr = new ExpressionMult(leftExpr, rightExpr);
            
            Assert.AreEqual(leftInt, leftExpr.Evaluate());
            Assert.AreEqual(rightInt, rightExpr.Evaluate());
            Assert.AreEqual(leftInt * rightInt, addExpr.Evaluate());
        }

        [TestMethod]
        public void TestDivisionExpression()
        {
            int leftInt = 4;
            int rightInt = 2;
            Expression leftExpr = new ExpressionValue(leftInt);
            Expression rightExpr = new ExpressionValue(rightInt);
            ExpressionDiv addExpr = new ExpressionDiv(leftExpr, rightExpr);
            
            Assert.AreEqual(leftInt, leftExpr.Evaluate());
            Assert.AreEqual(rightInt, rightExpr.Evaluate());
            Assert.AreEqual(leftInt / rightInt, addExpr.Evaluate());
        }

        [TestMethod]
        public void TestAndExpressionBothTrue()
        {
            bool leftBool = true;
            bool rightBool = true;
            Expression leftExpr = new ExpressionValue(leftBool);
            Expression rightExpr = new ExpressionValue(rightBool);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);
            
            Assert.AreEqual(leftBool, leftExpr.Evaluate());
            Assert.AreEqual(rightBool, rightExpr.Evaluate());
            Assert.AreEqual(leftBool && rightBool, andExpr.Evaluate());
        }

        [TestMethod]
        public void TestAndExpressionLeftFalse()
        {
            bool leftBool = false;
            bool rightBool = true;
            Expression leftExpr = new ExpressionValue(leftBool);
            Expression rightExpr = new ExpressionValue(rightBool);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);
            
            Assert.AreEqual(leftBool, leftExpr.Evaluate());
            Assert.AreEqual(rightBool, rightExpr.Evaluate());
            Assert.AreEqual(leftBool && rightBool, andExpr.Evaluate());
        }

        [TestMethod]
        public void TestOrExpressionRightFalse()
        {
            bool leftBool = true;
            bool rightBool = false;
            Expression leftExpr = new ExpressionValue(leftBool);
            Expression rightExpr = new ExpressionValue(rightBool);
            ExpressionOr orExpr = new ExpressionOr(leftExpr, rightExpr);
            
            Assert.AreEqual(leftBool, leftExpr.Evaluate());
            Assert.AreEqual(rightBool, rightExpr.Evaluate());
            Assert.AreEqual(leftBool || rightBool, orExpr.Evaluate());
        }

        [TestMethod]
        public void TestOrExpressionBothFalse()
        {
            bool leftBool = false;
            bool rightBool = false;
            Expression leftExpr = new ExpressionValue(leftBool);
            Expression rightExpr = new ExpressionValue(rightBool);
            ExpressionOr orExpr = new ExpressionOr(leftExpr, rightExpr);

            Assert.AreEqual(leftBool, leftExpr.Evaluate());
            Assert.AreEqual(rightBool, rightExpr.Evaluate());
            Assert.AreEqual(leftBool || rightBool, orExpr.Evaluate());
        }

        [TestMethod]
        //[ExpectedException(typeof(RuntimeBinderException))]
        public void TestInvalidLogicalExpression()
        {
            int leftInt = 1;
            int rightInt = 2;
            Expression leftExpr = new ExpressionValue(leftInt);
            Expression rightExpr = new ExpressionValue(rightInt);
            ExpressionAnd andExpr = new ExpressionAnd(leftExpr, rightExpr);

            //Assert.ThrowsException(andExpr.Evaluate());
        }

        [TestMethod]
        public void TestSmallerThanExpression()
        {
            int leftInt = 1;
            int rightInt = 2;
            Expression leftExpr = new ExpressionValue(leftInt);
            Expression rightExpr = new ExpressionValue(rightInt);
            ExpressionLess lessExpr = new ExpressionLess(leftExpr, rightExpr);

            Assert.AreEqual(leftInt < rightInt, lessExpr.Evaluate());
        }

        private static string TestFormId = "testForm";
        private static string TestQuestionId = "testQuestion";
        private static string TestQuestionLabel = "Programming, ****, do you speak it?";
    }
}
