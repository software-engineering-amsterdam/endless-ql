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


        public ElementManagerLeaf(string identifyer, string text, string xmlName, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, xmlName, controller, activationExpression)
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
    }
}
