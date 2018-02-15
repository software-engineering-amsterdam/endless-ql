namespace QuestionaireDomain.Entities.DomainObjects
{
    public class LineInfo
    {
        public LineInfo(int line, int characterPosition)
        {
            Line = line;
            CharacterPosition = characterPosition;
        }

        public int Line { get; }
        public int CharacterPosition { get; }
    }
}