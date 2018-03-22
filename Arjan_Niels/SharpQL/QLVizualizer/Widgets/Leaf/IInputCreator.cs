using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Widgets.Leaf
{
    public interface IInputCreator<T, Y>
    {
        T CreateInput(IStyler<T> styler, string text, QuestionElementManager<Y> questionElementManager);
    }
}
