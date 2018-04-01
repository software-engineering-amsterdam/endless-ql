using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators.CorrectWidgetForDefaults
{
    public class CorrectWidgetValidator : ICorrectWidgetValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public CorrectWidgetValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IStyleSheetRootNode> rootNode)
        {
            var styleSheet = m_domainItemLocator
                .GetAll<IStyleSheetRootNode>()
                .FirstOrDefault();

            if (!AllWidgetsValid(styleSheet))
            {
                yield return new CorrectWidgetValidationMetaData
                {
                    Source = m_domainItemLocator.GetRef<IStyleSheetRootNode>(styleSheet.Id),
                    Message = $@"The stylesheet '{styleSheet.Name}' has an invalid widget"
                };
            }

            var pages = m_domainItemLocator
                .GetAll<IPageNode>()
                .ToList();

            foreach (var page in pages)
            {
                if (AllWidgetsValid(page))
                {
                    continue;
                }

                yield return new CorrectWidgetValidationMetaData
                {
                    Source = m_domainItemLocator.GetRef<IPageNode>(page.Id),
                    Message = $@"The page '{page.Name}' has an invalid widget"
                };
            }
            
            var sections = m_domainItemLocator
                .GetAll<ISectionNode>()
                .ToList();

            foreach (var section in sections)
            {
                if (AllWidgetsValid(section))
                {
                    continue;
                }

                yield return new CorrectWidgetValidationMetaData
                {
                    Source = m_domainItemLocator.GetRef<ISectionNode>(section.Id),
                    Message = $@"The section '{section.Name}' has an invalid widget"
                };
            }
        }

        private bool AllWidgetsValid(IStyleSheetCompartment compartment)
        {
            var booleanWidget = compartment
                .BooleanStyle
                ?.ToDomainItem(m_domainItemLocator)
                .Widget;

            if (booleanWidget!=null && 
                booleanWidget.GetType() != typeof(AstDropDown) &&
                booleanWidget.GetType() != typeof(AstRadioButton) &&
                booleanWidget.GetType() != typeof(AstCheckBox))
            {
                return false;
            }

            var stringWidget = compartment
                .StringStyle
                ?.ToDomainItem(m_domainItemLocator)
                .Widget;

            if (stringWidget != null &&
                stringWidget.GetType() != typeof(AstTextBox))
            {
                return false;
            }

            var dateWidget = compartment
                .DateStyle
                ?.ToDomainItem(m_domainItemLocator)
                .Widget;

            if (dateWidget != null && 
                dateWidget.GetType() != typeof(AstTextBox) &&
                dateWidget.GetType() != typeof(AstDatePicker))
            {
                return false;
            }

            var integerWidget = compartment
                .IntegerStyle
                ?.ToDomainItem(m_domainItemLocator)
                .Widget;

            if (integerWidget != null &&
                integerWidget.GetType() != typeof(AstSlider) &&
                integerWidget.GetType() != typeof(AstSpinBox))
            {
                return false;
            }

            var decimalWidget = compartment
                .DecimalStyle
                ?.ToDomainItem(m_domainItemLocator)
                .Widget;

            if (decimalWidget != null &&
                decimalWidget.GetType() != typeof(AstSlider) &&
                decimalWidget.GetType() != typeof(AstSpinBox))
            {
                return false;
            }

            return true;

        }
    }
}