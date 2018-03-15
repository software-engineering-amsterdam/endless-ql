using System;
using System.Runtime.CompilerServices;

namespace QuestionnaireUI.Models
{
    public abstract class ModelWrapperBase<T> : Observable
    {
        protected ModelWrapperBase(T model)
        {
            if (model == null)
            {
                throw new ArgumentNullException(nameof(model));
            }

            Model = model;
        }

        public T Model { get; }

        protected void SetValue<TValue>(
            TValue value,
            [CallerMemberName] string propertyName = null)
        {
            var propertyInfo = Model
                .GetType()
                .GetProperty(propertyName);
            var currentValue = propertyInfo.GetValue(Model);
            if (!Equals(currentValue, value))
            {
                propertyInfo.SetValue(Model, value);
                RaisePropertyChanged(propertyName);
            }
        }

    }
}