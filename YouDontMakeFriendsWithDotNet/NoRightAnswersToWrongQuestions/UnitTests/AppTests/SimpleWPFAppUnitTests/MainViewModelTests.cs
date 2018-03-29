using System;
using Moq;
using NUnit.Framework;
using QuestionnaireOrchestration.Models;
using QuestionnaireUI.Models;
using SimpleWPFApp;
using SimpleWPFApp.DataProvider;

namespace QL.UnitTests.AppTests.SimpleWPFAppUnitTests
{
    [TestFixture]
    public class MainViewModelTests
    {
        [Test]
        public void WhenProvidedData_ShouldLoadQuestionnaire()
        {
            var model = new QuestionnaireModel(Guid.NewGuid(), "");
            var qmodel1 = new QuestionModel(Guid.NewGuid(), Guid.NewGuid(), "1", true, false, typeof(bool));
            var qmodel2 = new QuestionModel(Guid.NewGuid(), Guid.NewGuid(), "2", true, false, typeof(bool));
            model.Questions.Add(qmodel1);
            model.Questions.Add(qmodel2);
            var wrappedModel = new QuestionnaireWrapper(model);
            var dataProviderMock = new Mock<IQuestionnaireDataProvider>();
            dataProviderMock
                .Setup(x => x.GetSingleQuestionnaire())
                .Returns(wrappedModel);

            var viewModel = new QuestionnaireViewModel(dataProviderMock.Object);
            viewModel.Load();

            Assert.AreEqual(2, viewModel.Questionnaire.Questions.Count);
        }

    }
}
