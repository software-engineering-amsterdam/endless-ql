using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
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
            var createdForm = CreateForm(@"form MyForm {}");
            Assert.AreEqual(expected: "MyForm", actual: createdForm.FormName);
        }

        private IQuestionnaireAst CreateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var domainItemId = questionnaireCreator.Create(validText);
            var createdForm = domainItemLocator.Get<IQuestionnaireAst>(domainItemId);
            Assert.IsNotNull(domainItemId);
            return createdForm;
        }

        [TestCaseSource(nameof(CommentCases))]
        public void WhenGivenComments_ReturnsDomainObjects(string validText, string expectedName)
        {
            var createdForm = CreateForm(validText);
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
            var createdForm = CreateForm(validText);
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
        
        [TestCaseSource(nameof(QuestionCases))]
        public void WhenGivenValidQuestion_NameAndTextCorrect(string validText, string questionId, string questionText)
        {
            var createdForm = CreateForm(validText);
            var question = createdForm.Statements.OfType<IQuestionAst>().FirstOrDefault();
            Assert.AreEqual(expected: questionId, actual: question.Name);
            Assert.AreEqual(expected: questionText, actual: question.Text);
        }

        private static IEnumerable QuestionCases
        {
            get
            {
                var nl = Environment.NewLine;
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", @"x", @"xyz");
                yield return new TestCaseData("form NameForm { qname : \"this is a question\"  boolean }", @"qname", @"this is a question");
                yield return new TestCaseData(
                    $"form NameForm {{ {nl} qname2 : \"this is a question too\"  boolean{nl}  }} ",
                    @"qname2",
                    @"this is a question too");
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    qname3 : \"this is a question three\" boolean{nl}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    qname3 : \"this is a question three\" boolean{nl}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    qname3 : \"this is a question three\" boolean{nl}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData("form NameForm { x: \"xyz\" boolean }", @"x", @"xyz");
                yield return new TestCaseData("form NameForm { \"xyz\"  x: boolean }", @"x", @"xyz");
                yield return new TestCaseData($"form NameForm {{ \"xyz\" {nl} x: boolean {nl} \"xxx\" {nl} y: boolean {nl}}}", @"x", @"xyz");
            }
        }

        [TestCaseSource(nameof(MultipleQuestionCases))]
        public void WhenGivenMultipleQuestions_CorrectNumberOfQuestions(string validText, int questionCount)
        {
            var createdForm = CreateForm(validText);
            Assert.AreEqual(expected: questionCount, actual: createdForm.Statements.Count);
        }

        private static IEnumerable MultipleQuestionCases
        {
            get
            {
                var nl = Environment.NewLine;
                yield return new TestCaseData("form NameForm { x : \"xyz\" boolean }", 1);
                yield return new TestCaseData("form NameForm { qname : \"this is a question\" boolean }", 1);
                yield return new TestCaseData($"form NameForm {{ {nl} qname2 : \"this is a question too\"  boolean{nl} }} ", 1);
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    qname3 : \"this is a question three\"  boolean{nl}    qname4 : \"this is a question four\"  boolean }} ",
                    2);
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    x : \"xyz\"  boolean{nl}    y : \"yzx\"  boolean{nl}    z : \"zxy\"  boolean }} ",
                    3);
            }
        }

        [TestCaseSource(nameof(TypeCases))]
        public void WhenQuestionsHasType_CorrectTypeOnQuestions(string validText, Type expectedType)
        {
            var createdForm = CreateForm(validText);
            var actualType = createdForm.Statements.OfType<IQuestionAst>().FirstOrDefault()?.Type;
            Assert.AreEqual(expected: expectedType, actual: actualType);
        }

        private static IEnumerable TypeCases
        {
            get
            {
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", typeof(bool));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  string }", typeof(string));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  integer }", typeof(int));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  date }", typeof(DateTime));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  decimal }", typeof(decimal));
            }
        }

        [TestCaseSource(nameof(ConditionalStatementCases))]
        public void WhenFormHasConditionalStatement_CorrectNumberOfConditionalCasesExist(string validText, int conditionCount)
        {
            var createdForm = CreateForm(validText);
            var actualCount = createdForm.Statements.Flatten().OfType<IConditionalAst>().Count();
            Assert.AreEqual(expected: conditionCount, actual: actualCount);
        }

        private static IEnumerable ConditionalStatementCases
        {
            get
            {
                var nl = Environment.NewLine;
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", 0);
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    x : \"xyz\"  boolean{nl}    if (x) {{{nl}    z : \"zxy\"  boolean }} }} ",
                    1);
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    x : \"xyz\"  boolean{nl}    if (x) {{{nl}    z : \"zxy\"  boolean{nl}    if (z) {{{nl}    a : \"aaa\"  boolean }} }} }} ",
                    2);
            }
        }


        [TestCaseSource(nameof(QuestionDuplicatesCases))]
        public void WhenDuplicateQuestionId_ThrowsAnError(string invalidText, string duplicateName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            try
            {
                questionnaireCreator.Create(invalidText);
            }
            catch (QlParserException exception)
            {
                Assert.IsTrue(exception.ParseErrorDetails.Contains(duplicateName));
                return;
            }

            Assert.Fail("Should have thrown an exception");
        }

        private static IEnumerable QuestionDuplicatesCases
        {
            get
            {
                var nl = Environment.NewLine;
                yield return new TestCaseData($"form NameForm {{     x : \"xyz\"  boolean {nl}    x : \"123\"  boolean  }}", "x");
                yield return new TestCaseData($"form NameForm {{     y : \"xyz\"  boolean {nl}    x : \"123\"  boolean  {nl}    y : \"123\"  boolean  }}", "y");
                yield return new TestCaseData($"form NameForm {{     x : \"xyz\"  boolean {nl}    z : \"123\"  boolean  {nl}    z : \"123\"  boolean  }}", "z");
                yield return new TestCaseData(
                    $"form NameForm {{{nl}    aName : \"xyz\"  boolean{nl}    if (aName) {{{nl}    aName : \"zxy\"  boolean }} }} ", "aName");
            }
        }
    }

    public static class TestHelperExtensions
    {
        public static IEnumerable<IAstNode> Flatten(this IEnumerable<IAstNode> e)
        {
            return e.SelectMany(c => c.Statements.Flatten()).Concat(e);
        }
    }
}