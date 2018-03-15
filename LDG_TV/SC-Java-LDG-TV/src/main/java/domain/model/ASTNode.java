package domain.model;

public abstract class ASTNode {
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
