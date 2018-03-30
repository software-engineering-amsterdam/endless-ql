using System;
using System.Collections.Generic;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QL.UnitTests.Domain.UnitTests.Data;
using QuestionnaireDomain.Entities;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace QL.UnitTests.Domain.UnitTests.Tests
{
    [TestFixture]
    public class ValidatorUnitTest
    {
        private IServiceProvider m_serviceProvider;
        private IQuestionnaireValidator m_questionnaireValidator;
        private IDomainItemLocator m_domainItemLocator;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new InfrastructureModule());
            services.AddModule(new EntitiesModule());
            m_serviceProvider = services.BuildServiceProvider();
            m_domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
        }

        [TearDown]
        public void Cleanup()
        {
            //ToDo: this is a hack, should fix lifetime of service (possibly)
            var registry = m_serviceProvider.GetService<IDomainItemRegistry>();
            registry.Nuke();
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.RepeatedNamesDifferentTypes))]
        public void WhenRepeatedQuestionDifferentTypes_ProducesTheCorrectMetaData(
            string invalidDescription,
            IEnumerable<Type> expectedSourceTypes,
            string errorMessage)
        {
            CreateAndValidateForm(invalidDescription);
            var results = ResultsFor<DuplicateVariableValidationMetaData>();
            var actualTypes = results
                .Select(x => m_domainItemLocator.Get<IQuestionNode>(x.Source.Id))
                .Select(x => x.QuestionType);

            AssertThatSeverityLevelIsError(results);
            AssertThatErrorMessagesMatch(errorMessage, results);
            Assert.IsTrue(
                actualTypes.All(q => expectedSourceTypes.Any()),
                @"The types of the underlying source objects did not match the expected types");
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.RepeatedNamesSameTypes))]
        public void WhenRepeatedQuestionSameTypes_NoErrors(
            string validDescription)
        {
            CreateAndValidateForm(validDescription);
            Assert.AreEqual(0, m_questionnaireValidator.Results.Count);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.UndefinedVariable))]
        public void WhenGivenUndefindedVariable_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<UndefinedVariableValidationMetaData>(
                invalidDescription, 
                errorMessage);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.NonBooleanConditionVariable))]
        public void WhenGivenNonBooleanVariableInCondition_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<BooleanConditionValidationMetaData>(
                invalidDescription,
                errorMessage);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.NonDateConditionVariable))]
        public void WhenGivenNonDateInDateComparison_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<DateComparisonValidationMetaData>(
                invalidDescription,
                errorMessage);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.NonTextConditionVariable))]
        public void WhenGivenNonTextInTextComparison_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<TextComparisonValidationMetaData>(
                invalidDescription,
                errorMessage);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.NonNumericConditionVariable))]
        public void WhenGivenNonNumberInNumberComparison_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<MathComparisonValidationMetaData>(
                invalidDescription,
                errorMessage);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.NonNumberCalculationVariable))]
        public void WhenGivenNonNumberInCalculation_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<MathExpressionValidationMetaData>(
                invalidDescription,
                errorMessage);
        }

        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.UnKnownVariables))]
        public void WhenGivenTwoVariableInNonValidTypeExpressions_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            ValidateMetaDataCreation<UnkownTypeExpressionValidationMetaData>(
                invalidDescription,
                errorMessage);
        }
        
        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.RepeatedText))]
        public void WhenGivenQuestionsWithRepeatedString_ProducesAWarning(
            string invalidDescription,
            string errorMessage)
        {
            CreateAndValidateForm(invalidDescription);
            var results = ResultsFor<DuplicateTextValidationMetaData>();

            AssertThatSeverityLevelIsWarning(results);
            Assert.IsTrue(results.Any());
            AssertThatErrorMessagesMatch(errorMessage, results);
        }
        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.NoCyclicDependency))]
        public void WhenGivenNoCyclicalDependecies_ProducesNoMetaDatas(
            string validDescription)
        {
            CreateAndValidateForm(validDescription);
            var results = ResultsFor<CyclicDependencyValidationMetaData>();
            
            Assert.IsTrue(!results.Any());
        }


        [TestCaseSource(
            typeof(TestValidationData),
            nameof(TestValidationData.CyclicDependency))]
        public void WhenGivenQuestionsWithCyclicalDependecies_ProducesTheCorrectMetaDatas(
            string invalidDescription,
            string errorMessage)
        {
            CreateAndValidateForm(invalidDescription);
            var results = ResultsFor<CyclicDependencyValidationMetaData>();

            AssertThatSeverityLevelIsError(results);
            Assert.IsTrue(results.Any());
            AssertThatErrorMessagesMatch(errorMessage, results);
        }

        private IList<ValidationMetaData> ResultsFor<T>() where T : ValidationMetaData
        {
            return m_questionnaireValidator
                .Results
                .OfType<T>()
                .Cast<ValidationMetaData>()
                .ToList();
        }
        
        private void ValidateMetaDataCreation<T>(
            string invalidDescription, 
            string errorMessage) where T : ValidationMetaData
        {
            CreateAndValidateForm(invalidDescription);
            var results = ResultsFor<T>();

            AssertThatSeverityLevelIsError(new List<ValidationMetaData>(results));
            AssertThatOneValidationErrorIsCreated(results);
            AssertThatErrorMessagesMatch(errorMessage, results);
        }

        private static void AssertThatOneValidationErrorIsCreated(
            ICollection<ValidationMetaData> results)
        {
            Assert.AreEqual(
                expected: 1, 
                actual: results.Count, 
                message: @"no type check error");
        }

        private static void AssertThatErrorMessagesMatch(string errorMessage, IList<ValidationMetaData> results)
        {
            Assert.IsTrue(
                results.All(x => x.Message == errorMessage),
                $@"did not return the expected error message: '{errorMessage}'");
        }

        private static void AssertThatSeverityLevelIsError(
            IList<ValidationMetaData> results)
        {
            Assert.IsTrue(
                results.All(x => x.Severity == Severity.Error),
                @"Did not have the Severity 'Error'");
        }

        private static void AssertThatSeverityLevelIsWarning(
            IList<ValidationMetaData> results)
        {
            Assert.IsTrue(
                results.All(x => x.Severity == Severity.Warning),
                @"Did not have the Severity 'Warning'");
        }

        private void CreateAndValidateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider
                .GetService<IQuestionnaireAstCreator>();
            m_questionnaireValidator = m_serviceProvider
                .GetService<IQuestionnaireValidator>();

            var domainItemId = questionnaireCreator.
                Create(validText);

            Assert.IsNotNull(domainItemId, @"should have created a for from a valid definition");
            m_questionnaireValidator.Validate(domainItemId);
        }
    }
}
