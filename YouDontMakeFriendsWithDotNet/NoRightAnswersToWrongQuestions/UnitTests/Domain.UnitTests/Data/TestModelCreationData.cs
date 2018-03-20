using System;
using System.Collections;
using System.Globalization;
using NUnit.Framework;

namespace UnitTests.Domain.UnitTests.Data
{
    public static class TestModelCreationData
    {
        public static IEnumerable SimpleQuestionnaireCases
        {
            get
            {
                yield return new TestCaseData(
                    @"form John { q: ""i""  date}",
                    @"John");

                yield return new TestCaseData(
                    @"form TaxAuthorityForm { q: ""i""  date}",
                    @"TaxAuthorityForm");
            }
        }

        public static IEnumerable QuestionTypes
        {
            get
            {
                yield return TypeResult(@"boolean", typeof(bool));
                yield return TypeResult(@"string", typeof(string));
                yield return TypeResult(@"decimal", typeof(decimal));
                yield return TypeResult(@"integer", typeof(int));
                yield return TypeResult(@"date", typeof(DateTime));
            }
        }

        public static TestCaseData TypeResult(string qlType, Type dotnetType)
        {
            var formTemplate = @"form TestForm {{ q: ""i""  {0}}}";
            return new TestCaseData(
                string.Format(formTemplate, qlType),
                dotnetType);
        }

        public static IEnumerable DefaultQuestionValues
        {
            get
            {
                yield return DetaultTypeResult(@"boolean", default(bool).ToString());
                yield return DetaultTypeResult(@"string", string.Empty);
                yield return DetaultTypeResult(@"decimal", default(decimal).ToString(CultureInfo.InvariantCulture));
                yield return DetaultTypeResult(@"integer", default(int).ToString(CultureInfo.InvariantCulture));
                yield return DetaultTypeResult(@"date", default(DateTime).ToString(CultureInfo.InvariantCulture));
            }
        }

        public static TestCaseData DetaultTypeResult(string qlType, string value)
        {
            var formTemplate = @"form TestForm {{ q: ""i""  {0}}}";
            return new TestCaseData(
                string.Format(formTemplate, qlType),
                value);
        }

        public static IEnumerable IfQuestionValues
        {
            get
            {
                const string form1 = "form TestForm { if (true) { q: \"i\"  boolean } }";
                yield return new TestCaseData(form1, 1, 0);

                const string form2 = "form TestForm { if (true) { q1: \"i\"  boolean } else { q2: \"i\"  boolean } }";
                yield return new TestCaseData(form2, 1, 1);

                yield return TrueResult(@"true");
                yield return TrueResult(@"True");
                yield return TrueResult(@"TRUE");
                yield return TrueResult(@"true && true");
                yield return TrueResult(@"true || true");
                yield return TrueResult(@"true || false");
                yield return TrueResult(@"false || true");
                yield return TrueResult(@"false || false || true");
                yield return TrueResult(@"false || true || false ");
                yield return TrueResult(@"true || false || false");
                yield return TrueResult(@"true && true && true");
                yield return TrueResult(@"(false && true) || (true && true)");
                yield return TrueResult(@"!false");
                yield return TrueResult(@"!(true && false)");
                yield return TrueResult(@"true == true");
                yield return TrueResult(@"false == false");
                yield return TrueResult(@"false != true");
                yield return TrueResult(@"""Fred"" == ""Fred""");
                yield return TrueResult(@"""Freddie"" != ""Bungle""");
                yield return TrueResult(@"1 == 1");
                yield return TrueResult(@"999 == 999");
                yield return TrueResult(@"999.25 == 999.25");
                yield return TrueResult(@"-999.25 == -999.25");
                yield return TrueResult(@"2 > 1");
                yield return TrueResult(@"2 >= 1");
                yield return TrueResult(@"1 >= 1");
                yield return TrueResult(@"1 < 2");
                yield return TrueResult(@"1 <= 2");
                yield return TrueResult(@"2 <= 2");
                yield return TrueResult(@"(1 + 1) == 2");
                yield return TrueResult(@"(1 + 1) == (0 + 2)");
                yield return TrueResult(@"2 == (3 - 1)");
                yield return TrueResult(@"(10 * 2) == (5 * 4)");
                yield return TrueResult(@"(25 / 5) == (45 / 9)");

                yield return FalseResult(@"false");
                yield return FalseResult(@"False");
                yield return FalseResult(@"FALSE");
                yield return FalseResult(@"true && false");
                yield return FalseResult(@"false && true");
                yield return FalseResult(@"false && false");
                yield return FalseResult(@"false || false");
                yield return FalseResult(@"true && true && false");
                yield return FalseResult(@"true && false && true");
                yield return FalseResult(@"false && true && true");
                yield return FalseResult(@"false && true && true");
                yield return FalseResult(@"(false && true) && (true && true)");
                yield return FalseResult(@"!true");
                yield return FalseResult(@"!(true || false)");
                yield return FalseResult(@"true == false");
                yield return FalseResult(@"false == true");
                yield return FalseResult(@"false != false");
                yield return FalseResult(@"true != true");
                yield return FalseResult(@"1 != 1");
                yield return FalseResult(@"-999.25 == 1");
                yield return FalseResult(@"-999.25 != -999.25");
                yield return FalseResult(@"1 > 2");
                yield return FalseResult(@"1 >= 2");
                yield return FalseResult(@"2 < 1");
                yield return FalseResult(@"2 <= 1");


                var sameDate = new DateTime(1973, 12, 4).ToString(@"dd/MM/yyyy");
                var sameDate_1 = new DateTime(1973, 12, 4).ToString(@"d/M/yyyy");
                var differentDate = new DateTime(1973, 12, 3).ToString(@"dd/MM/yyyy");

                yield return TrueResult($@"{sameDate} == {sameDate}");
                yield return TrueResult($@"{sameDate} == {sameDate_1}");
                yield return FalseResult($@"{sameDate} != {sameDate}");
                yield return FalseResult($@"{sameDate} != {sameDate_1}");
                yield return FalseResult($@"{sameDate} == {differentDate}");
                yield return TrueResult($@"{sameDate} != {differentDate}");
            }
        }

