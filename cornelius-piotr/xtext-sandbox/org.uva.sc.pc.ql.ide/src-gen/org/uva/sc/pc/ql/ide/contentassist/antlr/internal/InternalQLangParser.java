package org.uva.sc.pc.ql.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.uva.sc.pc.ql.services.QLangGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalQLangParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'boolean'", "'form'", "'{'", "'}'", "':'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=5;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__15=15;
    public static final int RULE_INT=6;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalQLangParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalQLangParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalQLangParser.tokenNames; }
    public String getGrammarFileName() { return "InternalQLang.g"; }


    	private QLangGrammarAccess grammarAccess;

    	public void setGrammarAccess(QLangGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalQLang.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalQLang.g:54:1: ( ruleModel EOF )
            // InternalQLang.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalQLang.g:62:1: ruleModel : ( ( rule__Model__FormsAssignment )* ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:66:2: ( ( ( rule__Model__FormsAssignment )* ) )
            // InternalQLang.g:67:2: ( ( rule__Model__FormsAssignment )* )
            {
            // InternalQLang.g:67:2: ( ( rule__Model__FormsAssignment )* )
            // InternalQLang.g:68:3: ( rule__Model__FormsAssignment )*
            {
             before(grammarAccess.getModelAccess().getFormsAssignment()); 
            // InternalQLang.g:69:3: ( rule__Model__FormsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalQLang.g:69:4: rule__Model__FormsAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Model__FormsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getFormsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleForm"
    // InternalQLang.g:78:1: entryRuleForm : ruleForm EOF ;
    public final void entryRuleForm() throws RecognitionException {
        try {
            // InternalQLang.g:79:1: ( ruleForm EOF )
            // InternalQLang.g:80:1: ruleForm EOF
            {
             before(grammarAccess.getFormRule()); 
            pushFollow(FOLLOW_1);
            ruleForm();

            state._fsp--;

             after(grammarAccess.getFormRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleForm"


    // $ANTLR start "ruleForm"
    // InternalQLang.g:87:1: ruleForm : ( ( rule__Form__Group__0 ) ) ;
    public final void ruleForm() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:91:2: ( ( ( rule__Form__Group__0 ) ) )
            // InternalQLang.g:92:2: ( ( rule__Form__Group__0 ) )
            {
            // InternalQLang.g:92:2: ( ( rule__Form__Group__0 ) )
            // InternalQLang.g:93:3: ( rule__Form__Group__0 )
            {
             before(grammarAccess.getFormAccess().getGroup()); 
            // InternalQLang.g:94:3: ( rule__Form__Group__0 )
            // InternalQLang.g:94:4: rule__Form__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Form__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFormAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleForm"


    // $ANTLR start "entryRuleQuestion"
    // InternalQLang.g:103:1: entryRuleQuestion : ruleQuestion EOF ;
    public final void entryRuleQuestion() throws RecognitionException {
        try {
            // InternalQLang.g:104:1: ( ruleQuestion EOF )
            // InternalQLang.g:105:1: ruleQuestion EOF
            {
             before(grammarAccess.getQuestionRule()); 
            pushFollow(FOLLOW_1);
            ruleQuestion();

            state._fsp--;

             after(grammarAccess.getQuestionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQuestion"


    // $ANTLR start "ruleQuestion"
    // InternalQLang.g:112:1: ruleQuestion : ( ( rule__Question__Group__0 ) ) ;
    public final void ruleQuestion() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:116:2: ( ( ( rule__Question__Group__0 ) ) )
            // InternalQLang.g:117:2: ( ( rule__Question__Group__0 ) )
            {
            // InternalQLang.g:117:2: ( ( rule__Question__Group__0 ) )
            // InternalQLang.g:118:3: ( rule__Question__Group__0 )
            {
             before(grammarAccess.getQuestionAccess().getGroup()); 
            // InternalQLang.g:119:3: ( rule__Question__Group__0 )
            // InternalQLang.g:119:4: rule__Question__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Question__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQuestionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQuestion"


    // $ANTLR start "entryRuleQuestionType"
    // InternalQLang.g:128:1: entryRuleQuestionType : ruleQuestionType EOF ;
    public final void entryRuleQuestionType() throws RecognitionException {
        try {
            // InternalQLang.g:129:1: ( ruleQuestionType EOF )
            // InternalQLang.g:130:1: ruleQuestionType EOF
            {
             before(grammarAccess.getQuestionTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleQuestionType();

            state._fsp--;

             after(grammarAccess.getQuestionTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQuestionType"


    // $ANTLR start "ruleQuestionType"
    // InternalQLang.g:137:1: ruleQuestionType : ( ruleBoolType ) ;
    public final void ruleQuestionType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:141:2: ( ( ruleBoolType ) )
            // InternalQLang.g:142:2: ( ruleBoolType )
            {
            // InternalQLang.g:142:2: ( ruleBoolType )
            // InternalQLang.g:143:3: ruleBoolType
            {
             before(grammarAccess.getQuestionTypeAccess().getBoolTypeParserRuleCall()); 
            pushFollow(FOLLOW_2);
            ruleBoolType();

            state._fsp--;

             after(grammarAccess.getQuestionTypeAccess().getBoolTypeParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQuestionType"


    // $ANTLR start "entryRuleBoolType"
    // InternalQLang.g:153:1: entryRuleBoolType : ruleBoolType EOF ;
    public final void entryRuleBoolType() throws RecognitionException {
        try {
            // InternalQLang.g:154:1: ( ruleBoolType EOF )
            // InternalQLang.g:155:1: ruleBoolType EOF
            {
             before(grammarAccess.getBoolTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleBoolType();

            state._fsp--;

             after(grammarAccess.getBoolTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBoolType"


    // $ANTLR start "ruleBoolType"
    // InternalQLang.g:162:1: ruleBoolType : ( 'boolean' ) ;
    public final void ruleBoolType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:166:2: ( ( 'boolean' ) )
            // InternalQLang.g:167:2: ( 'boolean' )
            {
            // InternalQLang.g:167:2: ( 'boolean' )
            // InternalQLang.g:168:3: 'boolean'
            {
             before(grammarAccess.getBoolTypeAccess().getBooleanKeyword()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getBoolTypeAccess().getBooleanKeyword()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolType"


    // $ANTLR start "rule__Form__Group__0"
    // InternalQLang.g:177:1: rule__Form__Group__0 : rule__Form__Group__0__Impl rule__Form__Group__1 ;
    public final void rule__Form__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:181:1: ( rule__Form__Group__0__Impl rule__Form__Group__1 )
            // InternalQLang.g:182:2: rule__Form__Group__0__Impl rule__Form__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Form__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Form__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__0"


    // $ANTLR start "rule__Form__Group__0__Impl"
    // InternalQLang.g:189:1: rule__Form__Group__0__Impl : ( 'form' ) ;
    public final void rule__Form__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:193:1: ( ( 'form' ) )
            // InternalQLang.g:194:1: ( 'form' )
            {
            // InternalQLang.g:194:1: ( 'form' )
            // InternalQLang.g:195:2: 'form'
            {
             before(grammarAccess.getFormAccess().getFormKeyword_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getFormAccess().getFormKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__0__Impl"


    // $ANTLR start "rule__Form__Group__1"
    // InternalQLang.g:204:1: rule__Form__Group__1 : rule__Form__Group__1__Impl rule__Form__Group__2 ;
    public final void rule__Form__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:208:1: ( rule__Form__Group__1__Impl rule__Form__Group__2 )
            // InternalQLang.g:209:2: rule__Form__Group__1__Impl rule__Form__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Form__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Form__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__1"


    // $ANTLR start "rule__Form__Group__1__Impl"
    // InternalQLang.g:216:1: rule__Form__Group__1__Impl : ( ( rule__Form__NameAssignment_1 ) ) ;
    public final void rule__Form__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:220:1: ( ( ( rule__Form__NameAssignment_1 ) ) )
            // InternalQLang.g:221:1: ( ( rule__Form__NameAssignment_1 ) )
            {
            // InternalQLang.g:221:1: ( ( rule__Form__NameAssignment_1 ) )
            // InternalQLang.g:222:2: ( rule__Form__NameAssignment_1 )
            {
             before(grammarAccess.getFormAccess().getNameAssignment_1()); 
            // InternalQLang.g:223:2: ( rule__Form__NameAssignment_1 )
            // InternalQLang.g:223:3: rule__Form__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Form__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFormAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__1__Impl"


    // $ANTLR start "rule__Form__Group__2"
    // InternalQLang.g:231:1: rule__Form__Group__2 : rule__Form__Group__2__Impl rule__Form__Group__3 ;
    public final void rule__Form__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:235:1: ( rule__Form__Group__2__Impl rule__Form__Group__3 )
            // InternalQLang.g:236:2: rule__Form__Group__2__Impl rule__Form__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__Form__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Form__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__2"


    // $ANTLR start "rule__Form__Group__2__Impl"
    // InternalQLang.g:243:1: rule__Form__Group__2__Impl : ( '{' ) ;
    public final void rule__Form__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:247:1: ( ( '{' ) )
            // InternalQLang.g:248:1: ( '{' )
            {
            // InternalQLang.g:248:1: ( '{' )
            // InternalQLang.g:249:2: '{'
            {
             before(grammarAccess.getFormAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getFormAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__2__Impl"


    // $ANTLR start "rule__Form__Group__3"
    // InternalQLang.g:258:1: rule__Form__Group__3 : rule__Form__Group__3__Impl rule__Form__Group__4 ;
    public final void rule__Form__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:262:1: ( rule__Form__Group__3__Impl rule__Form__Group__4 )
            // InternalQLang.g:263:2: rule__Form__Group__3__Impl rule__Form__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Form__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Form__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__3"


    // $ANTLR start "rule__Form__Group__3__Impl"
    // InternalQLang.g:270:1: rule__Form__Group__3__Impl : ( ( rule__Form__QuestionsAssignment_3 )* ) ;
    public final void rule__Form__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:274:1: ( ( ( rule__Form__QuestionsAssignment_3 )* ) )
            // InternalQLang.g:275:1: ( ( rule__Form__QuestionsAssignment_3 )* )
            {
            // InternalQLang.g:275:1: ( ( rule__Form__QuestionsAssignment_3 )* )
            // InternalQLang.g:276:2: ( rule__Form__QuestionsAssignment_3 )*
            {
             before(grammarAccess.getFormAccess().getQuestionsAssignment_3()); 
            // InternalQLang.g:277:2: ( rule__Form__QuestionsAssignment_3 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalQLang.g:277:3: rule__Form__QuestionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Form__QuestionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getFormAccess().getQuestionsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__3__Impl"


    // $ANTLR start "rule__Form__Group__4"
    // InternalQLang.g:285:1: rule__Form__Group__4 : rule__Form__Group__4__Impl ;
    public final void rule__Form__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:289:1: ( rule__Form__Group__4__Impl )
            // InternalQLang.g:290:2: rule__Form__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Form__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__4"


    // $ANTLR start "rule__Form__Group__4__Impl"
    // InternalQLang.g:296:1: rule__Form__Group__4__Impl : ( '}' ) ;
    public final void rule__Form__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:300:1: ( ( '}' ) )
            // InternalQLang.g:301:1: ( '}' )
            {
            // InternalQLang.g:301:1: ( '}' )
            // InternalQLang.g:302:2: '}'
            {
             before(grammarAccess.getFormAccess().getRightCurlyBracketKeyword_4()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getFormAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__Group__4__Impl"


    // $ANTLR start "rule__Question__Group__0"
    // InternalQLang.g:312:1: rule__Question__Group__0 : rule__Question__Group__0__Impl rule__Question__Group__1 ;
    public final void rule__Question__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:316:1: ( rule__Question__Group__0__Impl rule__Question__Group__1 )
            // InternalQLang.g:317:2: rule__Question__Group__0__Impl rule__Question__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Question__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Question__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__0"


    // $ANTLR start "rule__Question__Group__0__Impl"
    // InternalQLang.g:324:1: rule__Question__Group__0__Impl : ( ( rule__Question__NameAssignment_0 ) ) ;
    public final void rule__Question__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:328:1: ( ( ( rule__Question__NameAssignment_0 ) ) )
            // InternalQLang.g:329:1: ( ( rule__Question__NameAssignment_0 ) )
            {
            // InternalQLang.g:329:1: ( ( rule__Question__NameAssignment_0 ) )
            // InternalQLang.g:330:2: ( rule__Question__NameAssignment_0 )
            {
             before(grammarAccess.getQuestionAccess().getNameAssignment_0()); 
            // InternalQLang.g:331:2: ( rule__Question__NameAssignment_0 )
            // InternalQLang.g:331:3: rule__Question__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Question__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getQuestionAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__0__Impl"


    // $ANTLR start "rule__Question__Group__1"
    // InternalQLang.g:339:1: rule__Question__Group__1 : rule__Question__Group__1__Impl rule__Question__Group__2 ;
    public final void rule__Question__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:343:1: ( rule__Question__Group__1__Impl rule__Question__Group__2 )
            // InternalQLang.g:344:2: rule__Question__Group__1__Impl rule__Question__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Question__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Question__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__1"


    // $ANTLR start "rule__Question__Group__1__Impl"
    // InternalQLang.g:351:1: rule__Question__Group__1__Impl : ( ':' ) ;
    public final void rule__Question__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:355:1: ( ( ':' ) )
            // InternalQLang.g:356:1: ( ':' )
            {
            // InternalQLang.g:356:1: ( ':' )
            // InternalQLang.g:357:2: ':'
            {
             before(grammarAccess.getQuestionAccess().getColonKeyword_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getQuestionAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__1__Impl"


    // $ANTLR start "rule__Question__Group__2"
    // InternalQLang.g:366:1: rule__Question__Group__2 : rule__Question__Group__2__Impl rule__Question__Group__3 ;
    public final void rule__Question__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:370:1: ( rule__Question__Group__2__Impl rule__Question__Group__3 )
            // InternalQLang.g:371:2: rule__Question__Group__2__Impl rule__Question__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__Question__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Question__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__2"


    // $ANTLR start "rule__Question__Group__2__Impl"
    // InternalQLang.g:378:1: rule__Question__Group__2__Impl : ( ( rule__Question__LabelAssignment_2 ) ) ;
    public final void rule__Question__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:382:1: ( ( ( rule__Question__LabelAssignment_2 ) ) )
            // InternalQLang.g:383:1: ( ( rule__Question__LabelAssignment_2 ) )
            {
            // InternalQLang.g:383:1: ( ( rule__Question__LabelAssignment_2 ) )
            // InternalQLang.g:384:2: ( rule__Question__LabelAssignment_2 )
            {
             before(grammarAccess.getQuestionAccess().getLabelAssignment_2()); 
            // InternalQLang.g:385:2: ( rule__Question__LabelAssignment_2 )
            // InternalQLang.g:385:3: rule__Question__LabelAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Question__LabelAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getQuestionAccess().getLabelAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__2__Impl"


    // $ANTLR start "rule__Question__Group__3"
    // InternalQLang.g:393:1: rule__Question__Group__3 : rule__Question__Group__3__Impl ;
    public final void rule__Question__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:397:1: ( rule__Question__Group__3__Impl )
            // InternalQLang.g:398:2: rule__Question__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Question__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__3"


    // $ANTLR start "rule__Question__Group__3__Impl"
    // InternalQLang.g:404:1: rule__Question__Group__3__Impl : ( ( rule__Question__TypeAssignment_3 ) ) ;
    public final void rule__Question__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:408:1: ( ( ( rule__Question__TypeAssignment_3 ) ) )
            // InternalQLang.g:409:1: ( ( rule__Question__TypeAssignment_3 ) )
            {
            // InternalQLang.g:409:1: ( ( rule__Question__TypeAssignment_3 ) )
            // InternalQLang.g:410:2: ( rule__Question__TypeAssignment_3 )
            {
             before(grammarAccess.getQuestionAccess().getTypeAssignment_3()); 
            // InternalQLang.g:411:2: ( rule__Question__TypeAssignment_3 )
            // InternalQLang.g:411:3: rule__Question__TypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Question__TypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getQuestionAccess().getTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__Group__3__Impl"


    // $ANTLR start "rule__Model__FormsAssignment"
    // InternalQLang.g:420:1: rule__Model__FormsAssignment : ( ruleForm ) ;
    public final void rule__Model__FormsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:424:1: ( ( ruleForm ) )
            // InternalQLang.g:425:2: ( ruleForm )
            {
            // InternalQLang.g:425:2: ( ruleForm )
            // InternalQLang.g:426:3: ruleForm
            {
             before(grammarAccess.getModelAccess().getFormsFormParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleForm();

            state._fsp--;

             after(grammarAccess.getModelAccess().getFormsFormParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__FormsAssignment"


    // $ANTLR start "rule__Form__NameAssignment_1"
    // InternalQLang.g:435:1: rule__Form__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Form__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:439:1: ( ( RULE_ID ) )
            // InternalQLang.g:440:2: ( RULE_ID )
            {
            // InternalQLang.g:440:2: ( RULE_ID )
            // InternalQLang.g:441:3: RULE_ID
            {
             before(grammarAccess.getFormAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFormAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__NameAssignment_1"


    // $ANTLR start "rule__Form__QuestionsAssignment_3"
    // InternalQLang.g:450:1: rule__Form__QuestionsAssignment_3 : ( ruleQuestion ) ;
    public final void rule__Form__QuestionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:454:1: ( ( ruleQuestion ) )
            // InternalQLang.g:455:2: ( ruleQuestion )
            {
            // InternalQLang.g:455:2: ( ruleQuestion )
            // InternalQLang.g:456:3: ruleQuestion
            {
             before(grammarAccess.getFormAccess().getQuestionsQuestionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleQuestion();

            state._fsp--;

             after(grammarAccess.getFormAccess().getQuestionsQuestionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Form__QuestionsAssignment_3"


    // $ANTLR start "rule__Question__NameAssignment_0"
    // InternalQLang.g:465:1: rule__Question__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Question__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:469:1: ( ( RULE_ID ) )
            // InternalQLang.g:470:2: ( RULE_ID )
            {
            // InternalQLang.g:470:2: ( RULE_ID )
            // InternalQLang.g:471:3: RULE_ID
            {
             before(grammarAccess.getQuestionAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQuestionAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__NameAssignment_0"


    // $ANTLR start "rule__Question__LabelAssignment_2"
    // InternalQLang.g:480:1: rule__Question__LabelAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Question__LabelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:484:1: ( ( RULE_STRING ) )
            // InternalQLang.g:485:2: ( RULE_STRING )
            {
            // InternalQLang.g:485:2: ( RULE_STRING )
            // InternalQLang.g:486:3: RULE_STRING
            {
             before(grammarAccess.getQuestionAccess().getLabelSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getQuestionAccess().getLabelSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__LabelAssignment_2"


    // $ANTLR start "rule__Question__TypeAssignment_3"
    // InternalQLang.g:495:1: rule__Question__TypeAssignment_3 : ( ruleQuestionType ) ;
    public final void rule__Question__TypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalQLang.g:499:1: ( ( ruleQuestionType ) )
            // InternalQLang.g:500:2: ( ruleQuestionType )
            {
            // InternalQLang.g:500:2: ( ruleQuestionType )
            // InternalQLang.g:501:3: ruleQuestionType
            {
             before(grammarAccess.getQuestionAccess().getTypeQuestionTypeParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleQuestionType();

            state._fsp--;

             after(grammarAccess.getQuestionAccess().getTypeQuestionTypeParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Question__TypeAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000800L});

}