//using System;
//using System.Collections.Generic;
//using System.Linq;
//using AntlrInterpretor;
//using Microsoft.Extensions.DependencyInjection;
//using Moq;
//using NUnit.Framework;
//using NUnit.Framework.Internal;
//using QuestionaireDomain.Entities.API;
//using QuestionaireDomain.Entities.API.AstNodes;
//using QuestionaireDomain.Entities.API.AstNodes.Calculation;
//using QuestionnaireDomain.Logic;
//using QuestionnaireDomain.Logic.API;
//using QuestionnaireInfrastructure.API;

//namespace UnitTests.Domain.UnitTests
//{
//    [TestFixture]
//    public class MathEvaluatorTests
//    {
//        private IServiceProvider m_serviceProvider;
//        private IList<Guid> m_ids = new List<Guid>();

//        [SetUp]
//        public void Init()
//        {
//            var services = new ServiceCollection();
//            services.AddModule(new AntlrModule());
//            services.AddModule(new DomainLogicModule());
//            var idMakerMock = new Mock<IIdMaker>();
//            var seq = idMakerMock.SetupSequence(x => x.Next);
//            for (var i = 0; i < 100; i++)
//            {
//                var id = Guid.NewGuid();
//                m_ids.Add(id);
//                seq.Returns(id);
//            }
//            services.AddSingleton(typeof(IIdMaker), idMakerMock.Object);
//            m_serviceProvider = services.BuildServiceProvider();
//        }

//        [Test]
//        public void WhenGivenSingleNumber_ReturnsSameNumber()
//        {
//            var questionnaireCreator = m_serviceProvider.GetService<IQuestionnaireCreator>();
//            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
//            var validText = "form NameForm { x: \"xyz\" integer = (1 + 1) }";
//            questionnaireCreator.Create(validText);
//            var value = domainItemLocator
//                .GetAll<INumberNode>()
//                .FirstOrDefault()
//                ?.Value;

//            Assert.IsNotNull(value);
//            Assert.AreEqual(expected: 1,actual: value);
//        }
//    }
//}
