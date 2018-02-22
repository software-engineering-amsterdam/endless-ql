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
        private static readonly string NewLine = Environment.NewLine;

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
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", @"x", @"xyz");
                yield return new TestCaseData("form NameForm { qname : \"this is a question\"  boolean }", @"qname", @"this is a question");
                yield return new TestCaseData(
                    $"form NameForm {{ {NewLine} qname2 : \"this is a question too\"  boolean{NewLine}  }} ",
                    @"qname2",
                    @"this is a question too");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData("form NameForm { x: \"xyz\" boolean }", @"x", @"xyz");
                yield return new TestCaseData("form NameForm { \"xyz\"  x: boolean }", @"x", @"xyz");
                yield return new TestCaseData($"form NameForm {{ \"xyz\" {NewLine} x: boolean {NewLine} \"xxx\" {NewLine} y: boolean {NewLine}}}", @"x", @"xyz");
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
                yield return new TestCaseData("form NameForm { x : \"xyz\" boolean }", 1);
                yield return new TestCaseData("form NameForm { qname : \"this is a question\" boolean }", 1);
                yield return new TestCaseData($"form NameForm {{ {NewLine} qname2 : \"this is a question too\"  boolean{NewLine} }} ", 1);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\"  boolean{NewLine}    qname4 : \"this is a question four\"  boolean }} ",
                    2);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    x : \"xyz\"  boolean{NewLine}    y : \"yzx\"  boolean{NewLine}    z : \"zxy\"  boolean }} ",
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
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", 0);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    x : \"xyz\"  boolean{NewLine}    if (x) {{{NewLine}    z : \"zxy\"  boolean }} }} ",
                    1);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    x : \"xyz\"  boolean{NewLine}    if (x) {{{NewLine}    z : \"zxy\"  boolean{NewLine}    if (z) {{{NewLine}    a : \"aaa\"  boolean }} }} }} ",
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
                yield return new TestCaseData($"form NameForm {{     x : \"xyz\"  boolean {NewLine}    x : \"123\"  boolean  }}", "x");
                yield return new TestCaseData($"form NameForm {{     y : \"xyz\"  boolean {NewLine}    x : \"123\"  boolean  {NewLine}    y : \"123\"  boolean  }}", "y");
                yield return new TestCaseData($"form NameForm {{     x : \"xyz\"  boolean {NewLine}    z : \"123\"  boolean  {NewLine}    z : \"123\"  boolean  }}", "z");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    aName : \"xyz\"  boolean{NewLine}    if (aName) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "aName");
            }
        }

        [TestCaseSource(nameof(NonBooleanConditional))]
        public void WhenANonBooleanQuestionIsUsedInAConditional_ThrowsAnError(string invalidText, string nonBooleanName)
        {
            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            try
            {
                questionnaireCreator.Create(invalidText);
            }
            catch (QlParserException exception)
            {
                Assert.IsTrue(exception.ParseErrorDetails.Contains(nonBooleanName));
                return;
            }

            Assert.Fail("Should have thrown an exception");
        }

        private static IEnumerable NonBooleanConditional
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    stringQuestion : \"xyz\"  string{NewLine}    if (stringQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "stringQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    integerQuestion : \"xyz\"  integer{NewLine}    if (integerQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "integerQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    decimalQuestion : \"xyz\"  decimal{NewLine}    if (decimalQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "decimalQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    dateQuestion : \"xyz\"  date{NewLine}    if (dateQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "dateQuestion");
            }
        }

        [TestCaseSource(nameof(BooleanConditional))]
        public void WhenBooleanQuestionUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
        {
            var createdForm = CreateForm(validText);
            var questionNames = createdForm
                .Statements
                .Flatten()
                .OfType<IConditionalAst>()
                .Select(x => x.QuestionName)
                .ToList();

            foreach (var expectedName in booleanNames)
            {
                Assert.Contains(expected: expectedName, actual: questionNames);
            }
        }

        private static IEnumerable BooleanConditional
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion1 : \"xyz\"  boolean{NewLine}    if (boolQuestion1) {{{NewLine}    aName : \"zxy\"  boolean }} {NewLine}    if (boolQuestion1) {{{NewLine}    aName2 : \"zxy\"  boolean }} }} ", new[] { "boolQuestion1" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion2 : \"xyz\"  boolean{NewLine}    if (boolQuestion2) {{{NewLine}    aName : \"zxy\"  boolean {NewLine}    if (aName) {{{NewLine}    aName2 : \"zxy\"  boolean }}  }} }} ", new[] { "boolQuestion2", "aName" });
            }
        }

        [TestCaseSource(nameof(ComparisonConditional))]
        public void WhenComparisonUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
        {
            var createdForm = CreateForm(validText);
            var questionNames = createdForm
                .Statements
                .Flatten()
                .OfType<IConditionalAst>()
                .Select(x => x.QuestionName)
                .ToList();

            foreach (var expectedName in booleanNames)
            {
                Assert.Contains(expected: expectedName, actual: questionNames);
            }
        }


        private static IEnumerable ComparisonConditional
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion == true) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion == True) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion == TRUE) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion == false) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion == False) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion == FALSE) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion != TRUE) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion != false) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
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