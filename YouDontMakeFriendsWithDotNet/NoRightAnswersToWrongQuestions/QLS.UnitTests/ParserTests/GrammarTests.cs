using System;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QlsParser;
using QlsTransformer;
using QlsTransformer.Ast.Tools;
using QuestionnaireDomain.Entities;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace QLS.UnitTests.ParserTests
{
    [TestFixture]
    public class GrammarTests
    {
        private IServiceProvider m_serviceProvider;
        private IDomainItemLocator m_domainItemLocator;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new QlsParserModule());
            services.AddModule(new InfrastructureModule());
            services.AddModule(new EntitiesModule());
            services.AddModule(new QlsTransformerModule());
            m_serviceProvider = services.BuildServiceProvider();
            m_domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
        }

        [TearDown]
        public void Cleanup()
        {
            //To Do: this is a hack, should fix lifetime of service (possibly)
            var registry = m_serviceProvider.GetService<IDomainItemRegistry>();
            registry.Nuke();
        }
        
        [Test]
        public void GivenStyleSheet_ReturnsStyleSheetObject()
        {
            var styleSheetCreator = m_serviceProvider
                .GetService<IStyleSheetCreator>();

            var styleSheetDefinition = @"stylesheet ss1 { }";
            var domainItemId = styleSheetCreator.
                Create(styleSheetDefinition);

            Assert.IsNotNull(domainItemId, "should have created a stylesheet from a valid definition");
        }
    }
}
