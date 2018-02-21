
import java.io.File;
import java.io.IOException;


import ast.Form;
import ast.literal.Identifier;
import ast.statement.IfThenElseStatement;import ast.statement.IfThenStatement;


public class Main {
	
  public static void main(String[] args) throws IOException {

	String qlPath = "";

	if (args.length != 0){
		qlPath = args[0];  	
	} else {
		qlPath = "src/antlr/grammar/examples/questionnaire.ql";
	}

    File qlFile = new File(qlPath);
    
    try {
    	Form form = Form.parseFileToForm(qlFile);
    	Identifier id = form.getId();
    	form.getBlock().getStatements().forEach(it -> {
			System.out.println("statementlocation = " + it.getLocation().getStartLine() + ", class = " + it.getClass());
			if (it.getClass() == ast.statement.IfThenElseStatement.class)
				//System.out.println("Count ifBody statements = " + ((IfThenElseStatement) it).getIfBody().getStatements().size());
				System.out.println("Count elseBody statements = " + ((IfThenElseStatement) it).getElseBody().getStatements().size());
    	});

    	System.out.println("Count statements = " + form.getBlock().getStatements().size());
    	System.out.print(id.getIdentifier());
    	/*
		  	statementlocation = 2, class = class ast.statement.ComputedQuestion
			statementlocation = 3, class = class ast.statement.ComputedQuestion
			statementlocation = 4, class = class ast.statement.ComputedQuestion
			statementlocation = 5, class = class ast.statement.IfThenStatement
			Count ifbody statements = 3
			Count elseBody statements = 1
			Count statements = 4
			Box1HouseOwning
    	 */
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // check tree
    // generate gui
  }
}
