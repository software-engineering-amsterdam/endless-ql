﻿using QL_Vizualizer.Factories;
using QL_Vizualizer.Widgets;
using System.Collections.Generic;

namespace QL_Vizualizer.Controllers.Display
{
    public abstract class WidgetDisplayController<T,Y>
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
        protected ElementFactory<T,Y> _elementFactory { get; private set; }

        public WidgetDisplayController(float initialPosition, ElementFactory<T,Y> elementFactory)
        {
            InitialPosition = initialPosition;
            _elementFactory = elementFactory;
            ElementIndex = new Dictionary<string, T>();
        }

        /// <summary>
        /// Displays widget at specified position
        /// </summary>
        /// <param name="widget">Widget to be shown</param>
        /// <param name="position">X-Position of widget</param>
        /// <returns>Bottom X-Position of placed widget with repsect to all style attributes</returns>
        public abstract float Show(QLWidget widget, Y style);

        public abstract Y UpdatePosition(QLWidget widget, float positionAbove, Y style);

        protected T CreateElement(QLWidget widget, Y style)
        {
            T element = _elementFactory.CreateElement(widget, style);
            ElementIndex.Add(widget.Identifyer, element);
            return element;
        }

        /// <summary>
        /// Updates the view of a specific widget
        /// </summary>
        /// <param name="widget">Target widget</param>
        public void UpdateView(QLWidget widget)
        {
            _elementFactory.UpdateElement(widget, ElementIndex[widget.Identifyer]);
        }
    }
}
