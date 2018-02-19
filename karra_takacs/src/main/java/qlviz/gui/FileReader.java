package qlviz.gui;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;

import java.io.IOException;


/**
 * Reads the contents of a file and returns a CharStream with its contents.
 */
public class FileReader implements ICharStreamProvider {


    private final String path;

    FileReader(String path) {
       this.path = path;
    }


    @Override
    public CharStream getStream() throws IOException {
        return new ANTLRFileStream(path);
    }
}
