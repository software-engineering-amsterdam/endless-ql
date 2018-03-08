using System;

namespace QLVizualizer.Style
{
    public class WindowsStyleProperties : ICloneable
    {
        public int YPosition;
        public int Width;
        public int LabelInputMargin;

        public object Clone()
        {
            return new WindowsStyleProperties { YPosition = YPosition, Width = Width, LabelInputMargin = LabelInputMargin };
        }
    }
}
