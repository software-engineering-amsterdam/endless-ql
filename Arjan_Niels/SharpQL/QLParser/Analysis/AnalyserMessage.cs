namespace QLParser.Analysis
{
    public class AnalyserMessage
    {
        public MessageType MessageType { get; private set; }
        public Language Language { get; private set; }
        public string Message { get; private set; }

        public AnalyserMessage(string message, Language language, MessageType messageType)
        {
            this.Message = message;
            this.Language = language;
            this.MessageType = messageType;
        }

        public override string ToString()
        {
            return string.Format("[{0}] {1} {2}", this.Language, this.MessageType, this.Message);
        }
    }
}