using System.Collections.Generic;

namespace Assignment1.TypeChecking
{
    public class MessageContainer
    {
        public IEnumerable<string> Errors => _errors.AsReadOnly();
        public IEnumerable<string> Warnings => _warnings.AsReadOnly();

        private readonly List<string> _errors = new List<string>();
        private readonly List<string> _warnings = new List<string>();

        public void AddError(string error) => _errors.Add(error);

        public void AddWarning(string warning) => _warnings.Add(warning);

        public void Add((IEnumerable<string> errors, IEnumerable<string> warnings) tuple)
        {
            _errors.AddRange(tuple.errors);
            _warnings.AddRange(tuple.warnings);
        }

        public void Add(MessageContainer messages) => Add(messages.ToTuple());

        public (IEnumerable<string> errors, IEnumerable<string> warnings) ToTuple() => (_errors, _warnings);
    }
}
