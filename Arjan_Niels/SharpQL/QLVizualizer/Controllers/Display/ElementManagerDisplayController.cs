namespace QLVisualizer.Controllers.Display
{
    public abstract class WidgetDisplayController<T> : ElementManagerController
    {
        protected T BaseDisplay;

        public override void DisplayForm()
        {
            UpdateBaseDisplay(CreateFormWidget());
            Form.RegisterListeners();
        }

        protected abstract T CreateFormWidget();

        protected abstract void UpdateBaseDisplay(T newDisplay);
    }
}
