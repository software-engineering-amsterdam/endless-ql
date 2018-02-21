using QL_Parser.AST.Nodes;
using System.Collections.Generic;

namespace QL_Parser.AST.Validators
{
    public class ASTValidator
    {
        #region Singleton
        private static ASTValidator _instance;
        public static ASTValidator Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new ASTValidator();

                return _instance;
            }
        }

        private List<IASTValidator> _validators;
        private List<string> _errors;

        private ASTValidator()
        {
            // Register the validators.
            _validators = new List<IASTValidator>
            {
                new SingleFormValidator(),
                new QuestionHasNoChildrenValidator()
            };

            _errors = new List<string>();
        }
        #endregion

        /// <summary>
        /// This function validates the entire AST. The GetErrors() function can be
        /// called to get the errors from this validation.
        /// </summary>
        /// <param name="AST"></param>
        /// <param name="logErrors"></param>
        /// <returns></returns>
        public static bool IsValid(Node AST, bool logErrors = true)
        {
            var ASTisValid = true;

            // Remove all the errors before we start validating.
            Instance.Reset();

            // Start validating, don't return immediately when we found an error,
            // because we want to find all errors.
            foreach (var validator in Instance._validators)
                if (!validator.IsValid(AST, logErrors))
                    ASTisValid = false;

            return ASTisValid;
        }

        public static void AddError(string error)
        {
            Instance._errors.Add(error);
        }

        /// <summary>
        /// Get the errors. Make sure to validate the AST first.
        /// </summary>
        /// <returns></returns>
        public static List<string> GetErrors()
        {
            return Instance._errors;
        }

        /// <summary>
        /// Internal function to reset this object.
        /// </summary>
        private void Reset()
        {
            _instance._errors.Clear();
        }
    }
}