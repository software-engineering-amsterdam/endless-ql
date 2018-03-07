using System;
using System.Collections.Generic;

namespace QuestionnaireUI
{
    public class CalculatedQuestionWrapper : ModelWrapper<CalculatedQuestionModel>
    {
        public CalculatedQuestionWrapper(CalculatedQuestionModel model) : base (model)
        {
            if (string.IsNullOrEmpty(model.Text))
            {
                throw new ArgumentNullException(nameof(CalculatedQuestionModel.Text));
            }

            if (string.IsNullOrEmpty(model.Value))
            {
                throw new ArgumentNullException(nameof(CalculatedQuestionModel.Value));
            }

            Value = model.Value;
        }

        public string Value { get; set; }
    }

    public class ModelWrapper<T> 
    {
        public ModelWrapper(T model)
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