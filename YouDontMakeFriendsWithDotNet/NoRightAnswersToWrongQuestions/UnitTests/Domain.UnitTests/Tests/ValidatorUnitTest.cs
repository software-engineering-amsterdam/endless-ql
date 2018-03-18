using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices.ComTypes;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionnaireDomain.Entities;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;
using UnitTests.Domain.UnitTests.Data;

namespace UnitTests.Domain.UnitTests.Tests
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
            var results = m_questionnaireValidator.Results;
            var actualTypes = results
                .Select(x => m_domainItemLocator.Get<IQuestionNode>(x.Source.Id))
                .Select(x => x.QuestionType);

            Assert.IsTrue(
                results.All(x => x.Severity == Severity.Error),
                "duplicate questions different type did not have the Severity 'Error'");
            Assert.IsTrue(
                results.All(x => x.Message == errorMessage),
                $"duplicate questions different type did not return the expected error messager '{errorMessage}'");
            Assert.IsTrue(
                actualTypes.All(q => expectedSourceTypes.Any()),
                "The types of the underlying source objects did not match the expected types");
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

        private void CreateAndValidateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider
                .GetService<IQuestionnaireAstCreator>();
            m_questionnaireValidator = m_serviceProvider
                .GetService<IQuestionnaireValidator>();
            var domainItemId = questionnaireCreator.
                Create(validText);
            Assert.IsNotNull(domainItemId, "should have created a for from a valid definition");
            m_questionnaireValidator.Validate(domainItemId);
        }
    }
}
