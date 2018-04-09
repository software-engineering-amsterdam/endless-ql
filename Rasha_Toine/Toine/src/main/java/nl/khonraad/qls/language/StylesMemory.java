package nl.khonraad.qls.language;

 class StylesMemory {

    private StyleTree<Style> styles;

    void addStylesheet( StyleTree<Style> styleSheet ) {
        this.styles = styleSheet;
    }

    void dump() {

        System.out.println( "Dumping QLS memory" );
        System.out.println( "------------------" );
        printTree( styles, "_" );
    }

    private void printTree( StyleTree<Style> styleTree, String appender ) {

        System.out.println( appender + "{" + styleTree.data.styleType() + "} " + styleTree.data.string() );

        for ( StyleTree<Style> each : styleTree.children ) {
            printTree( each, appender + appender );
        }
    }

    public StyleTree<Style> styles() {
       return styles;
    }
}
