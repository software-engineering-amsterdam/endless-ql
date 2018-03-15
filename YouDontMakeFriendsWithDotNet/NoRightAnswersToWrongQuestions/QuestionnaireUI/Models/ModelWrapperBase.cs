using System;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Xml.Serialization;

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

        protected TValue GetValue<TValue>(
            [CallerMemberName] string propertyName = null)
        {
            var propertyInfo = Model.GetType().GetProperty(propertyName);
            return (TValue)propertyInfo.GetValue(Model);
        }

    }
}