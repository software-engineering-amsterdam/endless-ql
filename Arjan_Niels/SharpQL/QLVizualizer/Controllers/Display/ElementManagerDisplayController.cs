using System.Collections.Generic;
using QLVisualizer.Elements.Managers.CollectionTypes;

namespace QLVisualizer.Controllers.Display
{
    /// <summary>
    /// 
    /// </summary>
    /// <typeparam name="T">View-element Type</typeparam>
    public abstract class WidgetDisplayController<T> : ElementManagerController
    {
        /// <summary>
        /// X-Position of first widget
        /// </summary>
        public float InitialPosition { get; private set; }

        /// <summary>
        /// Dictonary of all created elements, key: WidgetID, Value: element
        /// </summary>
        public Dictionary<string, T> ElementIndex { get; private set; }

        protected T BaseDisplay;

        public WidgetDisplayController(FormManager form, float initialPosition) : base(form)
        {
            InitialPosition = initialPosition;
            ElementIndex = new Dictionary<string, T>();
        }

        /// <summary>
        /// Shows form to user
        /// </summary>
        /// <param name="title">Tile of form</param>
        /// <param name="widgets">Widgets on form</param>
        public override void DisplayForm()
        {
            UpdateBaseDisplay(CreateFormWidget());
            Form.RegisterListeners();
        }

        protected abstract T CreateFormWidget();

        protected abstract void UpdateBaseDisplay(T newDisplay);

        /// <summary>
        /// Resets all values that define its state
        /// </summary>
        public override void Reset()
        {
            ElementIndex = new Dictionary<string, T>();
            //ElementStyleIndex = new Dictionary<string, Y>();
        }
    }
}
