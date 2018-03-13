using QLVisualizer.Elements;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetCreator<T>
    {
        public T CreateWidget(ElementManager elementManager)
        {
            switch (elementManager)
            {
                case FormManager form:                      return CreateWidget(form as FormManager);
                case PageManager page:                      return CreateWidget(page as PageManager);
                case SectionManager section:                return CreateWidget(section as SectionManager);
                case BoolQuestionManager boolQuestion:      return CreateWidget(boolQuestion as BoolQuestionManager);
                case IntQuestionManager intQuestion:        return CreateWidget(intQuestion as IntQuestionManager);
                case MoneyQuestionManager moneyQuestion:    return CreateWidget(moneyQuestion as MoneyQuestionManager);
                case StringQuestionManager stringQuestion:  return CreateWidget(stringQuestion as StringQuestionManager);
            }

            throw new NotImplementedException();
        }
        protected abstract T CreateWidget(FormManager form);
        protected abstract T CreateWidget(PageManager page);
        protected abstract T CreateWidget(SectionManager section);

        protected abstract T CreateWidget(BoolQuestionManager boolQuestion);
        protected abstract T CreateWidget(IntQuestionManager intQuestion);
        protected abstract T CreateWidget(MoneyQuestionManager moneyQuestion);
        protected abstract T CreateWidget(StringQuestionManager stringQuestion);

    }
}
