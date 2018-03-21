package frontend;

import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ql.ASTBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class SoftwareConstructionApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SoftwareConstructionApplication.class, args);

		createForm("C:\\Users\\metchu\\Documents\\GitHub\\SoftwareConstruction\\endless-ql\\Ioana_Hisham\\src\\main\\resources\\form.ql");
	}

	public static void createForm(String file) throws IOException {
		if (!Files.exists(Paths.get(file))) {
			throw new FileNotFoundException();
		}

		InputStream inputStream = new FileInputStream(file);
		ASTBuilder ast = new ASTBuilder();
		ast.build(inputStream);
		//visitor.visit(parseTree);
	}
}
