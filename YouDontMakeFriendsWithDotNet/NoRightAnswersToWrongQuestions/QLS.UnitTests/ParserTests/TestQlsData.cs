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

            }
        }
    }
}