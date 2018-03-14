using QLVisualizer.Controllers;
using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Expression
{
    public class LazyElementExpressionLink<T>
    {
        private QuestionElementManager<T> _questionElementManager;
        private ElementManagerController _elementManagerController;
        private string _questionElementManagerID;

        public T ElementValue
        {
            get
            {
                if (_questionElementManager == null)
                    _questionElementManager = _elementManagerController.Form.FindLeafsByID(_questionElementManagerID)[_questionElementManagerID] as QuestionElementManager<T>;
                return _questionElementManager.Answer.Value;
            }
        }

        public LazyElementExpressionLink(ElementManagerController elementManagerController, string questionElementManagerID)
        {
            _elementManagerController = elementManagerController;
            _questionElementManagerID = questionElementManagerID;
        }
    }
}
