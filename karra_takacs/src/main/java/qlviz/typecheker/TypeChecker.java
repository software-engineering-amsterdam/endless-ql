package qlviz.typecheker;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import qlviz.model.question.BooleanQuestion;
import qlviz.model.question.QuestionType;


public class TypeChecker {
	Boolean isValid = false;
	public Boolean isValidType(TerminalNode terminalNode){
	for(QuestionType qType : QuestionType.values()) {
		if(terminalNode.getText().equals(qType.toString())) {
			isValid = true;
		}
	}
	return isValid;
	}
}
