using System.Collections;
using NUnit.Framework;

namespace QLS.UnitTests.ParserTests
{
    public static class TestQlsData
    {
        public static IEnumerable GoodStyleSheetCases
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
    }
}