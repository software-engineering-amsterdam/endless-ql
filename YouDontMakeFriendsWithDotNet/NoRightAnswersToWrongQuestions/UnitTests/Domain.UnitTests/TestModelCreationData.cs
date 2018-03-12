using System;
using System.Collections;
using System.Globalization;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;

namespace UnitTests.Domain.UnitTests
{
    public static class TestModelCreationData
    {
        private static readonly string NewLine = Environment.NewLine;

        public static IEnumerable SimpleQuestionnaireCases
        {
            get
            {
                var formTemplate = "form {0} {{ q: \"i\"  date}}";
                yield return new TestCaseData(
                    string.Format(formTemplate, "John"),
                    "John");
                yield return new TestCaseData(
                    string.Format(formTemplate, "TaxAuthorityForm"),
                    "TaxAuthorityForm");
            }
        }

        public static IEnumerable QuestionTypes
        {
            get
            {
                var formTemplate = "form TestForm {{ q: \"i\"  {0}}}";
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolean"),
                    typeof(bool));

                yield return new TestCaseData(
                    string.Format(formTemplate, "string"),
                    typeof(string));

                yield return new TestCaseData(
                    string.Format(formTemplate, "decimal"),
                    typeof(decimal));

                yield return new TestCaseData(
                    string.Format(formTemplate, "integer"),
                    typeof(int));

                yield return new TestCaseData(
                    string.Format(formTemplate, "date"),
                    typeof(DateTime));
            }
        }

        public static IEnumerable DefaultQuestionValues
        {
            get
            {
                var formTemplate = "form TestForm {{ q: \"i\"  {0}}}";
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolean"),
                    default(bool).ToString());

                yield return new TestCaseData(
                    string.Format(formTemplate, "string"),
                    string.Empty);

                yield return new TestCaseData(
                    string.Format(formTemplate, "decimal"),
                    default(decimal).ToString(CultureInfo.InvariantCulture));

                yield return new TestCaseData(
                    string.Format(formTemplate, "integer"),
                    default(int).ToString(CultureInfo.InvariantCulture));

                yield return new TestCaseData(
                    string.Format(formTemplate, "date"),
                    default(DateTime).ToString(CultureInfo.InvariantCulture));
            }
        }

        public static IEnumerable IfQuestionValues
        {
            get
            {
                const string form1 = "form TestForm { if (true) { q: \"i\"  boolean } }";
                yield return new TestCaseData(form1, 1, 0);

                const string form2 = "form TestForm { if (true) { q1: \"i\"  boolean } else { q2: \"i\"  boolean } }";
                yield return new TestCaseData(form2, 1, 1);

                const string template1T2F = "form TestForm {{ if ({0}) {{ q1: \"i\"  boolean }} else {{ q2: \"i\"  boolean q3: \"i\"  boolean }} }}";
                yield return new TestCaseData(
                    string.Format(template1T2F, "false"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "False"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "FALSE"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "True"), 1, 2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "TRUE"), 1, 2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true && true"), 1, 2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true && false"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false && true"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false && false"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true || true"), 1, 2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true || false"), 1, 2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false || true"), 1, 2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false || false"), 2, 1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false || false || true"), 
                    1, 
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false || true || false "), 
                    1, 
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true || false || false"), 
                    1, 
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true && true && true"), 
                    1, 
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true && true && false"), 
                    2, 
                    1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "true && false && true"), 
                    2, 
                    1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false && true && true"), 
                    2, 
                    1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "false && true && true"),
                    2,
                    1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "(false && true) && (true && true)"),
                    2,
                    1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "(false && true) || (true && true)"),
                    1,
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "!false"),
                    1,
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "!true"),
                    2,
                    1);

                yield return new TestCaseData(
                    string.Format(template1T2F, "!(true && false)"),
                    1,
                    2);

                yield return new TestCaseData(
                    string.Format(template1T2F, "!(true || false)"),
                    2,
                    1);

            }
        }

    }
        }