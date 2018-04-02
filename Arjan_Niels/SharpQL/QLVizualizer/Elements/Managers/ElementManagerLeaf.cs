using QLParser.AST.QLS;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Collections.Generic;

namespace QLVisualizer.Elements.Managers
{
    public abstract class ElementManagerLeaf : ElementManager
    {
        public bool Editable { get; protected set; }

        public delegate void AnswerValueUpdate(ElementManagerLeaf elementManagerLeaf, bool calculated);

        public event AnswerValueUpdate OnAnswerValueUpdate;

        private QLSStyle _style;


        public ElementManagerLeaf(string identifier, string text, string xmlName, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifier, text, xmlName, controller, activationExpression)
        {
            Parent = parent;
        }

        public override IEnumerable<string> GetActivationTargetIDs()
        {
            return _activationExpression == null ? new string[0] : _activationExpression.UsedIdentifiers;
        }

        protected void TriggerAnwerUpdate(bool calculated)
        {
            OnAnswerValueUpdate?.Invoke(this, calculated);
        }

        public abstract string AnswerToString();

        public override void SetStyle(QLSStyle style)
        {
            _style = style;
        }

        public override QLSStyle GetStyle()
        {
            return _style;
        }
    }
}
