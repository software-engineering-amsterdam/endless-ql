using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Elements
{
    public static class ElementUtils
    {
        public static ElementType ElementToEnum(ElementManager manager)
        {
            switch (manager)
            {
                case BoolQuestionManager boolQuestion:
                    return ElementType.Bool;
                case IntQuestionManager intQuestion:
                    return ElementType.Int;
                case MoneyQuestionManager moneyQuestion:
                    return ElementType.Money;
                case StringQuestionManager stringQuestion:
                    return ElementType.Sting;

                case FormManager form:
                    return ElementType.Form;
                case PageManager page:
                    return ElementType.Page;
                case SectionManager section:
                    return ElementType.Section;
            }
            throw new NotImplementedException();
        }
    }
}
