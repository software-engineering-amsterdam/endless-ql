package Nodes.Term;

import sun.awt.SunHints;

public class TermFactory {

    public Term getTerm(Class<QLBoolean> type, boolean value){
        return new QLBoolean(value);
    }

    public Term getTerm(Class<QLFloat> type, float value){
        return new QLFloat(value);
    }

    public Term getTerm(Class<QLString> type, String value){
        return new QLString(value);
    }

    //getTerm is not needed for Value as it will not change after parsing
}
