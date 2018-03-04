package ql;

import java.io.File;
import java.io.IOException;



import ql.checking.TypeChecker;
import ql.ast.Form;
import ql.ast.literal.Identifier;
import ql.ast.statement.IfThenElseStatement;
import ql.ast.statement.IfThenStatement;


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
	    	// 1- Generate ast tree
	    	ql.ast.Form form = Form.parseFileToForm(qlFile);
	    	ql.ast.literal.Identifier id = form.getId();
	    	form.getBlock().getStatements().forEach(it -> {
				System.out.println("statementlocation = " + it.getLocation().getStartLine() + ", class = " + it.getClass());
				/*if (it.getClass() == ast.statement.IfThenElseStatement.class)
					System.out.println("Count ifBody statements = " + ((IfThenElseStatement) it).getIfBody().getStatements().size());
					System.out.println("Count elseBody statements = " + ((IfThenElseStatement) it).getElseBody().getStatements().size());*/
	    	});
	
	    	System.out.println("Count statements = " + form.getBlock().getStatements().size());
	    	System.out.print(id.getIdentifier());

	    	// 2- Run type checker on generated ql form 
	        TypeChecker checker = new TypeChecker();
	        checker.runChecker(form);
	        
	        // 3- Generate gui for the ql form
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	  }
}
