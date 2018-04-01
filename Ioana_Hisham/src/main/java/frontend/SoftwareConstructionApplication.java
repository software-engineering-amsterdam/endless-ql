package frontend;

import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ql.ASTBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class SoftwareConstructionApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SoftwareConstructionApplication.class, args);
	}
}
