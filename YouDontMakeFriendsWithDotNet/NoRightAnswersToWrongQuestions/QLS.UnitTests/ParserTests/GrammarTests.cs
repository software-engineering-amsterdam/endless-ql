using System;
using System.Linq;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QlsParser;
using QlsTransformer;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Ast.Tools;
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
        public void GivenValidDefaultStyles_Parses(
            string validStyleSheetDefinition)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyle = m_domainItemLocator
                .GetAll<IStyleNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdStyle, "could not find a style node");
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.WidgetType))]
        public void GivenValidWidget_CreatesCorrectWidget(
            string validStyleSheetDefinition,
            Type widgetInterface)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyle = m_domainItemLocator
                .GetAll<IStyleNode>()
                .FirstOrDefault();

            Assert.IsNotNull(createdStyle, "could not find a style node");

            Assert.IsTrue(widgetInterface.IsInstanceOfType(createdStyle.Widget));
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.BooleanWidgetType))]
        public void GivenTrueFalseValues_HasCorrectValues(
            string validStyleSheetDefinition,
            string trueText,
            string falseText)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyle = m_domainItemLocator
                .GetAll<IStyleNode>()
                .FirstOrDefault();

            var widget = (IBinaryWidget) createdStyle.Widget;

            Assert.AreEqual(
                expected: trueText,
                actual: widget.TrueText);

            Assert.AreEqual(
                expected: falseText,
                actual: widget.FalseText);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.SliderWidget))]
        public void GivenSliderWidget_HasCorrectBeginEndAndStepValues(
            string validStyleSheetDefinition,
            int start,
            int end,
            int step)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyle = m_domainItemLocator
                .GetAll<IStyleNode>()
                .FirstOrDefault();

            var widget = (ISlider) createdStyle.Widget;

            Assert.AreEqual(
                expected: start,
                actual: widget.RangeStart);

            Assert.AreEqual(
                expected: end,
                actual: widget.RangeEnd);

            Assert.AreEqual(
                expected: step,
                actual: widget.Step);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.PropertyValues))]
        public void GivenStyleWithAllProperties_StoresCorrectValues(
            string validStyleSheetDefinition,
            int width,
            string font,
            decimal fontsize,
            string color)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyle = m_domainItemLocator
                .GetAll<IStyleNode>()
                .FirstOrDefault();

            Assert.AreEqual(
                expected: width,
                actual: createdStyle.Width);

            Assert.AreEqual(
                expected: font,
                actual: createdStyle.Font);

            Assert.AreEqual(
                expected: fontsize,
                actual: createdStyle.FontSize);

            Assert.AreEqual(
                expected: color,
                actual: createdStyle.Color);
        }

        [TestCaseSource(
            typeof(TestQlsData),
            nameof(TestQlsData.DefaultPropertyCounts))]
        public void GivenStyleWithDefaultStylesForAllTypes_StoresCorrectValues(
            string validStyleSheetDefinition,
            int defaultPropertyCount)
        {
            CreateStyleSheet(validStyleSheetDefinition);

            var createdStyles = m_domainItemLocator
                .GetAll<IStyleNode>();

            Assert.AreEqual(
                expected: defaultPropertyCount,
                actual: createdStyles.Count());
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
