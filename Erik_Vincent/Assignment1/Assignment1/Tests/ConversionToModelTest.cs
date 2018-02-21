using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Tests
{
    class ConversionToModelTest
    {
        public void RunTests()
        {
            TestSingleSimpleQuestion();

            Console.Read();
        }

        /// <summary>
        /// Tests the conversion to Question object of a single question in a form by checking if 
        /// the Id and Label match. The question does not contain an initialization
        /// of the value.
        /// </summary>
        public void TestSingleSimpleQuestion()
        {
            int desiredQuestionCount = 1;
            int questionCount = 0;
            QuestionForm form = TestHelper.ParseInputString(TestValues.SingleSimpleQForm);
            questionCount = form.Questions.Count;

            if (questionCount == desiredQuestionCount)
            {
                Question question = form.Questions.ElementAt(0).Value;
                Tuple<bool, List<string>> result = TestQuestion(question, TestValues.SimpleQId, TestValues.SimpleQLabel);
                TestHelper.LogToConsole(result.Item2);
                if (result.Item1)
                    TestHelper.LogToConsole("Test 1 simple question PASSED");
                else
                    TestHelper.LogToConsole("Test 1 simple question FAILED");
            }
            else
                TestHelper.LogQuestionCountFail("1 simple question", questionCount, desiredQuestionCount);
        }

        /// <summary>
        /// Test conversion to Question object with arithmetic expression.
        /// </summary>
        public void TestSingleArithmeticQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with logical expression.
        /// </summary>
        public void TestSingleLogicalQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with comparison expression.
        /// </summary>
        public void TestSingleComparisonQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with arithmetic expression.
        /// </summary>
        public void TestCombinedArithmeticArithmeticQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with arithmetic expression.
        /// </summary>
        public void TestCombinedArithmeticLogicalQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with arithmetic expression.
        /// </summary>
        public void TestCombinedArithmeticComparisonQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with logical expression (redundant?)
        /// </summary>
        public void TestCombinedLogicalArithmeticQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with logical expression.
        /// </summary>
        public void TestCombinedLogicalLogicalQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with logical expression.
        /// </summary>
        public void TestCombinedLogicalComparisonQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question object with comparison expression.
        /// </summary>
        public void TestCombinedComparisonQuestion()
        {

        }

        /// <summary>
        /// Test conversion to Question objects without value initialization where the order
        /// of the questions is also checked.
        /// </summary>
        public void TestMultipleSimpleQuestions()
        {
            int desiredQuestionCount = 3;
            int questionCount = 0;
            QuestionForm form = TestHelper.ParseInputString(TestValues.SingleSimpleQForm);
            questionCount = form.Questions.Count;

            if (questionCount == desiredQuestionCount)
            {
                form.Questions.ToList();
            } else
            {
                TestHelper.LogQuestionCountFail("multiple simple questions", questionCount, desiredQuestionCount);
            }
        }

        /// <summary>
        /// Test conversion to Question objects with value initialization where the order 
        /// of the questions is also checked.
        /// </summary>
        public void TestMultipleComplexQuestion()
        {

        }

        /// <summary>
        /// Test conversion to If object and checking the then branch.
        /// </summary>
        public void TestSimpleIfStatement()
        {

        }

        /// <summary>
        /// Test conversion to IfElse object and checking the then and else branch
        /// </summary>
        public void TestSimpleIfElseStatement()
        {

        }

        /// <summary>
        /// Test conversion to If object that contains an If object and a Question object
        /// in the then branch
        /// </summary>
        public void TestNestedIfStatement()
        {

        }

        /// <summary>
        /// Test conversion to If object that contains an If object and a Question object
        /// in the then and else branch
        /// </summary>
        public void TestNestedIfElseStatement()
        {

        }

        /// <summary>
        /// Checks if the Id and Label property of the question match the given Id and Label
        /// </summary>
        /// <param name="question">The question to check</param>
        /// <param name="desiredId">The desired Id</param>
        /// <param name="desiredLabel">The desired Label</param>
        /// <returns></returns>
        public Tuple<bool, List<string>> TestQuestion(Question question, string desiredId, string desiredLabel)
        {
            bool success = true;
            List<string> messages = new List<string>();
            
            if (question.Id != desiredId)
            {
                messages.Add("Test question trace: id " + question.Id + " != " + desiredId);
                success = false;
            }
            if (question.Label != desiredLabel)
            {
                messages.Add("Test question trace: label " + question.Label + " != " + desiredLabel);
                success = false;
            }

            string result = success ? "PASSED" : "FAILED";
            messages.Add("Test question [" + question.ToString() + "]: " +  result);// + " with input: ");
            messages.Reverse();

            return Tuple.Create(success, messages);
        }
    }
}
