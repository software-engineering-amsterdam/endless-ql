import jdk.internal.org.objectweb.asm.ClassVisitor;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.io.*;

public class Application extends JFrame{

    private QLLexer lexer;
    private CommonTokenStream tokens;
    private QLParser parser;

    public static void main(String[] args) {
        String fileName = "example.ql";
        Application application = new Application(fileName);
        application.view();
    }

    Application(String fileName) {
        InputStream stream = getClass().getResourceAsStream(fileName);

        try {
            lexer = new QLLexer(CharStreams.fromStream(stream));
        } catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }

        tokens = new CommonTokenStream(lexer);
        parser = new QLParser(tokens);

        // Walk it and attach our listener
        FormVisitor visitor = new FormVisitor();
        visitor.visit(parser.root());

        // Visualize tree
        parser.reset();
        Trees.inspect(parser.root(), parser);
    }

    private void view() {
        // Do jframe stuff
        this.setSize(500, 500);
//        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
