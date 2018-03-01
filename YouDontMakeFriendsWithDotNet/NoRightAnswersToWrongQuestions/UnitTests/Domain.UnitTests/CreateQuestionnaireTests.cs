using System;
using System.Collections.Generic;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;

namespace UnitTests.Domain.UnitTests
{
    [TestFixture]
    public class CreateQuestionnaireTests
    {
        private IServiceProvider m_serviceProvider;
        private IDomainItemLocator m_domainItemLocator;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new DomainLogicModule());
            m_serviceProvider = services.BuildServiceProvider();
            m_domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
        }

        [TearDown]
        public void Cleanup()
        {
            //To Do: this is a hack, should fix lifetime of service (possibly)
            var registry = m_serviceProvider.GetService<IDomainItemRegistry>();
            registry.Nuke();
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
            CreateForm(@"form MyForm {}");
            var createdForm = m_domainItemLocator
                .GetAll<IRootNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdForm);
            Assert.AreEqual(expected: "MyForm", actual: createdForm.QuestionnaireName);
        }

        private void CreateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var domainItemId = questionnaireCreator.Create(validText);
            Assert.IsNotNull(domainItemId);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.CommentCases))]
        public void WhenGivenComments_ReturnsDomainObjects(string validText, string expectedName)
        {
            CreateForm(validText);
            Assert.AreEqual(expected: expectedName, actual: GetForm().QuestionnaireName);
        }

        private IRootNode GetForm()
        {
            var createdForm = m_domainItemLocator
                .GetAll<IRootNode>()
                .FirstOrDefault();
            Assert.IsNotNull(createdForm);
            return createdForm;
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.ValidNameCases))]
        public void WhenGivenValidIdentifier_NamesTheFormCorrectly(string validText, string expectedName)
        {
            CreateForm(validText);
            Assert.AreEqual(expected: expectedName, actual: GetForm().QuestionnaireName);
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
            CreateForm(validText);
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var question = domainItemLocator
                .GetAll<IQuestionNode>()
                .FirstOrDefault();
            
            Assert.IsNotNull(question);
            Assert.AreEqual(expected: questionText, actual: question.QuestionText);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.MultipleQuestionCases))]
        public void WhenGivenMultipleQuestions_CorrectNumberOfQuestions(string validText, int questionCount)
        {
            CreateForm(validText);
            Assert.AreEqual(expected: questionCount, actual: m_domainItemLocator.GetAll<IQuestionNode>().Count());
        }


        [TestCaseSource(typeof(TestData), nameof(TestData.TypeCases))]
        public void WhenQuestionsHasType_CorrectTypeOnQuestions(string validText, Type expectedType)
        {
            CreateForm(validText);
            Assert.AreEqual(expected: expectedType, actual: GetQuestion().QuestionType);
        }

        private IQuestionNode GetQuestion()
        {
            var question = m_domainItemLocator.GetAll<IQuestionNode>().FirstOrDefault();
            Assert.IsNotNull(question);
            return question;
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.ConditionalStatementCases))]
        public void WhenFormHasConditionalStatement_CorrectNumberOfConditionalCasesExist(string validText, int conditionCount)
        {
            CreateForm(validText);
            var actualCount = m_domainItemLocator.GetAll<IConditionalStatementNode>().Count();
            Assert.AreEqual(expected: conditionCount, actual: actualCount);
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.ElseStatementCases))]
        public void WhenFormHasElseConditional_CorrectNumberOfQuestionsExist(string validText, int conditionCount)
        {
            CreateForm(validText);
            var actualCount = m_domainItemLocator.GetAll<IQuestionNode>().Count();
            Assert.AreEqual(expected: conditionCount, actual: actualCount);
        }
        
        //[TestCaseSource(typeof(TestData), nameof(TestData.QuestionDuplicatesCases))]
        //public void WhenDuplicateQuestionId_ThrowsAnError(string invalidText, string duplicateName)
        //{
        //    var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
        //    try
        //    {
        //        questionnaireCreator.Create(invalidText);
        //    }
        //    catch (QlParserException exception)
        //    {
        //        Assert.IsTrue(exception.ParseErrorDetails.Contains(duplicateName));
        //        return;
        //    }

        //    Assert.Fail("Should have thrown an exception");
        //}

        //[TestCaseSource(typeof(TestData), nameof(TestData.NonBooleanConditional))]
        //public void WhenANonBooleanQuestionIsUsedInAConditional_ThrowsAnError(string invalidText, string nonBooleanName)
        //{
        //    var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
        //    try
        //    {
        //        questionnaireCreator.Create(invalidText);
        //    }
        //    catch (QlParserException exception)
        //    {
        //        Assert.IsTrue(exception.ParseErrorDetails.Contains(nonBooleanName));
        //        return;
        //    }

        //    Assert.Fail("Should have thrown an exception");
        //}


        [TestCaseSource(typeof(TestData), nameof(TestData.BooleanConditional))]
        public void WhenBooleanQuestionUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
        {
            CreateForm(validText);
            var conditionNames = m_domainItemLocator
                .GetAll<IConditionalStatementNode>()
                .Select(x => x.ConditionDefinition)
                .ToList();

            foreach (var expectedName in booleanNames)
            {
                Assert.Contains(expected: expectedName, actual: conditionNames);
            }
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.CalculationQuestionCases))]
        public void WhenQuestionIsCalculation_ParsesCorrectly(
            string validText,
            IEnumerable<string> expectedDefinitions,
            IEnumerable<decimal> expectedNumbers,
            IEnumerable<string> expectedVariableNames)
        {
            CreateForm(validText);
            //var actualDefinitions = m_domainItemLocator
            //    .GetAll<ICalculationNode>()
            //    .Select(x => x.CalculationDefinition)
            //    .ToList();

            //foreach (var expectedDefinition in expectedDefinitions)
            //{
            //    Assert.Contains(expected: expectedDefinition, actual: actualDefinitions);
            //}

            //var actualNumbers = m_domainItemLocator
            //    .GetAll<INumberNode>()
            //    .Select(x => x.Value)
            //    .ToList();

            //foreach (var expectedNumber in expectedNumbers)
            //{
            //    Assert.Contains(expected: expectedNumber, actual: actualNumbers);
            //}

            //var actualVariableNames = m_domainItemLocator
            //    .GetAll<IVariableNode>()
            //    .Select(x => x.VariableName)
            //    .ToList();

            //foreach (var expectedVariableName in expectedVariableNames)
            //{
            //    Assert.Contains(expected: expectedVariableName, actual: actualVariableNames);
            //}
        }

        [TestCaseSource(typeof(TestData), nameof(TestData.ComparisonConditional))]
        public void WhenComparisonUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
        {
            CreateForm(validText);
            var questionNames = m_domainItemLocator
                .GetAll<IConditionalStatementNode>()
                .Select(x => x.ConditionDefinition)
                .ToList();

            foreach (var expectedName in booleanNames)
            {
                Assert.Contains(expected: expectedName, actual: questionNames);
            }
        }
    }
}