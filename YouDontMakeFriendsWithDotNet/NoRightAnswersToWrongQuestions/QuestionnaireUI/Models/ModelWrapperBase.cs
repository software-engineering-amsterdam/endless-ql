using System;

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
    }
}