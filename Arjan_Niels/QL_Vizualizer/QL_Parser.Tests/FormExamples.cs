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
        public static readonly string SimpleFormWithDuplicateVars = "form ValidForm " +
            "{" +
            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +

            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +
            "}";
        public static readonly string GitHubExampleForm = "form taxOfficeExample" +
            "{ " +
            "   \"Did you sell a house in 2010?\"" +
            "       hasSoldHouse: boolean" +
            "   \"Did you buy a house in 2010?\"" +
            "       hasBoughtHouse: boolean" +

            "   if hasSoldHouse {" +
            "   \"What was the selling price?\"" +
            "       sellingPrice: money" +
            "   \"Private debts for the sold house:\"" +
            "       privateDebt: money" +
            "   \"Value residue:\"" +
            "       valueResidue: money =" +
            "           (sellingPrice - privateDebt)" +
            "   }" +
            "}";
        public static readonly string DuplicateVarsAndComputedForm = "form TestForm {" +
            "   \"Have you sold a house in 2010?\"" +
            "       soldHouse: boolean" +
            "   \"Duplicate computed\"" +
            "       soldHouse: boolean = 5 - 10" +
            "}";
    }
}