package domain.model.ast;

public abstract class ASTNode {
    private boolean disabled;
    /**
     *
     * <p>Return if ASTNode is disabled.</p>
     * @return boolean: disabled.
     */
    public boolean isDisabled() {
        return disabled;
    }
    /**
     *
     * <p>Set disabled property of ASTNode</p>
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
