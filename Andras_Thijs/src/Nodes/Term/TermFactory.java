package Nodes.Term;

public class TermFactory {

    public Term getTerm(boolean value){
        return new QLBoolean(value);
    }

    public Term getTerm(float value){
        return new QLFloat(value);
    }

    public Term getTerm(String value){
        return new QLString(value);
    }

    //getTerm is not needed for Variable as it will not change after parsing
}
