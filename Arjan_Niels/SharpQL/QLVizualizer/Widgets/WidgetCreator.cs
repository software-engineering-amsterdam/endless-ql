using QLVisualizer.Elements;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetCreator<T>
    {
        public T CreateWidgets(FormManager formManager, T holder)
        {
            return CreateWidget(formManager, holder);
        }

        protected T CreateWidget(ElementManager elementManager, T holder)
        {
            switch (elementManager)
            {
                case FormManager form:                      throw new InvalidOperationException("Cannot have multiple forms");
                case PageManager page:                      return CreateWidget(page as PageManager, holder);
                case SectionManager section:                return CreateWidget(section as SectionManager, holder);
                case BoolQuestionManager boolQuestion:      return CreateWidget(boolQuestion as BoolQuestionManager, holder);
                case IntQuestionManager intQuestion:        return CreateWidget(intQuestion as IntQuestionManager, holder);
                case MoneyQuestionManager moneyQuestion:    return CreateWidget(moneyQuestion as MoneyQuestionManager, holder);
                case StringQuestionManager stringQuestion:  return CreateWidget(stringQuestion as StringQuestionManager, holder);
            }

            throw new NotImplementedException();
        }
        protected abstract T CreateWidget(FormManager form, T holder);
        protected abstract T CreateWidget(PageManager page, T holder);
        protected abstract T CreateWidget(SectionManager section, T holder);

        protected abstract T CreateWidget(BoolQuestionManager boolQuestion, T holder);
        protected abstract T CreateWidget(IntQuestionManager intQuestion, T holder);
        protected abstract T CreateWidget(MoneyQuestionManager moneyQuestion, T holder);
        protected abstract T CreateWidget(StringQuestionManager stringQuestion, T holder);

    }
}
