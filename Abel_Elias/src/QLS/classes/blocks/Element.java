package QLS.classes.blocks;

public abstract class Element {
    private ElementType type;

    public Element(ElementType type) {
        this.type = type;
    }

    public ElementType getType() {
        return type;
    }

    public abstract String getName();
}
