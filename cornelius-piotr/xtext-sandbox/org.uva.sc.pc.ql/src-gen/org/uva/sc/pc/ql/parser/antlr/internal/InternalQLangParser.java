package org.uva.sc.pc.ql.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.uva.sc.pc.ql.services.QLangGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalQLangParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'form'", "'{'", "'}'", "':'", "'boolean'"
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

        public InternalQLangParser(TokenStream input, QLangGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected QLangGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalQLang.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalQLang.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalQLang.g:65:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalQLang.g:71:1: ruleModel returns [EObject current=null] : ( (lv_forms_0_0= ruleForm ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_forms_0_0 = null;



        	enterRule();

        try {
            // InternalQLang.g:77:2: ( ( (lv_forms_0_0= ruleForm ) )* )
            // InternalQLang.g:78:2: ( (lv_forms_0_0= ruleForm ) )*
            {
            // InternalQLang.g:78:2: ( (lv_forms_0_0= ruleForm ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalQLang.g:79:3: (lv_forms_0_0= ruleForm )
            	    {
            	    // InternalQLang.g:79:3: (lv_forms_0_0= ruleForm )
            	    // InternalQLang.g:80:4: lv_forms_0_0= ruleForm
            	    {

            	    				newCompositeNode(grammarAccess.getModelAccess().getFormsFormParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_forms_0_0=ruleForm();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getModelRule());
            	    				}
            	    				add(
            	    					current,
            	    					"forms",
            	    					lv_forms_0_0,
            	    					"org.uva.sc.pc.ql.QLang.Form");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleForm"
    // InternalQLang.g:100:1: entryRuleForm returns [EObject current=null] : iv_ruleForm= ruleForm EOF ;
    public final EObject entryRuleForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForm = null;


        try {
            // InternalQLang.g:100:45: (iv_ruleForm= ruleForm EOF )
            // InternalQLang.g:101:2: iv_ruleForm= ruleForm EOF
            {
             newCompositeNode(grammarAccess.getFormRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForm=ruleForm();

            state._fsp--;

             current =iv_ruleForm; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForm"


    // $ANTLR start "ruleForm"
    // InternalQLang.g:107:1: ruleForm returns [EObject current=null] : (otherlv_0= 'form' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_questions_3_0= ruleQuestion ) )* otherlv_4= '}' ) ;
    public final EObject ruleForm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_questions_3_0 = null;



        	enterRule();

        try {
            // InternalQLang.g:113:2: ( (otherlv_0= 'form' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_questions_3_0= ruleQuestion ) )* otherlv_4= '}' ) )
            // InternalQLang.g:114:2: (otherlv_0= 'form' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_questions_3_0= ruleQuestion ) )* otherlv_4= '}' )
            {
            // InternalQLang.g:114:2: (otherlv_0= 'form' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_questions_3_0= ruleQuestion ) )* otherlv_4= '}' )
            // InternalQLang.g:115:3: otherlv_0= 'form' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_questions_3_0= ruleQuestion ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getFormAccess().getFormKeyword_0());
            		
            // InternalQLang.g:119:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalQLang.g:120:4: (lv_name_1_0= RULE_ID )
            {
            // InternalQLang.g:120:4: (lv_name_1_0= RULE_ID )
            // InternalQLang.g:121:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFormAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFormRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getFormAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalQLang.g:141:3: ( (lv_questions_3_0= ruleQuestion ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalQLang.g:142:4: (lv_questions_3_0= ruleQuestion )
            	    {
            	    // InternalQLang.g:142:4: (lv_questions_3_0= ruleQuestion )
            	    // InternalQLang.g:143:5: lv_questions_3_0= ruleQuestion
            	    {

            	    					newCompositeNode(grammarAccess.getFormAccess().getQuestionsQuestionParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_questions_3_0=ruleQuestion();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFormRule());
            	    					}
            	    					add(
            	    						current,
            	    						"questions",
            	    						lv_questions_3_0,
            	    						"org.uva.sc.pc.ql.QLang.Question");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getFormAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForm"


    // $ANTLR start "entryRuleQuestion"
    // InternalQLang.g:168:1: entryRuleQuestion returns [EObject current=null] : iv_ruleQuestion= ruleQuestion EOF ;
    public final EObject entryRuleQuestion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuestion = null;


        try {
            // InternalQLang.g:168:49: (iv_ruleQuestion= ruleQuestion EOF )
            // InternalQLang.g:169:2: iv_ruleQuestion= ruleQuestion EOF
            {
             newCompositeNode(grammarAccess.getQuestionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQuestion=ruleQuestion();

            state._fsp--;

             current =iv_ruleQuestion; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQuestion"


    // $ANTLR start "ruleQuestion"
    // InternalQLang.g:175:1: ruleQuestion returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_label_2_0= RULE_STRING ) ) ( (lv_type_3_0= ruleQuestionType ) ) ) ;
    public final EObject ruleQuestion() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_label_2_0=null;
        AntlrDatatypeRuleToken lv_type_3_0 = null;



        	enterRule();

        try {
            // InternalQLang.g:181:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_label_2_0= RULE_STRING ) ) ( (lv_type_3_0= ruleQuestionType ) ) ) )
            // InternalQLang.g:182:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_label_2_0= RULE_STRING ) ) ( (lv_type_3_0= ruleQuestionType ) ) )
            {
            // InternalQLang.g:182:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_label_2_0= RULE_STRING ) ) ( (lv_type_3_0= ruleQuestionType ) ) )
            // InternalQLang.g:183:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_label_2_0= RULE_STRING ) ) ( (lv_type_3_0= ruleQuestionType ) )
            {
            // InternalQLang.g:183:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalQLang.g:184:4: (lv_name_0_0= RULE_ID )
            {
            // InternalQLang.g:184:4: (lv_name_0_0= RULE_ID )
            // InternalQLang.g:185:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(lv_name_0_0, grammarAccess.getQuestionAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getQuestionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,14,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getQuestionAccess().getColonKeyword_1());
            		
            // InternalQLang.g:205:3: ( (lv_label_2_0= RULE_STRING ) )
            // InternalQLang.g:206:4: (lv_label_2_0= RULE_STRING )
            {
            // InternalQLang.g:206:4: (lv_label_2_0= RULE_STRING )
            // InternalQLang.g:207:5: lv_label_2_0= RULE_STRING
            {
            lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            					newLeafNode(lv_label_2_0, grammarAccess.getQuestionAccess().getLabelSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getQuestionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"label",
            						lv_label_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalQLang.g:223:3: ( (lv_type_3_0= ruleQuestionType ) )
            // InternalQLang.g:224:4: (lv_type_3_0= ruleQuestionType )
            {
            // InternalQLang.g:224:4: (lv_type_3_0= ruleQuestionType )
            // InternalQLang.g:225:5: lv_type_3_0= ruleQuestionType
            {

            					newCompositeNode(grammarAccess.getQuestionAccess().getTypeQuestionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_type_3_0=ruleQuestionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getQuestionRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_3_0,
            						"org.uva.sc.pc.ql.QLang.QuestionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQuestion"


    // $ANTLR start "entryRuleQuestionType"
    // InternalQLang.g:246:1: entryRuleQuestionType returns [String current=null] : iv_ruleQuestionType= ruleQuestionType EOF ;
    public final String entryRuleQuestionType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQuestionType = null;


        try {
            // InternalQLang.g:246:52: (iv_ruleQuestionType= ruleQuestionType EOF )
            // InternalQLang.g:247:2: iv_ruleQuestionType= ruleQuestionType EOF
            {
             newCompositeNode(grammarAccess.getQuestionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQuestionType=ruleQuestionType();

            state._fsp--;

             current =iv_ruleQuestionType.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQuestionType"


    // $ANTLR start "ruleQuestionType"
    // InternalQLang.g:253:1: ruleQuestionType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_BoolType_0= ruleBoolType ;
    public final AntlrDatatypeRuleToken ruleQuestionType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_BoolType_0 = null;



        	enterRule();

        try {
            // InternalQLang.g:259:2: (this_BoolType_0= ruleBoolType )
            // InternalQLang.g:260:2: this_BoolType_0= ruleBoolType
            {

            		newCompositeNode(grammarAccess.getQuestionTypeAccess().getBoolTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_BoolType_0=ruleBoolType();

            state._fsp--;


            		current.merge(this_BoolType_0);
            	

            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQuestionType"


    // $ANTLR start "entryRuleBoolType"
    // InternalQLang.g:273:1: entryRuleBoolType returns [String current=null] : iv_ruleBoolType= ruleBoolType EOF ;
    public final String entryRuleBoolType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBoolType = null;


        try {
            // InternalQLang.g:273:48: (iv_ruleBoolType= ruleBoolType EOF )
            // InternalQLang.g:274:2: iv_ruleBoolType= ruleBoolType EOF
            {
             newCompositeNode(grammarAccess.getBoolTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBoolType=ruleBoolType();

            state._fsp--;

             current =iv_ruleBoolType.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBoolType"


    // $ANTLR start "ruleBoolType"
    // InternalQLang.g:280:1: ruleBoolType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'boolean' ;
    public final AntlrDatatypeRuleToken ruleBoolType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalQLang.g:286:2: (kw= 'boolean' )
            // InternalQLang.g:287:2: kw= 'boolean'
            {
            kw=(Token)match(input,15,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getBoolTypeAccess().getBooleanKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolType"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});

}