using System;
using System.Linq;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QlsParser;
using QlsTransformer;
using QlsTransformer.Ast.Nodes;
using QlsTransformer.Ast.Tools;
using QuestionnaireDomain.Entities;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace QLS.UnitTests.ParserTests
{
    [TestFixture]
    public class GrammarTests
    {
        private IServiceProvider m_serviceProvider;
        private IDomainItemLocator m_domainItemLocator;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new QlsParserModule());
            services.AddModule(new InfrastructureModule());
            services.AddModule(new EntitiesModule());
            services.AddModule(new QlsTransformerModule());
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
            typeof(TestQlsData),
            nameof(TestQlsData.EmptyStyleSheet))]
        public void GivenStyleSheet_CreatesStyleSheetObject(
            string validStyleSheetDefinition,
            string styleSheetName)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyleSheet = m_domainItemLocator
                .GetAll<IStyleSheetRootNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdStyleSheet, "could not find a questionnaire node");

            Assert.AreEqual(
                expected: styleSheetName,
                actual: createdStyleSheet.Name);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.StyleSheetWithOnePage))]
        public void GivenStyleSheetWithPage_CreatesPageObject(
            string validStyleSheetDefinition,
            string pageName)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdPage = m_domainItemLocator
                .GetAll<IPageNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdPage, "could not find a page node");

            Assert.AreEqual(
                expected: pageName,
                actual: createdPage.Name);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.StyleSheetWithMultiplePages))]
        public void GivenStyleSheetWithManyPages_CreatesPageObjects(
            string validStyleSheetDefinition,
            int pageCount)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            Assert.AreEqual(
                expected: pageCount,
                actual: m_domainItemLocator.GetAll<IPageNode>().Count());
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.StyleSheetSection))]
        public void GivenStyleSheetWithSections_CreatesSectionObject(
            string validStyleSheetDefinition,
            string sectionName)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdSection = m_domainItemLocator
                .GetAll<ISectionNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdSection, "could not find a section node");

            Assert.AreEqual(
                expected: sectionName,
                actual: createdSection.Name);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.MultipleStyleSheetSection))]
        public void GivenStyleSheetWithManySections_CreatesSectionObjects(
            string validStyleSheetDefinition,
            int sectionCount)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            Assert.AreEqual(
                expected: sectionCount,
                actual: m_domainItemLocator.GetAll<ISectionNode>().Count());
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.WithAQuestion))]
        public void GivenStyleSheetWithAQuestion_CreatesAQuestionObjects(
            string validStyleSheetDefinition,
            string questionName)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdQuestion = m_domainItemLocator
                .GetAll<IQlsQuestionNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdQuestion, "could not find a question node");

            Assert.AreEqual(
                expected: questionName,
                actual: createdQuestion.Name);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.WithMultipleQuestions))]
        public void GivenStyleSheetWithManyQuestions_CreatesQuestionObjects(
            string validStyleSheetDefinition,
            int questionCount)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            Assert.AreEqual(
                expected: questionCount,
                actual: m_domainItemLocator.GetAll<IQlsQuestionNode>().Count());
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.StyleSheetWithDefaultStyle))]
        public void GivenValidDefaultStles_Parses(
            string validStyleSheetDefinition)
        {
            CreateStyleSheet(validStyleSheetDefinition);
        }

        private void CreateStyleSheet(string definition)
        {
            var styleSheetCreator = m_serviceProvider
                .GetService<IStyleSheetCreator>();

            var domainItemId = styleSheetCreator.Create(definition);

            Assert.IsNotNull(domainItemId, "should have created a stylesheet from a valid definition");
        }
    }
}