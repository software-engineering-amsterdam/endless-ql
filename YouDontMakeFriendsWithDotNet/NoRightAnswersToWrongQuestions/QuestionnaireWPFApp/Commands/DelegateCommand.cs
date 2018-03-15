using System;
using System.Windows.Input;

namespace QuestionnaireWPFApp.Commands
{
    public class DelegateCommand : ICommand
    {
        private readonly Action<object> m_execute;
        private readonly Func<object, bool> m_canExecute;

        public DelegateCommand(Action<object> execute, Func<object, bool> canExecute = null)
        {
            if (execute == null)
            {
                throw new ArgumentNullException(nameof(execute));
            }

            m_execute = execute;
            m_canExecute = canExecute;
        }

        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter)
        {
            return m_canExecute == null || m_canExecute(parameter);
        }

        public void Execute(object parameter)
        {
            m_execute(parameter);
        }

        public void RaiseCanExecuteChanged()
        {
            CanExecuteChanged?.Invoke(this, EventArgs.Empty);
        }
    }
}