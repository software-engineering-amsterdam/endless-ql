using System;
using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Output.Nodes;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QlsTransformer.Domain.Output.Tools
{
    internal class QuestionStyleVisitor : IQuestionStyleVisitor
    {
        private Dictionary<Type, StyleStack> StyleStacks { get; } = new Dictionary<Type, StyleStack>();

        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IStyledOutputItemFactory m_styledOutputItemFactory;
        private readonly IStyleFactory m_styleFactory;

        public QuestionStyleVisitor(
            IDomainItemLocator domainItemLocator,
            IStyledOutputItemFactory styledOutputItemFactory,
            IStyleFactory styleFactory)
        {
            m_domainItemLocator = domainItemLocator;
            m_styledOutputItemFactory = styledOutputItemFactory;
            m_styleFactory = styleFactory;

            StyleStacks.Add(typeof(IntegerQuestionType), new StyleStack(m_styleFactory.CreateIntegerBaseStyle()));
            StyleStacks.Add(typeof(DecimalQuestionType), new StyleStack(m_styleFactory.CreateDecimalBaseStyle()));
            StyleStacks.Add(typeof(DateQuestionType), new StyleStack(m_styleFactory.CreateDateBaseStyle()));
            StyleStacks.Add(typeof(StringQuestionType), new StyleStack(m_styleFactory.CreateStringBaseStyle()));
            StyleStacks.Add(typeof(BooleanQuestionType), new StyleStack(m_styleFactory.CreateBoolBaseStyle()));
        }

        public DomainId<IStyledQuestionnaireOutputItem> Build(
            DomainId<IStyleSheetRootNode> node)
        {
            dynamic d = node;
            this.Visit(d);
            return m_domainItemLocator
                .GetAllRefs<IStyledQuestionnaireOutputItem>()
                .FirstOrDefault();
        }

        private DomainId<IPagesOutputItem> Visit(DomainId<IPageNode> pageNodeId)
        {
            var pageNode = pageNodeId
                .ToDomainItem(m_domainItemLocator);

            UpdateDefaults(pageNode);

            var sections = pageNode
                .Sections
                .Select(Visit)
                .ToList();

            var page = m_styledOutputItemFactory.CreatePage(pageNode.Name, sections);
            PopDefaults();
            return page;
        }

        private DomainId<ISectionOutputItem> Visit(DomainId<ISectionNode> sectionId)
        {
            var sectionNode = sectionId
                .ToDomainItem(m_domainItemLocator);

            UpdateDefaults(sectionNode);

            var questions = sectionNode
                .Questions
                .Select(Visit)
                .ToList();

            var section = m_styledOutputItemFactory.CreateSection(sectionNode.Name, questions);
            PopDefaults();
            return section;
        }

        private DomainId<IStyledQuestionOutputItem> Visit(
            DomainId<IQlsQuestionNode> questionId)
        {
            var questionNode = questionId
                .ToDomainItem(m_domainItemLocator);

            var questionStyle = questionNode
                .Style
                ?.ToDomainItem(m_domainItemLocator);

            var question = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault(x => x.QuestionName == questionNode.Name);

            Style defaultStyle = GetStyleDefaultForType(question.QuestionType);
            var style = m_styleFactory.CreateMergedStyle(defaultStyle, questionStyle);

            var section = m_styledOutputItemFactory.CreateQuestion(question, style);
            return section;
        }

        private Style GetStyleDefaultForType(IQuestionType questionType)
        {
            var styleStack = StyleStacks[questionType.GetType()];
            return styleStack.PeekStyle();    throw new ArgumentException(nameof(questionType),$"unknown");
        }

        private void UpdateDefaults(IStyleSheetCompartment compartment)
        {
            StyleStacks[typeof(IntegerQuestionType)]
                .PushStyle(compartment
                    .IntegerStyle
                    ?.ToDomainItem(m_domainItemLocator));

            StyleStacks[typeof(DecimalQuestionType)]
                .PushStyle(compartment
                    .DecimalStyle
                    ?.ToDomainItem(m_domainItemLocator));

            StyleStacks[typeof(DateQuestionType)]
                .PushStyle(compartment
                    .DateStyle
                    ?.ToDomainItem(m_domainItemLocator));

            StyleStacks[typeof(StringQuestionType)]
                .PushStyle(compartment
                    .StringStyle
                    ?.ToDomainItem(m_domainItemLocator));

            StyleStacks[typeof(BooleanQuestionType)]
                .PushStyle(compartment
                    .BooleanStyle
                    ?.ToDomainItem(m_domainItemLocator));
        }

        private void PopDefaults()
        {
            foreach (var styleStack in StyleStacks.Values)
            {
                styleStack.PopStyle();
            }
        }

        private void Visit(DomainId<IStyleSheetRootNode> styleSheetNodeId)
        {
            var styleSheetNode = styleSheetNodeId
                .ToDomainItem(m_domainItemLocator);

            UpdateDefaults(styleSheetNode);

            var pages = styleSheetNode
                .Pages
                .Select(Visit)
                .ToList();

            m_styledOutputItemFactory.CreateRoot(styleSheetNode.Name, pages);
            PopDefaults();
        }
    }
}
