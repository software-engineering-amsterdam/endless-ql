using System.Collections.Generic;
using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Widgets;

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

        /// <summary>
        /// Element factory that creates all elements
        /// </summary>
        //protected ElementFactory<T, Y> _elementFactory;

        /// <summary>
        /// Style for each widget, key: widgetID, value: style
        /// </summary>
        //public Dictionary<string, Y> ElementStyleIndex { get; private set; }

//        public Dictionary<string, Y> ActiveElementStyles { get; private set; }

        public WidgetCreator<T> WidgetCreator { get; private set; }

        protected T BaseDisplay;

        /// <summary>
        /// Default style in case no style is set
        /// </summary>
        //public Y DefaultStyle { get; private set; }

        public WidgetDisplayController(FormManager form, float initialPosition, WidgetCreator<T> widgetCreator) : base(form)
        {
            InitialPosition = initialPosition;
            ElementIndex = new Dictionary<string, T>();
            WidgetCreator = widgetCreator;
        }

        public abstract bool ValidateStyle(IEnumerable<IQLSElement> qlsElements);

        /*/// <summary>
        /// Set style for widgets
        /// </summary>
        /// <param name="styles">Styles by widgetID</param>
        public void SetStyles(Dictionary<string, Y> styles)
        {          
            if (ElementStyleIndex == null)      // Case no defaultstyle is set
                ElementStyleIndex = styles;
            else                                // Case defaultstyle already set
                foreach (KeyValuePair<string, Y> style in styles)
                    ElementStyleIndex[style.Key] = style.Value;
        }*/

        /// <summary>
        /// Shows form to user
        /// </summary>
        /// <param name="title">Tile of form</param>
        /// <param name="widgets">Widgets on form</param>
        public override void DisplayForm()
        {
            UpdateBaseDisplay(WidgetCreator.CreateWidget(Form, BaseDisplay));
            Form.RegisterListeners();
        }

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
