using System;
using System.Collections;
using NUnit.Framework;

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
            }
        }
    }
}