        public static TestCaseData FalseResult(string predicate)
        {
            const string template1T2F =
                "form TestForm {{ if ({0}) {{ q1: \"i\"  boolean }} else {{ q2: \"i\"  boolean q3: \"i\"  boolean }} }}";
            return new TestCaseData(string.Format(template1T2F, predicate), 2, 1);
        }

        public static TestCaseData TrueResult(string predicate)
        {
            const string template1T2F =
                "form TestForm {{ if ({0}) {{ q1: \"i\"  boolean }} else {{ q2: \"i\"  boolean q3: \"i\"  boolean }} }}";
            return new TestCaseData(string.Format(template1T2F, predicate), 1, 2);
        }

        private const string VariableTemplate = @"
form TestForm {{ 
   q1: ""First int question"" {1}
   q2: ""2nd int question"" {1}
   if (q1 {0} q2) 
   {{ 
      trueVisibleQuestion: ""trueviz""  integer 
   }} 
   else 
   {{ 
      falseVisibleQuestion1: ""falseViz1""  integer 
      falseVisibleQuestion2: ""falseViz2""  integer 
   }} 
}}";

        public static IEnumerable CalculationIntVariableTrueToFalseValues
        {
            get
            {

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"==", @"integer"),
                    100,
                    200);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"<=", @"integer"),
                    5,
                    4);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @">=", @"integer"),
                    -5,
                    -4);
            }
        }

        public static IEnumerable CalculationIntVariableFalseToTrueValues
        {
            get
            {
                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"!=", @"integer"),
                    100,
                    200);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"<", @"integer"),
                    444,
                    555);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @">", @"integer"),
                    123,
                    -123);
            }
        }

        public static IEnumerable CalculationDecimalVariableTrueToFalseValues
        {
            get
            {
                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"==", @"decimal"),
                    100m,
                    200m);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"<=", @"decimal"),
                    5m,
                    4m);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @">=", @"decimal"),
                    -5m,
                    -4m);
            }
        }

        public static IEnumerable CalculationDecimalVariableFalseToTrueValues
        {
            get
            {
                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"!=", @"decimal"),
                    100m,
                    200m);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"<", @"decimal"),
                    444m,
                    555m);

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @">", @"decimal"),
                    123m,
                    -123m);
            }
        }

        public static IEnumerable CalculationDateVariableTrueToFalseValues
        {
            get
            {
                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"==", @"date"),
                    new DateTime(2012, 12, 31),
                    new DateTime(2013, 1, 2));

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @">=", @"date"),
                    new DateTime(1966, 7, 30),
                    new DateTime(1974, 7, 7));

                yield return new TestCaseData(
                    string.Format(VariableTemplate, @"<=", @"date"),
                    new DateTime(2044, 10, 10),
                    new DateTime(2015, 3, 2));
            }
        }
    }
        }