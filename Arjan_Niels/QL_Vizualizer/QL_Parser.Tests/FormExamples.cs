namespace QL_Parser.Tests
{
    public static class FormExamples
    {
        public static readonly string RandomChars = "dsafjoi23590ujsdjofjoajodfj";
        public static readonly string SimpleForm = "form SimpleForm { \"Have you bought a house?\" boughtAHouse: boolean}";
        public static readonly string SimpleFormWithConditional = "form SimpleForm { " +
            "\"Have you bought a house?\"" +
            "   boughtAHouse: boolean" +
            "" +
            "   if boughtAHouse {" +
            "   }" +
            "}";
        public static readonly string SimpleFormWithConditionalNotInitialised = "form SimpleForm { " +
            "\"Have you bought a house?\"" +
            "   boughtAHouse: boolean" +
            "" +
            "   if notInitialisedVar {" +
            "   }" +
            "}";
        public static readonly string SimpleFormWithDuplicateVars = "form ValidForm {" +
            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +

            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +
            "}";
    }
}