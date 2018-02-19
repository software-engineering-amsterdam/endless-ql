using System;
using System.Collections;
using System.ComponentModel.Design;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;
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
                    expected: @"ANTLR 4.0",
                    actual: exception.ParserName);

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
                    expected: @"ANTLR 4.0",
                    actual: exception.ParserName);

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
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var validText = @"form MyForm {}";
            var domainItemId = questionnaireCreator.Create(validText);
            var createdForm = domainItemLocator.Get<IQuestionnaireAst>(domainItemId); 
            Assert.IsNotNull(domainItemId);
            Assert.AreEqual(expected: "MyForm", actual: createdForm.FormName);
        }

        [TestCaseSource(nameof(CommentCases))]
        public void WhenGivenComments_ReturnsDomainObjects(string validText, string expectedName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var domainItemId = questionnaireCreator.Create(validText);
            var createdForm = domainItemLocator.Get<IQuestionnaireAst>(domainItemId);
            Assert.IsNotNull(domainItemId);
            Assert.AreEqual(expected: expectedName, actual: createdForm.FormName);
        }

        private static IEnumerable CommentCases
        {
            get
            {
                var comment1 = @"form CommentForm {}//";
                yield return new TestCaseData(comment1, @"CommentForm");
                var comment2 = @"form CommentFormX {}// has comment";
                yield return new TestCaseData(comment2, @"CommentFormX");
                var comment3 = @"// has comment
form CommentFormX {}";
                yield return new TestCaseData(comment2, @"CommentFormX");
            }
        }
    }
}
