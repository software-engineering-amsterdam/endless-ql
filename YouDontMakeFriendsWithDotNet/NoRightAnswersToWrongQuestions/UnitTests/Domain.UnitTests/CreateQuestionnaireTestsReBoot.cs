using System;
using System.Collections.Generic;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities;
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
            services.AddModule(new EntitiesModule());
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

            Assert.IsNotNull(domainItemId, "should have created a for from a valid definition");
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
                actual: GetForm().QuestionnaireName,
                message: $"should have created a form from this definition: '{validDescription}'");
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.BadFormCases))]
        public void WhenGivenBadlyFormedForm_ThrowsException(
            string invalidDescription)
        {
            Assert.Throws<QlParserException>(
                code: () => CreateForm(invalidDescription),
                message:$"Should not have created a form from this definition: '{invalidDescription}'");
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.GoodCommentCases))]
        public void WhenGivenComments_ReturnsDomainObjects(
            string validText, 
            string expectedName)
        {
            CreateForm(validText);
            Assert.AreEqual(
                expected: expectedName, 
                actual: GetForm().QuestionnaireName,
                message: $"The following definition with comments should have parsed correctly: '{validText}'" );
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.BadCommentCases))]
        public void WhenGivenBadlyFormedComments_ThrowsException(
            string invalidDescription)
        {
            Assert.Throws<QlParserException>(
                code:() => CreateForm(invalidDescription),
                message: $"the following description with comments should not have parsed correctly: '{invalidDescription}'");
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
                actual: GetForm().QuestionnaireName,
                message: $"the questionnaire name '{expectedName}' was not created from the following desctiption: '{validText}'");
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

            Assert.Throws(
                expression: constraint, 
                code: () => CreateForm(invalidText),
                message: $"The questionnaire identifier '{invalidName}' should not be parsed");
        }

        private IRootNode GetForm()
        {
            var createdForm = m_domainItemLocator
                .GetAll<IRootNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdForm, "could not find a questionnaire node");

            return createdForm;
        }

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

            Assert.IsNotNull(question, "could not find a question node");

            Assert.AreEqual(
                expected: expectedId, 
                actual: question.QuestionId,
                message: "The question name did not match");

            Assert.AreEqual(
                expected: expectedText, 
                actual: question.QuestionText,
                message: "The question text did not match");

            Assert.AreEqual(
                expected: expectedType, 
                actual: question.QuestionType,
                message: "The question type did not match");
        }

        [TestCaseSource(
            typeof(TestData), 
            nameof(TestData.MultipleQuestionCases))]
        public void WhenGivenMultipleQuestions_CorrectNumberOfQuestions(
            string validText,
            int expectedQuestionCount)
        {
            CreateForm(validText);
            var actualQuestionCount = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .Count();

            Assert.AreEqual(
                expected: expectedQuestionCount, 
                actual: actualQuestionCount,
                message: "an incorrect number of questions was created");
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
                actual: actualCount,
                message: "an incorrect number of If Then Else conditions were created");
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
                actual: actualCount,
                message: "question statements in the else block have not been correctly created");
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
            ValidateBooleanLiterals(expectedLiterals);
            ValidateBooleanOperators(operatorCount);
        }

        private void ValidateBooleanLiterals(IEnumerable<bool> expectedLiterals)
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
                actual: literals.Count(x => x),
                message: "There is an incorrect number of true boolean literals");

            Assert.AreEqual(
                expected: expectedLiterals.Count(x => !x),
                actual: literals.Count(x => !x),
                message: "There is an incorrect number of false boolean literals");
        }
        
        private void ValidateConditionalDefinitions(IEnumerable<string> expectedDefinitions)
        {
            var conditionDefinitions = m_domainItemLocator
                .GetAll<IConditionalStatementNode>()
                .Select(x => x.Definition)
                .ToList();

            foreach (var expectedDefinition in expectedDefinitions)
            {
                Assert.Contains(
                    expected: expectedDefinition, 
                    actual: conditionDefinitions,
                    message: $"The conditions '{expectedDefinition}' was not created.  The created definitions were: '{string.Join("','", conditionDefinitions)}'");
            }
        }

        private void ValidateBooleanOperators(BooleanOperatorCount operatorCount)
        {
            Assert.AreEqual(
                expected: operatorCount.OrCount, 
                actual: NodeCount<IOrNode>(),
                message: "there is an incorrect number of created '||' Nodes");

            Assert.AreEqual(
                expected: operatorCount.AndCount, 
                actual: NodeCount<IAndNode>(),
                message: "there is an incorrect number of created '&&' Nodes");

            Assert.AreEqual(
                expected: operatorCount.EqualityCount, 
                actual: NodeCount<IEqualityNode>(),
                message: "there is an incorrect number of created '==' Nodes");

            Assert.AreEqual(
                expected: operatorCount.InequalityCount, 
                actual: NodeCount<IInequalityNode>(),
                message: "there is an incorrect number of created '!=' Nodes");

            Assert.AreEqual(
                expected: operatorCount.GreaterThanCount, 
                actual: NodeCount<IGreaterThanNode>(),
                message: "there is an incorrect number of created '>' Nodes");

            Assert.AreEqual(
                expected: operatorCount.GreaterOrEqualCount, 
                actual: NodeCount<IGreaterOrEqualNode>(),
                message: "there is an incorrect number of created '>=' Nodes");

            Assert.AreEqual(
                expected: operatorCount.LessThanCount, 
                actual: NodeCount<ILessThanNode>(),
                message: "there is an incorrect number of created '<' Nodes");

            Assert.AreEqual(
                expected: operatorCount.LessOrEqualCount, 
                actual: NodeCount<ILessOrEqualNode>(),
                message: "there is an incorrect number of created '<=' Nodes");
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
            ValidateVariableNames(expectedVariables);
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

            var actualLiterals = m_domainItemLocator
                .GetAll<INumberNode>()
                .Select(x => x.Value);

            var createdNotExpected = actualLiterals.Except(expectedLiterals);
            Assert.IsEmpty(
                collection: createdNotExpected,
                message: $"numbers created but not expected: {string.Join(",", createdNotExpected)}.");

            var expectedNotCreated = expectedLiterals.Except(actualLiterals);
            Assert.IsEmpty(
                collection: expectedNotCreated,
                message: $"numbers expected but not created: {string.Join(",",expectedNotCreated)}");
        }
        
        private void ValidateMathOperators(MathOperatorCount operatorCount)
        {
            Assert.AreEqual(
                expected: operatorCount.AdditionCount,
                actual: NodeCount<IAddNode>(),
                message: "incorrect number of addition operations");

            Assert.AreEqual(
                expected: operatorCount.SubtractionCount,
                actual: NodeCount<ISubtractNode>(),
                message: "incorrect number of subtraction operations");

            Assert.AreEqual(
                expected: operatorCount.MultiplicationCount,
                actual: NodeCount<IMultiplyNode>(),
                message: "incorrect number of multiplication operations");

            Assert.AreEqual(
                expected: operatorCount.DivisionCount,
                actual: NodeCount<IDivideNode>(),
                message: "incorrect number of division operations");
        }

        private int NodeCount<T>() where T : IAstNode
        {
            return m_domainItemLocator
                .GetAll<T>()
                .Count();
        }

        [TestCaseSource(
            typeof(TestData),
            nameof(TestData.ComparisonConditional))]
        public void WhenComparisonUsedInACondition_ParsesCorrectly(
            string validText,
            IEnumerable<string> expectedCalculationDefinitions,
            IEnumerable<bool> expectedLiterals,
            IEnumerable<string> expectedVariables,
            RelationalOperatorCount operatorCount)
        {
            CreateForm(validText);
            ValidateRelationalDefinition(expectedCalculationDefinitions);
            ValidateVariableNames(expectedVariables);
            ValidateBooleanLiterals(expectedLiterals);
            ValidateRelationalOperators(operatorCount);
        }

        private void ValidateRelationalOperators(RelationalOperatorCount operatorCount)
        {
            Assert.AreEqual(
                expected: operatorCount.EqualityCount,
                actual: NodeCount<IEqualityNode>(),
                message: "incorrect number of '==' operations");

            Assert.AreEqual(
                expected: operatorCount.InequalityCount,
                actual: NodeCount<IInequalityNode>(),
                message: "incorrect number of '!=' operations");

            Assert.AreEqual(
                expected: operatorCount.GreaterThanCount,
                actual: NodeCount<IGreaterThanNode>(),
                message: "incorrect number of '>' operations");

            Assert.AreEqual(
                expected: operatorCount.GreaterOrEqualCount,
                actual: NodeCount<IGreaterOrEqualNode>(),
                message: "incorrect number of '>=' operations");

            Assert.AreEqual(
                expected: operatorCount.LessThanCount,
                actual: NodeCount<ILessThanNode>(),
                message: "incorrect number of '<' operations");

            Assert.AreEqual(
                expected: operatorCount.LessOrEqualCount,
                actual: NodeCount<ILessOrEqualNode>(),
                message: "incorrect number of '<=' operations");
        }

        [TestCaseSource(
            typeof(TestData),
            nameof(TestData.DateComparisonConditional))]
        public void WhenDateComparisonUsedInACondition_ParsesCorrectly(
            string validText,
            IEnumerable<string> expectedCalculationDefinitions,
            IEnumerable<DateTime> expectedLiterals,
            IEnumerable<string> expectedVariables,
            RelationalOperatorCount operatorCount)
        {
            CreateForm(validText);
            ValidateRelationalDefinition(expectedCalculationDefinitions);
            ValidateVariableNames(expectedVariables);
            ValidateDateLiterals(expectedLiterals);
            ValidateRelationalOperators(operatorCount);
        }
        
        private void ValidateDateLiterals(
            IEnumerable<DateTime> expectedLiterals)
        {
            if (!expectedLiterals.Any())
            {
                return;
            }

            var actualLiterals = m_domainItemLocator
                .GetAll<IDateNode>()
                .Select(x => x.Value);

            var createdNotExpected = actualLiterals.Except(expectedLiterals);
            Assert.IsEmpty(
                collection: createdNotExpected,
                message: $"dates created but not expected: {string.Join(",", createdNotExpected)}.");

            var expectedNotCreated = expectedLiterals.Except(actualLiterals);
            Assert.IsEmpty(
                collection: expectedNotCreated,
                message: $"dates expected but not created: {string.Join(",", expectedNotCreated)}");
        }

        private void ValidateRelationalDefinition(IEnumerable<string> expectedDefinitions)
        {
            var conditionDefinitions = m_domainItemLocator
                .GetAll<IRelationalLogicNode>()
                .Select(x => x.Definition)
                .ToList();

            foreach (var expectedDefinition in expectedDefinitions)
            {
                Assert.Contains(
                    expected: expectedDefinition,
                    actual: conditionDefinitions,
                    message: $"The conditions '{expectedDefinition}' was not created.  The created definitions were: '{string.Join("','", conditionDefinitions)}'");
            }
        }
        [TestCaseSource(
            typeof(TestData),
            nameof(TestData.TextEqualityConditional))]
        public void WhenTextComparisonUsedInACondition_ParsesCorrectly(
            string validText,
            IEnumerable<string> expectedCalculationDefinitions,
            IEnumerable<string> expectedLiterals,
            IEnumerable<string> expectedVariables,
            RelationalOperatorCount operatorCount)
        {
            CreateForm(validText);
            ValidateRelationalDefinition(expectedCalculationDefinitions);
            ValidateVariableNames(expectedVariables);
            ValidateTextLiterals(expectedLiterals);
            ValidateRelationalOperators(operatorCount);
        }


        private void ValidateTextLiterals(
            IEnumerable<string> expectedLiterals)
        {
            if (!expectedLiterals.Any())
            {
                return;
            }

            var actualLiterals = m_domainItemLocator
                .GetAll<ITextNode>()
                .Select(x => x.Value);

            var createdNotExpected = actualLiterals.Except(expectedLiterals);
            Assert.IsEmpty(
                collection: createdNotExpected,
                message: $"numbers created but not expected: {string.Join(",", createdNotExpected)}.");

            var expectedNotCreated = expectedLiterals.Except(actualLiterals);
            Assert.IsEmpty(
                collection: expectedNotCreated,
                message: $"numbers expected but not created: {string.Join(",", expectedNotCreated)}");
        }

        private void ValidateVariableNames(
            IEnumerable<string> expectedVariables)
        {
            var conditionVariable = m_domainItemLocator
                .GetAll<IVariableNode>()
                .Select(x => x.VariableName)
                .ToList();

            foreach (var expectedVariable in expectedVariables)
            {
                Assert.Contains(
                    expected: expectedVariable,
                    actual: conditionVariable,
                    message: "There is an incorrect number of text variable nodes");
            }
        }


        [TestCaseSource(
            typeof(TestData),
            nameof(TestData.CalculationRelationalConditional))]
        public void WhenCalculationUsedInACondition_ParsesCorrectly(
            string validText,
            IEnumerable<string> expectedCalculationDefinitions,
            IEnumerable<string> expectedExpressions,
            IEnumerable<string> expectedVariables,
            RelationalOperatorCount operatorCount)
        {
            CreateForm(validText);
            ValidateRelationalDefinition(expectedCalculationDefinitions);
            ValidateVariableNames(expectedVariables);
            ValidateMathExpressions(expectedExpressions);
            ValidateRelationalOperators(operatorCount);
        }

        private void ValidateMathExpressions(IEnumerable<string> expectedExpressions)
        {
            if (!expectedExpressions.Any())
            {
                return;
            }

            var actualLiterals = m_domainItemLocator
                .GetAll<ICalculationNode>()
                .Select(x => x.Definition);

            var createdNotExpected = actualLiterals.Except(expectedExpressions);
            Assert.IsEmpty(
                collection: createdNotExpected,
                message: $"expression created but not expected: {string.Join(",", createdNotExpected)}.");

            var expectedNotCreated = expectedExpressions.Except(actualLiterals);
            Assert.IsEmpty(
                collection: expectedNotCreated,
                message: $"expression expected but not created: {string.Join(",", expectedNotCreated)}");

        }
    }
}