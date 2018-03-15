package classes;

import java.util.ArrayList;
import java.util.List;

public class Block{
    boolean constraint;
    List<Question> statements;

    public Block(CodeBlock code, boolean constraint) {
        List<Question> statements = new ArrayList<>();
    }
}
