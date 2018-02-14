package qlviz;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;

import java.io.IOException;

/**
 * Provides CharStreams for ANTLR.
 */
public interface ICharStreamProvider {
    CharStream getStream() throws IOException;
}


