package classes.form;
import classes.Block;

public interface FormVisitor {

    public void visitForm(Form form);
    public void visitBlock(Block block);
}
