import ParseObjects.Form;

import java.io.InputStream;

public class main {

    public Form parseForm(InputStream stream){
        try{
            //Todo: Fix project structure
           /*
           QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
           CommonTokenStream tokens = new CommonTokenStream(lexer);
           QLParser parser = new QLParser(tokens);
           Trees.inspect(parser.root(), parser);
           */
            return null;
        }catch(Exception e){
            System.out.println(e);

            return null;
        }
    }

    public void start(){
        String file = "example.ql";
        InputStream stream = getClass().getResourceAsStream(file);
        Form form = parseForm(stream);
    }

    public static void main(String[] args) {
        new main().start();
    }
}