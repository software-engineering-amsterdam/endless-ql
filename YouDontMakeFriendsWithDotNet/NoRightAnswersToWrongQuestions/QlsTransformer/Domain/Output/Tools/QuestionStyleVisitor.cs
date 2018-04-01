using System;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Output.Nodes;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QlsTransformer.Domain.Output.Tools
{
    internal class QuestionStyleVisitor : IQuestionStyleVisitor
    {
        private readonly StyleStack m_integerStyle;
        private readonly StyleStack m_decimalStyle;
        private readonly StyleStack m_dateStyle; 
        private readonly StyleStack m_stringStyle;
        private readonly StyleStack m_booleanStyle;

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

            m_integerStyle = new StyleStack(m_styleFactory.CreateIntegerBaseStyle());
            m_decimalStyle = new StyleStack(m_styleFactory.CreateDecimalBaseStyle());
            m_dateStyle= new StyleStack(m_styleFactory.CreateDateBaseStyle());
            m_stringStyle = new StyleStack(m_styleFactory.CreateStringBaseStyle());
            m_booleanStyle = new StyleStack(m_styleFactory.CreateBoolBaseStyle());
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
                .ToDomainItem(m_domainItemLocator);

            var question = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault(x => x.QuestionName == questionNode.Name);

            Style defaultStyle = GetStyleDefaultForType(question.QuestionType);
            var style = m_styleFactory.CreateMergedStyle(defaultStyle, questionStyle);

            var section = m_styledOutputItemFactory.CreateQuestion(question, style);
            PopDefaults();
            return section;
        }

        private Style GetStyleDefaultForType(Type questionType)
        {
            //ToDo: replace with polymorphism!
            if (questionType == typeof(int))
            {
                return m_integerStyle.PeekStyle();
            }
            if (questionType == typeof(bool))
            {
                return m_booleanStyle.PeekStyle();
            }
            if (questionType == typeof(string))
            {
                return m_stringStyle.PeekStyle();
            }
            if (questionType == typeof(decimal))
            {
                return m_decimalStyle.PeekStyle();
            }
            if (questionType == typeof(DateTime))
            {
                return m_dateStyle.PeekStyle();
            }

            throw new ApplicationException("Unknown type");
        }

        private void UpdateDefaults(IStyleSheetCompartment compartment)
        {
            m_integerStyle.PushStyle(compartment.IntegerStyle?.ToDomainItem(m_domainItemLocator));
            m_decimalStyle.PushStyle(compartment.DecimalStyle?.ToDomainItem(m_domainItemLocator));
            m_dateStyle.PushStyle(compartment.DateStyle?.ToDomainItem(m_domainItemLocator));
            m_stringStyle.PushStyle(compartment.StringStyle?.ToDomainItem(m_domainItemLocator));
            m_booleanStyle.PushStyle(compartment.BooleanStyle?.ToDomainItem(m_domainItemLocator));
        }

        private void PopDefaults()
        {
            m_integerStyle.PopStyle();
            m_decimalStyle.PopStyle();
            m_dateStyle.PopStyle();
            m_stringStyle.PopStyle();
            m_booleanStyle.PopStyle();
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
