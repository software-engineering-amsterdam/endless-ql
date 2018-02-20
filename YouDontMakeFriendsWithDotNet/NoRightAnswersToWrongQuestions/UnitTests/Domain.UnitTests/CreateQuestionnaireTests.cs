using System;
using System.Collections;
using System.ComponentModel.Design;
using System.Linq;
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
                yield return new TestCaseData(@"form CommentForm {}//", @"CommentForm");
                yield return new TestCaseData(@"form CommentFormX {}// has comment", @"CommentFormX");
                var comment1 = @"// has comment
form CommentFormXYZ {}";
                yield return new TestCaseData(comment1, @"CommentFormXYZ");
                yield return new TestCaseData(@"form CommentFormABC {}/* . */", @"CommentFormABC");
                yield return new TestCaseData(@"form /* inline */ CommentFormDEF {}", @"CommentFormDEF");
                var multilineComment1 = @"/*
some comment
*/
form CommentFormML {}";
                yield return new TestCaseData(multilineComment1, @"CommentFormML");
                var multilineComment2 = @"/*
//some comment
*/
form CommentFormMLX {}";
                yield return new TestCaseData(multilineComment2, @"CommentFormMLX");
                yield return new TestCaseData(@"form /* //inline */ CommentFormCIC {}", @"CommentFormCIC");
                // ToDo: Add instring comments and make sure they print
            }
        }

        [TestCaseSource(nameof(ValidNameCases))]
        public void WhenGivenValidIdentifier_NamesTheFormCorrectly(string validText, string expectedName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var domainItemId = questionnaireCreator.Create(validText);
            var createdForm = domainItemLocator.Get<IQuestionnaireAst>(domainItemId);
            Assert.IsNotNull(domainItemId);
            Assert.AreEqual(expected: expectedName, actual: createdForm.FormName);
        }

        private static IEnumerable ValidNameCases
        {
            get
            {
                yield return new TestCaseData(@"form NameForm {}", @"NameForm");
                yield return new TestCaseData(@"form NameForm1 {}", @"NameForm1");
                yield return new TestCaseData(@"form A {}", @"A");
                yield return new TestCaseData(@"form z {}", @"z");
                yield return new TestCaseData(@"form Name_Form1 {}", @"Name_Form1");
            }
        }

        [TestCaseSource(nameof(InvalidNameCases))]
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

        private static IEnumerable InvalidNameCases
        {
            get
            {
                yield return new TestCaseData(@"form 1NameForm {}", @"1NameForm");
                yield return new TestCaseData(@"form Name$Form {}", @"Name$Form");
                yield return new TestCaseData(@"form _NameForm {}", @"_NameForm");
                yield return new TestCaseData(@"form % {}", @"%");
                yield return new TestCaseData(@"form +_(# {}", @"+_(#");
            }
        }

        //[TestCaseSource(nameof(QuestionCases))]
        //public void WhenGivenValidQuestion_NameAndTextCorrect(string validText, string questionId, string questionText)
        //{
        //    var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
        //    var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
        //    var domainItemId = questionnaireCreator.Create(validText);
        //    var createdForm = domainItemLocator.Get<IQuestionnaireAst>(domainItemId);
        //    var question = createdForm.Questions.FirstOrDefault();
        //    Assert.IsNotNull(domainItemId);
        //    Assert.AreEqual(expected: questionId, actual: question.Name);
        //    Assert.AreEqual(expected: questionText, actual: question.Text);
        //}

        //private static IEnumerable QuestionCases
        //{
        //    get
        //    {
        //        yield return new TestCaseData(@"form NameForm { x: ""xyz"" boolean}", @"x", @"xyz");
        //    }
        //}
    }
}