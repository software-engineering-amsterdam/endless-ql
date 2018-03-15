using System;
using NUnit.Framework;
using QuestionnaireUI.Models;

namespace UnitTests.UI.UnitTests
{
    [TestFixture]
    public class ChangeNotificationTests
    {
        private QuestionModel m_inputQuestion1;

        [SetUp]
        public void Init()
        {
            m_inputQuestion1 = new QuestionModel(
                new Guid("40E98F85-949C-48F0-8194-CECEEBD0177F"),
                "int input question",
                true,
                false,
                typeof(int));
        }

        [Test]
        public void WhenPropertyChanged_ShouldRaisePropertyChangedEvent()
        {
            var propertyEventWasRaised = false;
            var wrapper = new QuestionWrapper(m_inputQuestion1);
            wrapper.PropertyChanged += (s, e) =>
            {
                if (e.PropertyName == "Value")
                {
                    propertyEventWasRaised = true;
                }
            };

            wrapper.Value = "999";
            Assert.IsTrue(propertyEventWasRaised);
        }
    }
}
