package nl.khonraad.qls.language;

 class StylesMemory {

    private StyleNodeTree<StyleNode> styleSheet;

    void addStylesheet( StyleNodeTree<StyleNode> styleSheet ) {
        this.styleSheet = styleSheet;
    }

    void dump() {

        System.out.println( "Dumping QLS memory" );
        System.out.println( "------------------" );
        printTree( styleSheet, "_" );
    }

    private void printTree( StyleNodeTree<StyleNode> node, String appender ) {

        System.out.println( appender + "{" + node.data.nodeType() + "} " + node.data.string() );

        for ( StyleNodeTree<StyleNode> each : node.children ) {
            printTree( each, appender + appender );
        }
    }

    public StyleNodeTree<StyleNode> nodes() {
       return styleSheet;
    }
}
