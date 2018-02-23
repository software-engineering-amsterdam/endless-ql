package checking;

public class TypeChecker {
	
	public boolean detectDuplicatedQuestionsDifferentTypes() {
		return true;
	}
	
	public boolean detectReferenceToUndentifiedQuestion() {
		return true;
	}
	
	public boolean detectNonBooleanCondition(){
		return true;
	}
	
	public boolean detectDuplicatedLabels() {
		return true;
	}

}
