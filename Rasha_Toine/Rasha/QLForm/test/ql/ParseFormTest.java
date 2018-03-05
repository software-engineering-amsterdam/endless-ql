package test.ql;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import ast.Form;
import ast.literal.Identifier;
import ast.statement.IfThenElseStatement;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ParseFormTest {
    @Test
    public void checkForm(String[] args) throws IOException {
    	String qlPath = "";
		if (args.length != 0){
			qlPath = args[0];  	
		} else {
			qlPath = "src/antlr/grammar/examples/questionnaire.ql";
		}
	    File qlFile = new File(qlPath);
	    if (qlFile != null) {
	    	 try {
		    	Form form = Form.parseFileToForm(qlFile);
		    	Identifier id = form.getId();
		    	String expectedId = "Box1HouseOwning";
		    	String extractedId = id.getIdentifier(); 
	
		        assertTrue(form != null);
		        assertEquals(expectedName, extractedId);
		        
		        int expectedSize = 4;
		        int extractedSize = form.getBlock().getStatements().size();

		        assertEquals(expectedSize, extractedSize);
		    	
	    	 } catch (Exception e) {
		        e.printStackTrace();
		    }
 	    }
    }
}
