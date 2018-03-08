//        [TestCaseSource(typeof(TestData), nameof(TestData.CalculationQuestionCases))]
//        public void WhenQuestionIsCalculation_ParsesCorrectly(
//            string validText,
//            IEnumerable<string> expectedDefinitions,
//            IEnumerable<decimal> expectedNumbers,
//            IEnumerable<string> expectedVariableNames)
//        {
//            CreateForm(validText);
//            //var actualDefinitions = m_domainItemLocator
//            //    .GetAll<ICalculationNode>()
//            //    .Select(x => x.CalculationDefinition)
//            //    .ToList();

//            //foreach (var expectedDefinition in expectedDefinitions)
//            //{
//            //    Assert.Contains(expected: expectedDefinition, actual: actualDefinitions);
//            //}

//            //var actualNumbers = m_domainItemLocator
//            //    .GetAll<INumberNode>()
//            //    .Select(x => x.Value)
//            //    .ToList();

//            //foreach (var expectedNumber in expectedNumbers)
//            //{
//            //    Assert.Contains(expected: expectedNumber, actual: actualNumbers);
//            //}

//            //var actualVariableNames = m_domainItemLocator
//            //    .GetAll<ICalculationVariableNode>()
//            //    .Select(x => x.VariableName)
//            //    .ToList();

//            //foreach (var expectedVariableName in expectedVariableNames)
//            //{
//            //    Assert.Contains(expected: expectedVariableName, actual: actualVariableNames);
//            //}
//        }

//        [TestCaseSource(typeof(TestData), nameof(TestData.ComparisonConditional))]
//        public void WhenComparisonUsedInAConditional_ParsesCorrectly(string validText, IEnumerable<string> booleanNames)
//        {
//            CreateForm(validText);
//            var questionNames = m_domainItemLocator
//                .GetAll<IConditionalStatementNode>()
//                .Select(x => x.Definition)
//                .ToList();

//            foreach (var expectedName in booleanNames)
//            {
//                Assert.Contains(expected: expectedName, actual: questionNames);
//            }
//        }
//    }
//}