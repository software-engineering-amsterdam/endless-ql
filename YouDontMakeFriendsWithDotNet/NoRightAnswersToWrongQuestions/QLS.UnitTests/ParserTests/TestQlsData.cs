using System.Collections;
using NUnit.Framework;

namespace QLS.UnitTests.ParserTests
{
    public static class TestQlsData
    {
        public static IEnumerable EmptyStyleSheet
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { }",
                    @"ss1");

                yield return new TestCaseData(
                    @"stylesheet xyz { }",
                    @"xyz");

                yield return new TestCaseData(
                    @"stylesheet PhiL123 { }",
                    @"PhiL123");
            }
        }

        public static IEnumerable StyleSheetWithOnePage
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } }",
                    @"p1");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page anotherPage { } }",
                    @"anotherPage");
            }
        }
        
        public static IEnumerable StyleSheetWithMultiplePages
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { }}",
                    1);
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { }}",
                    2);
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { } page p3 { }}",
                    3);
            }
        }

        public static IEnumerable StyleSheetSection
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } } }",
                    @"s1");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { section s2 { } } }",
                    @"s2");
            }
        }

        public static IEnumerable MultipleStyleSheetSection
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } } }",
                    1);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } section s2 { } } }",
                    2);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } } page p2 { section s2 { } } }",
                    2);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } section s2 { } } page p2 { section s11 { } section s12 { } section s13 { } } }",
                    5);
            }
        }

        public static IEnumerable WithAQuestion
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 } } }",
                    @"q1");
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { section s2 { question q2 } } }",
                    @"q2");
            }
        }
        public static IEnumerable WithMultipleQuestions
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 question q2} } }",
                    2);
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1  { question q1 } } page p2 { section s2 { question q2 } } }",
                    2);
            }
        }

        public static IEnumerable StyleSheetWithDefaultStyle
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget checkbox}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio(""Ay"", ""Nee"") }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown(""darntootin"", ""nonononono"") }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default string widget textbox }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget slider(50, 100, 2)}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget spinbox}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal widget textbox }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { widget textbox } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { widget textbox width: 200 } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { font : ""Arial"" fontsize: 12.5 color: #00FF19BF } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { fontsize: 9.2 widget textbox width: 120 font : ""ComicSans"" color: #FFFFFFFF } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { fontsize: 5 widget textbox color: #12345678 } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { default decimal { fontsize: 5 widget textbox color: #12345678 } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 question q2 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 widget textbox question q2 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 { widget textbox fontsize: 20 } question q2 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

            }
        }
    }
}