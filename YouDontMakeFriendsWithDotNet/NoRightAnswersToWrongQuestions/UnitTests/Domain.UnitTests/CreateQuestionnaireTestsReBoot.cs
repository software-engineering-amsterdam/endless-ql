using System;
using System.Collections.Generic;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;
using IBooleanVariableNode = QuestionaireDomain.Entities.API.AstNodes.Boolean.IVariableNode;
using ICalculationVariableNode = QuestionaireDomain.Entities.API.AstNodes.Calculation.IVariableNode;


namespace UnitTests.Domain.UnitTests
{
    [TestFixture]
    public class CreateQuestionnaireTests
    {
        #region reboot done

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
            var constraint = Is.TypeOf<QlParserException>()
                .And
                .Property(nameof(QlParserException.ParseErrorDetails))
                .EqualTo(
                    @"'gobbldygook' was not recognized at line 1, position 0, giving the following error: missing 'form' at 'gobbldygook' ")
                .And
                .Message
                .EqualTo(@"Parse failed. See inner exception for details.");

            Assert.Throws(constraint, () => CreateForm(@"gobbldygook"));
        }

        private void CreateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider
                .GetService<IQuestionnaireCreator>();

            var domainItemId = questionnaireCreator.
                Create(validText);

            Assert.IsNotNull(domainItemId);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.GoodFormCases))]
        public void WhenGivenWellFormedDefinition_ReturnsAFormObjects(
            string validDescription,
            string expectedId)
        {
            CreateForm(validDescription);
            Assert.AreEqual(
                expected: expectedId, 
                actual: GetForm().QuestionnaireName);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.BadFormCases))]
        public void WhenGivenBadlyFormedForm_ThrowsException(
            string invalidDescription)
        {
            Assert.Throws<QlParserException>(() => CreateForm(invalidDescription));
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.GoodCommentCases))]
        public void WhenGivenComments_ReturnsDomainObjects(
            string validText, 
            string expectedName)
        {
            CreateForm(validText);
            Assert.AreEqual(expected: expectedName, actual: GetForm().QuestionnaireName);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.BadCommentCases))]
        public void WhenGivenBadlyFormedComments_ThrowsException(
            string invalidDescription)
        {
            Assert.Throws<QlParserException>(() => CreateForm(invalidDescription));
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.ValidNameCases))]
        public void WhenGivenValidIdentifier_NamesTheFormCorrectly(
            string validText, 
            string expectedName)
        {
            CreateForm(validText);
            Assert.AreEqual(
                expected: expectedName, 
                actual: GetForm().QuestionnaireName);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.InvalidNameCases))]
        public void WhenGivenInvalidIdentifier_ThrowsLexingError(
            string invalidText, 
            string invalidName)
        {
            var constraint = Is
                .TypeOf<QlParserException>()
                .And
                .Property(nameof(QlParserException.ParseErrorDetails))
                .Contains(invalidName);

            Assert.Throws(constraint, () => CreateForm(invalidText));
        }


        private IRootNode GetForm()
        {
            var createdForm = m_domainItemLocator
                .GetAll<IRootNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdForm);
            return createdForm;
        }

        #endregion

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.QuestionCases))]
        public void WhenGivenValidQuestion_NameTextAndTypeCorrect(
            string validText,
            string expectedId,
            string expectedText,
            Type expectedType)
        {
            CreateForm(validText);
            var question = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .FirstOrDefault();

            Assert.IsNotNull(question);
            Assert.AreEqual(expected: expectedId, actual: question.QuestionId);
            Assert.AreEqual(expected: expectedText, actual: question.QuestionText);
            Assert.AreEqual(expected: expectedType, actual: question.QuestionType);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.MultipleQuestionCases))]
        public void WhenGivenMultipleQuestions_CorrectNumberOfQuestions(
            string validText,
            int questionCount)
        {
            CreateForm(validText);
            Assert.AreEqual(expected: questionCount, actual: m_domainItemLocator.GetAll<IQuestionNode>().Count());
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.ConditionalStatementCases))]
        public void WhenFormHasConditionalStatement_CorrectNumberOfConditionalCasesExist(
            string validText,
            int conditionCount)
        {
            CreateForm(validText);
            var actualCount = m_domainItemLocator
                .GetAll<IConditionalStatementNode>()
                .Count();

            Assert.AreEqual(
                expected: conditionCount, 
                actual: actualCount);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.ElseStatementCases))]
        public void WhenFormHasElseConditional_CorrectNumberOfQuestionsExist(
            string validText,
            int conditionCount)
        {
            CreateForm(validText);
            var actualCount = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .Count();

            Assert.AreEqual(
                expected: conditionCount, 
                actual: actualCount);
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.BooleanConditional))]
        public void WhenBooleanQuestionUsedInAConditional_ParsesCorrectly(
            string validText,
            IEnumerable<string> expectedDefinitions,
            IEnumerable<bool> expectedLiterals,
            IEnumerable<string> expectedVariables,
            BooleanOperatorCount operatorCount,
            IEnumerable<string> expectedSubDefinitions)
        {
            CreateForm(validText);
            ValidateConditionalDefinitions(expectedDefinitions);
            ValidateVariableNames(expectedVariables);
            ValidateLiterals(expectedLiterals);
            ValidateBooleanOperators(operatorCount);
        }

        private void ValidateLiterals(IEnumerable<bool> expectedLiterals)
        {
            if (!expectedLiterals.Any())
            {
                return;
            }

            var literals = m_domainItemLocator
                .GetAll<ILiteralNode>()
                .Select(x => x.Value)
                .ToList();

            Assert.AreEqual(
                expected: expectedLiterals.Count(x => x),
                actual: literals.Count(x => x));

            Assert.AreEqual(
                expected: expectedLiterals.Count(x => !x),
                actual: literals.Count(x => !x));
        }

        private void ValidateVariableNames(
            IEnumerable<string> expectedVariables)
        {
            var conditionVariable = m_domainItemLocator
                .GetAll<IBooleanVariableNode>()
                .Select(x => x.VariableName)
                .ToList();

            foreach (var expectedVariable in expectedVariables)
            {
                Assert.Contains(
                    expected: expectedVariable, 
                    actual: conditionVariable);
            }
        }

        private void ValidateConditionalDefinitions(IEnumerable<string> expectedDefinitions)
        {
            var conditionNames = m_domainItemLocator
                .GetAll<IConditionalStatementNode>()
                .Select(x => x.Definition)
                .ToList();

            foreach (var expectedDefinition in expectedDefinitions)
            {
                Assert.Contains(
                    expected: expectedDefinition, 
                    actual: conditionNames);
            }
        }

        private void ValidateBooleanOperators(BooleanOperatorCount operatorCount)
        {
            Assert.AreEqual(
                expected: operatorCount.OrCount, 
                actual: NodeCount<IOrNode>());

            Assert.AreEqual(
                expected: operatorCount.AndCount, 
                actual: NodeCount<IAndNode>());

            Assert.AreEqual(
                expected: operatorCount.EqualityCount, 
                actual: NodeCount<IEqualityNode>());

            Assert.AreEqual(
                expected: operatorCount.InequalityCount, 
                actual: NodeCount<IInequalityNode>());

            Assert.AreEqual(
                expected: operatorCount.GreaterThanCount, 
                actual: NodeCount<IGreaterThanNode>());

            Assert.AreEqual(
                expected: operatorCount.GreaterOrEqualCount, 
                actual: NodeCount<IGreaterOrEqualNode>());

            Assert.AreEqual(
                expected: operatorCount.LessThanCount, 
                actual: NodeCount<ILessThanNode>());

            Assert.AreEqual(
                expected: operatorCount.LessOrEqualCount, 
                actual: NodeCount<ILessOrEqualNode>());
        }

        [TestCaseSource(
            typeof(TestData),
            nameof(TestData.MathStatements))]
        public void WhenCalculationUsedInACalcualtedQuestion_ParsesCorrectly(
            string validText,
            IEnumerable<decimal> expectedLiterals,
            IEnumerable<string> expectedVariables,
            MathOperatorCount operatorCount)
        {
            CreateForm(validText);
            ValidateMathVariableNames(expectedVariables);
            ValidateMathLiterals(expectedLiterals);
            ValidateMathOperators(operatorCount);
        }

        private void ValidateMathLiterals(
            IEnumerable<decimal> expectedLiterals)
        {
            if (!expectedLiterals.Any())
            {
                return;
            }

            var actualLiteralsList = m_domainItemLocator
                .GetAll<INumberNode>()
                .Select(x => x.Value)
                .ToList();

            actualLiteralsList.Sort();
            var expectedLiteralsList = expectedLiterals
                .ToList();

            expectedLiteralsList.Sort();

            Assert.AreEqual(
                expected: actualLiteralsList,
                actual: expectedLiteralsList);
        }
        private void ValidateMathVariableNames(
            IEnumerable<string> expectedVariables)
        {
            var conditionVariable = m_domainItemLocator
                .GetAll<ICalculationVariableNode>()
                .Select(x => x.VariableName)
                .ToList();

            foreach (var expectedVariable in expectedVariables)
            {
                Assert.Contains(
                    expected: expectedVariable,
                    actual: conditionVariable);
            }
        }

        private void ValidateMathOperators(MathOperatorCount operatorCount)
        {
            Assert.AreEqual(
                expected: operatorCount.AdditionCount,
                actual: NodeCount<IAddNode>());

            Assert.AreEqual(
                expected: operatorCount.SubtractionCount,
                actual: NodeCount<ISubtractNode>());

            Assert.AreEqual(
                expected: operatorCount.MultiplicationCount,
                actual: NodeCount<IMultiplyNode>());

            Assert.AreEqual(
                expected: operatorCount.DivisionCount,
                actual: NodeCount<IDivideNode>());
        }

        private int NodeCount<T>() where T : IAstNode
        {
            return m_domainItemLocator
                .GetAll<T>()
                .Count();
        }
    }


    public struct BooleanOperatorCount
    {
        public int AndCount;
        public int OrCount;
        public int NegateCount;
        public int EqualityCount;
        public int InequalityCount;
        public int GreaterOrEqualCount;
        public int GreaterThanCount;
        public int LessOrEqualCount;
        public int LessThanCount;
    }

    public struct MathOperatorCount
    {
        public int AdditionCount;
        public int SubtractionCount;
        public int MultiplicationCount;
        public int DivisionCount;
    }
}