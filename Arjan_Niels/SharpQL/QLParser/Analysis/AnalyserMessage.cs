namespace QLParser.Analysis
{
    public enum MessageType
    {
        ERROR,
        WARNING
    }

    public enum LanguageType
    {
        QL,
        QLS
    }

    public class AnalyserMessage
    {
        public MessageType MessageType { get; private set; }
        public LanguageType LanguageType { get; private set; }
        public string Message { get; private set; }

        public AnalyserMessage(string message, LanguageType languageType, MessageType messageType)
        {
            this.Message = message;
            this.LanguageType = languageType;
            this.MessageType = messageType;
        }

        public override string ToString()
        {
            return string.Format("[{0}] {1}\t {2}", this.LanguageType, this.MessageType, this.Message);
        }
    }
}