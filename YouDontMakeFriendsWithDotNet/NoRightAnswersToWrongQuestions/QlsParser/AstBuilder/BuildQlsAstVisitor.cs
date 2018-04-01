using System;
using System.Linq;
using QlsGrammar;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Ast.Tools;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsParser.AstBuilder
{
    public class BuildQlsAstVisitor : QlsBaseVisitor<DomainId<IAstNode>>
    {
        private readonly IQlsAstFactory m_astFactory;
        private readonly IDomainItemLocator m_domainItemLocator;

        public BuildQlsAstVisitor(
            IQlsAstFactory astFactory,
            IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
        }

        public override DomainId<IAstNode> VisitStyleSheet(
            QlsGrammar.QlsParser.StyleSheetContext context)
        {
            var definition = context.GetText();
            var styleSheetName = context.styleSheetName.Text;
            var defaultStyles = context.defaultStyle()
                .Select(CreateDefaultStyle)
                .ToList();

            var pages = context.page()
                .Select(Visit)
                .To<IPageNode>(m_domainItemLocator);

            return m_astFactory.CreateStyleSheet(
                definition,
                styleSheetName,
                defaultStyles,
                pages);
        }

        private IDefaultStyle CreateDefaultStyle(
            QlsGrammar.QlsParser.DefaultStyleContext defaultStyleContext)
        {
            var style = VisitStyle(defaultStyleContext.style()).To<IStyleNode>(m_domainItemLocator);
            var type = GetAstType(defaultStyleContext.type().GetText());
            return new DefaultStyle(type, style);
        }

        private Type GetAstType(string typeString)
        {
            switch (typeString)
            {
                case "integer" : return typeof(int);
                case "decimal": return typeof(decimal);
                case "date": return typeof(DateTime);
                case "boolean": return typeof(bool);
                case "string": return typeof(string);
                default: throw new ArgumentException(nameof(typeString),$"UnknownType {typeString}");
            }
        }
        
        public override DomainId<IAstNode> VisitStyle(
            QlsGrammar.QlsParser.StyleContext context)
        {
            var definition = context.GetText();

            var widget = CreateWidget(context);
            var width = GetWidth(context);
            var fontSize = GetFontSize(context);
            var font = GetFont(context);
            var color = GetColor(context);
            
            return m_astFactory.CreateStyle(
                definition,
                widget,
                width,
                fontSize,
                font,
                color);
        }

        private string GetColor(QlsGrammar.QlsParser.StyleContext context)
        {
            return context
                .stylePart()
                .FirstOrDefault(x => x.color != null)
                ?.color
                .Text;
        }

        private string GetFont(QlsGrammar.QlsParser.StyleContext context)
        {
            return context
                .stylePart()
                .FirstOrDefault(x => x.fontname != null)
                ?.fontname
                .Text
                .Replace("\"", "");
        }

        private decimal? GetFontSize(QlsGrammar.QlsParser.StyleContext context)
        {
            var size = context
                .stylePart()
                .FirstOrDefault(x => x.fontsize != null)
                ?.fontsize
                .Text;

            return size != null ?  (decimal?)decimal.Parse(size) : null ;
        }

        private int? GetWidth(QlsGrammar.QlsParser.StyleContext context)
        {
            var width = context
                .stylePart()
                .FirstOrDefault(x => x.width != null)
                ?.width
                .Text;

            return width != null ? (int?)int.Parse(width) : null;
        }

        private IWidget CreateWidget(QlsGrammar.QlsParser.StyleContext context)
        {
            var chosenWidget = context
                .stylePart()
                .FirstOrDefault(x => x.widget != null)
                ?.widget
                .controlType();
            
            return CreateWidget(chosenWidget);
        }

        private static IWidget CreateWidget(QlsGrammar.QlsParser.ControlTypeContext chosenWidget)
        {
            if (chosenWidget == null)
            {
                return null;
            }

            if (chosenWidget.CHECKBOX() != null)
            {
                return new AstCheckBox();
            }

            if (chosenWidget.TEXTBOX() != null)
            {
                return new AstTextBox();
            }

            if (chosenWidget.NUMERICUPDOWN() != null)
            {
                return new AstSpinBox();
            }

            if (chosenWidget.RADIOBUTTON() != null)
            {
                return CreateRadioButton(chosenWidget);
            }

            if (chosenWidget.COMBOBOX() != null)
            {
                return CreateDropDown(chosenWidget);
            }

            if (chosenWidget.TRACKBAR() != null)
            {
                return CreateSlider(chosenWidget);
            }

            return null;
        }

        private static AstSlider CreateSlider(QlsGrammar.QlsParser.ControlTypeContext sliderWidgetContext)
        {
            if (sliderWidgetContext.sliderRange() == null)
            {
                return new AstSlider(0, 100, 1);
            }

            return new AstSlider(
                int.Parse(sliderWidgetContext.sliderRange().rangeStart.Text),
                int.Parse(sliderWidgetContext.sliderRange().rangeEnd.Text),
                int.Parse(sliderWidgetContext.sliderRange().step.Text));
        }

        private static AstDropDown CreateDropDown(QlsGrammar.QlsParser.ControlTypeContext dropdownWidget)
        {
            return new AstDropDown(
                GetTrueText(dropdownWidget),
                GetFalseText(dropdownWidget));
        }

        private static AstRadioButton CreateRadioButton(QlsGrammar.QlsParser.ControlTypeContext radioWidget)
        {
            return new AstRadioButton(
                GetTrueText(radioWidget),
                GetFalseText(radioWidget));
        }

        private static string GetFalseText(QlsGrammar.QlsParser.ControlTypeContext chosenWidget)
        {
            return chosenWidget
                       .trueFalseText()
                       ?.falseText
                       .Text
                       .Replace("\"", "") ?? "false";
        }

        private static string GetTrueText(QlsGrammar.QlsParser.ControlTypeContext chosenWidget)
        {
            return chosenWidget
                       .trueFalseText()
                       ?.trueText
                       .Text
                       .Replace("\"", "") ?? "true";
        }

        public override DomainId<IAstNode> VisitPage(
            QlsGrammar.QlsParser.PageContext context)
        {
            var definition = context.GetText();
            var pageNameText = context.pageName.Text;
            var defaultStyles = context.defaultStyle()
                .Select(CreateDefaultStyle)
                .ToList();

            var sections = context.section()
                .Select(Visit)
                .To<ISectionNode>(m_domainItemLocator);
            
            return m_astFactory.CreatePage(
                definition,
                pageNameText,
                defaultStyles,
                sections);
        }

        public override DomainId<IAstNode> VisitSection(QlsGrammar.QlsParser.SectionContext context)
        {
            var definition = context.GetText();
            var sectionNameText = context.sectionName.Text;
            var defaultStyles = context.defaultStyle()
                .Select(CreateDefaultStyle)
                .ToList();

            var questions = context.question()
                .Select(Visit)
                .To<IQlsQuestionNode>(m_domainItemLocator);

            return m_astFactory.CreateSection(
                definition,
                sectionNameText,
                defaultStyles,
                questions);
        }

        public override DomainId<IAstNode> VisitQuestion(QlsGrammar.QlsParser.QuestionContext context)
        {
            var definition = context.GetText();
            var questionNameText = context.questionName.Text;
            var styleContext = context.style();
            DomainId<IStyleNode> styleNode = null;
            if (styleContext != null)
            {
                styleNode = Visit(context.style()).To<IStyleNode>(m_domainItemLocator);
            }

            return m_astFactory.CreateQuestion(
                definition,
                questionNameText,
                styleNode);
        }
    }
}