using System;
using Moq;
using NUnit.Framework;
using QuestionnaireInfrastructure.API;
using TechTalk.SpecFlow;

namespace IntegrationTests.Steps
{
    [Binding]
    public class BadFormDescriptionDisplaysErrorsSteps
    {
        
        private IAppSettingService m_settingService;
        private IDefinitionFileReaderService m_fileReaderService;

        [Given(@"I am using file input of the Questionnaire")]
        public void GivenIAmUsingFileInputOfTheQuestionnaire()
        {
            var appSettings = new AppSettings(loadDefinitionFromFile: true);
            var mockSettingService = new Mock<IAppSettingService>();
            mockSettingService
                .Setup(x => x.GetSettings())
                .Returns(appSettings);

            m_settingService = mockSettingService.Object;
        }

        [Given(@"That the input description is")]
        public void GivenThatTheInputDescriptionIs(Table table)
        {
            var mockFileReaderService = new Mock<IDefinitionFileReaderService>();
            mockFileReaderService
                .Setup(x => x.Read(It.IsAny<string>()))
                .Returns("Rhubarb");

            m_fileReaderService = mockFileReaderService.Object;
        }


        [When(@"I start the app")]
        public void WhenIStartTheApp()
        {
        }

        [Then(@"the result should be an appropriate error view")]
        public void ThenTheResultShouldBeAnAppropriateErrorView()
        {
            Assert.True(true);
        }
    }
}