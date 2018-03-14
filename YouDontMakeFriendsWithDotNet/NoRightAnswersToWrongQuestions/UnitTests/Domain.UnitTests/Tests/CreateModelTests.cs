using System;
using System.Diagnostics;
using System.Globalization;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionnaireDomain.Entities;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;
using UnitTests.Domain.UnitTests.Data;

namespace UnitTests.Domain.UnitTests.Tests
{
    [TestFixture]
    public class CreateModelTests
    {
        private IServiceProvider m_serviceProvider;
        private IDomainItemLocator m_domainItemLocator;
        private IQuestionnaireModelCreator m_modelCreator;
        private IVariableUpdater m_variableUpdater;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new InfrastructureModule());
            services.AddModule(new EntitiesModule());
            m_serviceProvider = services.BuildServiceProvider();
            m_domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            m_modelCreator = m_serviceProvider.GetService<IQuestionnaireModelCreator>();
            m_variableUpdater = m_serviceProvider.GetService<IVariableUpdater>();
        }

        [TearDown]
        public void Cleanup()
        {
            //ToDo: this is a hack, should fix lifetime of service (possibly)
            var registry = m_serviceProvider.GetService<IDomainItemRegistry>();
            registry.Nuke();
        }


        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.SimpleQuestionnaireCases))]
        public void GivenAValidForm_CreatesAQuestionnaireOutputWithSameName(
            string validDescription,
            string expectedQuestionnaireName)
        {
            CreateForm(validDescription);

            var actualDisplayName = m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .FirstOrDefault()
                ?.DisplayName;

            Assert.AreEqual(
                expected: expectedQuestionnaireName,
                actual: actualDisplayName);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.QuestionTypes))]
        public void GivenAValidType_ReturnsQuestionOfCorrectType(
           string validDefinition,
           Type questionType) 
        {
            CreateForm(validDefinition);
            var domainItem = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault();

            Assert.NotNull(domainItem);
            Assert.AreEqual(
                expected: questionType, 
                actual: domainItem.QuestionType);
        }
        
        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.DefaultQuestionValues))]
        public void GivenAnUninitiatedValue_ReturnsDefaultValue(
            string validDefinition,
            string questionValue)
        {
            CreateForm(validDefinition);
            var outputItem = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault();

            Assert.NotNull(outputItem);
            Assert.AreEqual(
                expected: questionValue,
                actual: outputItem.Value);
        }


        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.IfQuestionValues))]
        public void GivenIfElseCondition_ReturnsCorrectVisibility(
            string validDefinition,
            int expectedVisible,
            int expectedInvisible)
        {
            CreateForm(validDefinition);
            var actualVisibleCount = GetVisibleCount();
            var actualInvisibleCount = GetInvisibleCount();

            Assert.AreEqual(
                expected: expectedVisible,
                actual: actualVisibleCount);
            Assert.AreEqual(
                expected: expectedInvisible,
                actual: actualInvisibleCount);
        }


        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculationVariableValues))]
        public void GivenBooleanVariableThatChangeFromTrueToFalse_ReturnsCorrectVisibility(
            string validDefinition,
            int newValueVariable1,
            int newValueVariable2)
        {
            CreateForm(validDefinition);
            var actualInitialVisibleCount = GetVisibleCount();
            var actualInitialInvisibleCount = GetInvisibleCount();

            UpdateIntVariable(@"intQ1", newValueVariable1);
            UpdateIntVariable(@"intQ2", newValueVariable2);

            var actualNewVisibleCount = GetVisibleCount();
            var actualNewInvisibleCount = GetInvisibleCount();

            Assert.AreEqual(expected: 3, actual: actualInitialVisibleCount);
            Assert.AreEqual(expected: 2, actual: actualInitialInvisibleCount);
            Assert.AreEqual(expected: 4, actual: actualNewVisibleCount);
            Assert.AreEqual(expected: 1,actual: actualNewInvisibleCount);
        }

        private int GetVisibleCount()
        {
            return GetVisibilityCount(x => x.Visible);
        }

        private int GetInvisibleCount()
        {
            return GetVisibilityCount(x => !x.Visible);
        }
        
        private int GetVisibilityCount(Func<IQuestionOutputItem, bool> predicate)
        {
            return m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Count(predicate);
        }

        private void UpdateIntVariable(string variableName, int value)
        {

            var variableItem = m_domainItemLocator
                .GetAll<IVariableNode>()
                .FirstOrDefault(x => x.VariableName == variableName);

            var questionItem = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .Where(x => x.QuestionName == variableItem.VariableName)
                .Select(x => new Reference<IQuestionNode>(x.Id))
                .FirstOrDefault();

            m_variableUpdater.Update(questionItem, value.ToString(CultureInfo.InvariantCulture));
        }

        private void CreateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider
                .GetService<IQuestionnaireAstCreator>();

            questionnaireCreator.Create(validText);

            var questionnaireNodes = m_domainItemLocator
                .GetAll<IQuestionnaireRootNode>();

            foreach (var questionnaireRootNode in questionnaireNodes)
            {
                var questionnaireRef = new Reference<IQuestionnaireRootNode>(questionnaireRootNode.Id);
                if (m_modelCreator.Validate(questionnaireRef))
                {
                    m_modelCreator.Create(questionnaireRef);
                }
            }

        }
    }
}
