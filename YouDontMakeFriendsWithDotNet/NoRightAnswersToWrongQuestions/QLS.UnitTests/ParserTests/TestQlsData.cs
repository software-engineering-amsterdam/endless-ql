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

            }
        }
    }
}