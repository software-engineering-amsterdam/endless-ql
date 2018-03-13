using System;
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
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace UnitTests.Domain.UnitTests
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
            services.AddModule(new DomainLogicModule());
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
            var actualVisibleCount = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Count(x => x.Visible);
            var actualInvisibleCount = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Count(x => !x.Visible);

            Assert.AreEqual(
                expected: expectedVisible,
                actual: actualVisibleCount);
            Assert.AreEqual(
                expected: expectedInvisible,
                actual: actualInvisibleCount);
        }

        
        //[TestCaseSource(
        //    typeof(TestModelCreationData),
        //    nameof(TestModelCreationData.CalculationVariableValues))]
        //public void GivenBooleanVariableThatChange_ReturnsCorrectVisibility(
        //    string validDefinition,
        //    string variableName1,
        //    string variableName2,
        //    int expectedInitialVisible,
        //    int expectedInitialInvisible,
        //    int newValueVariable1,
        //    int newValueVariable2,
        //    int expectedNewVisible,
        //    int expectedNewInvisible)
        //{
        //    CreateForm(validDefinition);
        //    var actualInitialVisibleCount = m_domainItemLocator
        //        .GetAll<IQuestionOutputItem>()
        //        .Count(x => x.Visible);
        //    var actualInitialInvisibleCount = m_domainItemLocator
        //        .GetAll<IQuestionOutputItem>()
        //        .Count(x => !x.Visible);

        //    UpdateIntVariable(variableName1, newValueVariable1);
        //    UpdateIntVariable(variableName2, newValueVariable2);

        //    var actualNewVisibleCount = m_domainItemLocator
        //        .GetAll<IQuestionOutputItem>()
        //        .Count(x => x.Visible);
        //    var actualNewInvisibleCount = m_domainItemLocator
        //        .GetAll<IQuestionOutputItem>()
        //        .Count(x => !x.Visible);

        //    Assert.AreEqual(
        //        expected: expectedInitialVisible,
        //        actual: actualInitialVisibleCount);
        //    Assert.AreEqual(
        //        expected: expectedInitialInvisible,
        //        actual: actualInitialInvisibleCount);
        //    Assert.AreEqual(
        //        expected: expectedNewVisible,
        //        actual: actualNewVisibleCount);
        //    Assert.AreEqual(
        //        expected: expectedNewInvisible,
        //        actual: actualNewInvisibleCount);
        //}

        private void UpdateIntVariable(string variableName, int value)
        {
            var domainItem = m_domainItemLocator
                .GetAll<IVariableNode>()
                .Where(x => x.VariableName == variableName)
                .Select(x => new Reference<IVariableNode>(x.Id))
                .FirstOrDefault();

            m_variableUpdater.Update(domainItem, value.ToString(CultureInfo.InvariantCulture));
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
