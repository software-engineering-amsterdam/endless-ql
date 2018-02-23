using System;
using System.Collections.Generic;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure.API;

namespace UnitTests.Domain.UnitTests
{
    [TestFixture]
    public class CreateQuestionnaireTests
    {
        private IServiceProvider m_serviceProvider;
        private static readonly string NewLine = Environment.NewLine;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new DomainLogicModule());
            m_serviceProvider = services.BuildServiceProvider();
        }

        [Test]
        public void WhenGivenMalformedLexableDefinition_ThrowsParserException()
        {
            try
            {
                var questionnaire = m_serviceProvider.GetService<IQuestionnaireCreator>();
                questionnaire.Create("gobbldygook");
            }
            catch (QlParserException exception)
            {
                Assert.AreEqual(
                    expected: @"Parse failed. See inner exception for details.",
                    actual: exception.Message);

                Assert.AreEqual(
                    expected: @"'gobbldygook' was not recognized at line 1, position 0, giving the following error: missing 'form' at 'gobbldygook' ",
                    actual: exception.ParseErrorDetails);
                
                return;
            }

            Assert.Fail("Should have thrown an QlParserException exception");
        }
        
        [Test]
        public void WhenGivenMalformedNonLexableDefinition_ThrowsParserException()
        {
            try
            {
                var questionnaire = m_serviceProvider.GetService<IQuestionnaireCreator>();
                questionnaire.Create("#$%@$ 09090");
            }
            catch (QlParserException exception)
            {
                Assert.AreEqual(
                    expected: @"Parse failed. See inner exception for details.",
                    actual: exception.Message);
                
                Assert.AreEqual(
                    expected: @"Lexing of #$%@$ 09090 failed at line 1, position 0, giving the following error: token recognition error at: '#' ",
                    actual: exception.ParseErrorDetails);
            
                return;
            }

            Assert.Fail("Should have thrown an QlParserException exception");
        }

        [Test]
        public void WhenGivenWellFormedDefinition_ReturnsDomainObjects()
        {
            var createdForm = CreateForm(@"form MyForm {}");
            Assert.AreEqual(expected: "MyForm", actual: createdForm.FormName);
        }

        private IQuestionnaireAst CreateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var domainItemId = questionnaireCreator.Create(validText);
            var createdForm = domainItemLocator.Get<IQuestionnaireAst>(domainItemId);
            Assert.IsNotNull(domainItemId);
            return createdForm;
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.CommentCases))]
        public void WhenGivenComments_ReturnsDomainObjects(string validText, string expectedName)
        {
            var createdForm = CreateForm(validText);
            Assert.AreEqual(expected: expectedName, actual: createdForm.FormName);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.ValidNameCases))]
        public void WhenGivenValidIdentifier_NamesTheFormCorrectly(string validText, string expectedName)
        {
            var createdForm = CreateForm(validText);
            Assert.AreEqual(expected: expectedName, actual: createdForm.FormName);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.InvalidNameCases))]
        public void WhenGivenInvalidIdentifier_ThrowsLexingError(string invalidText, string invalidName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            try
            {
                questionnaireCreator.Create(invalidText);
            }
            catch (QlParserException exception)
            {
                Assert.IsTrue(exception.ParseErrorDetails.Contains(invalidName));

                return;
            }

            Assert.Fail("Should have thrown an QlParserException exception");
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.QuestionCases))]
        public void WhenGivenValidQuestion_NameAndTextCorrect(string validText, string questionId, string questionText)
        {
            var createdForm = CreateForm(validText);
            var question = createdForm.Statements.OfType<IQuestionAst>().FirstOrDefault();
            Assert.AreEqual(expected: questionId, actual: question.Name);
            Assert.AreEqual(expected: questionText, actual: question.Text);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.MultipleQuestionCases))]
        public void WhenGivenMultipleQuestions_CorrectNumberOfQuestions(string validText, int questionCount)
        {
            var createdForm = CreateForm(validText);
            Assert.AreEqual(expected: questionCount, actual: createdForm.Statements.Count);
        }


        [TestCaseSource(typeof(TestData), nameof(TestData.TypeCases))]
        public void WhenQuestionsHasType_CorrectTypeOnQuestions(string validText, Type expectedType)
        {
            var createdForm = CreateForm(validText);
            var actualType = createdForm.Statements.OfType<IQuestionAst>().FirstOrDefault()?.Type;
            Assert.AreEqual(expected: expectedType, actual: actualType);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.ConditionalStatementCases))]
        public void WhenFormHasConditionalStatement_CorrectNumberOfConditionalCasesExist(string validText, int conditionCount)
        {
            var createdForm = CreateForm(validText);
            var actualCount = createdForm.Statements.Flatten().OfType<IConditionalAst>().Count();
            Assert.AreEqual(expected: conditionCount, actual: actualCount);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.QuestionDuplicatesCases))]
        public void WhenDuplicateQuestionId_ThrowsAnError(string invalidText, string duplicateName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            try
            {
                questionnaireCreator.Create(invalidText);
            }
            catch (QlParserException exception)
            {
                Assert.IsTrue(exception.ParseErrorDetails.Contains(duplicateName));
                return;
            }

            Assert.Fail("Should have thrown an exception");
        }
        
        [TestCaseSource(typeof(TestData), nameof(TestData.NonBooleanConditional))]
        public void WhenANonBooleanQuestionIsUsedInAConditional_ThrowsAnError(string invalidText, string nonBooleanName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            try
            {
                questionnaireCreator.Create(invalidText);
            }
            catch (QlParserException exception)
            {
                Assert.IsTrue(exception.ParseErrorDetails.Contains(nonBooleanName));
                return;
            }

            Assert.Fail("Should have thrown an exception");
        }

        
        [TestCaseSource(typeof(TestData), nameof(TestData.BooleanConditional))]
        public void WhenBooleanQuestionUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
        {
            var createdForm = CreateForm(validText);
            var questionNames = createdForm
                .Statements
                .Flatten()
                .OfType<IConditionalAst>()
                .Select(x => x.QuestionName)
                .ToList();

            foreach (var expectedName in booleanNames)
            {
                Assert.Contains(expected: expectedName, actual: questionNames);
            }
        }
        
        [TestCaseSource(typeof(TestData), nameof(TestData.ComparisonConditional))]
        public void WhenComparisonUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
        {
            var createdForm = CreateForm(validText);
            var questionNames = createdForm
                .Statements
                .Flatten()
                .OfType<IConditionalAst>()
                .Select(x => x.QuestionName)
                .ToList();

            foreach (var expectedName in booleanNames)
            {
                Assert.Contains(expected: expectedName, actual: questionNames);
            }
        }
    }

    public static class TestHelperExtensions
    {
        public static IEnumerable<IAstNode> Flatten(this IEnumerable<IAstNode> e)
        {
            return e.SelectMany(c => c.Statements.Flatten()).Concat(e);
        }
    }
}