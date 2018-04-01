parser grammar QLS;

@header {
using Type = Assignment1.Model.QL.AST.Type;
using Assignment1.Model.QLS.AST;
using Assignment1.Model.QLS.AST.Style;
using Assignment1.Model.QLS.AST.Style.Widget;
}

@parser::members
{
    private static string UnEscapeQLSString(string input) => input.Substring(1, input.Length - 2).Replace("\"\"", "\"");
}

options { tokenVocab=QLSLexer; }

stylesheet returns [StyleSheet result]
	: STYLESHEET ID pages EOF
		{$result = new StyleSheet(_localctx.Start.Line, $pages.result);}
	;
pages returns [List<Page> result]
	@init {
	$result = new List<Page>();
	}
	: OPEN_CB (pages
			{$result.AddRange($pages.result);}
		)* CLOSE_CB
	| PAGE ID statements
		{$result.Add(new Page(_localctx.Start.Line, $ID.text, $statements.result));}
	;
statements returns [List<Statement> result]
	@init {
	$result = new List<Statement>();
	}
	: OPEN_CB (statements
			{$result.AddRange($statements.result);}
		)* CLOSE_CB
	| section
		{$result.Add($section.result);}
	| questionStyle
		{$result.Add($questionStyle.result);}
	| defaultStyle
		{$result.Add($defaultStyle.result);}
	;
section returns [Section result]
	: SECTION string statements
		{$result = new Section(_localctx.Start.Line, $string.result, $statements.result);}
	;
questionStyle returns [QuestionStyle result]
	: QUESTION ID styles
		{$result = new QuestionStyle(_localctx.Start.Line, $ID.text, $styles.result);}
	| QUESTION ID
		{$result = new QuestionStyle(_localctx.Start.Line, $ID.text);}
	;
styles returns [List<IStyle> result]
	@init {
	$result = new List<IStyle>();
	}
	: OPEN_CB (styles
			{$result.AddRange($styles.result);}
		)* CLOSE_CB
	| COLOR SEP HEXCOLORCODE
		{$result.Add(new Color(_localctx.Start.Line, System.Drawing.ColorTranslator.FromHtml($HEXCOLORCODE.text)));}
	| FONT SEP string
		{$result.Add(new Font(_localctx.Start.Line, $string.result));}
	| FONTSIZE SEP NUMBER
		{$result.Add(new FontSize(_localctx.Start.Line, int.Parse($NUMBER.text)));}
	| WIDTH SEP NUMBER
		{$result.Add(new Width(_localctx.Start.Line, int.Parse($NUMBER.text)));}
	| widget
		{$result.Add($widget.result);}
	;
widget returns [IWidget result]
	: WIDGET CHECKBOX
		{$result = new CheckBox(_localctx.Start.Line);}
	| WIDGET DROPDOWN OPEN_BR yes=string COMMA no=string CLOSE_BR
		{$result = new DropDown(_localctx.Start.Line, $yes.result, $no.result);}
	| WIDGET RADIO OPEN_BR yes=string COMMA no=string CLOSE_BR
		{$result = new Radio(_localctx.Start.Line, $yes.result, $no.result);}
	| WIDGET SLIDER
		{$result = new Slider(_localctx.Start.Line);}
	| WIDGET SPINBOX
		{$result = new SpinBox(_localctx.Start.Line);}
	| WIDGET TEXTBOX
		{$result = new TextBox(_localctx.Start.Line);}
	;
defaultStyle returns [DefaultStyle result]
	: DEFAULT type styles
		{$result = new DefaultStyle(_localctx.Start.Line, $type.result, $styles.result);}
	;
type returns [Type result]
	: BOOLEAN_TYPE
		{$result = Type.Boolean;}
	| INTEGER_TYPE
		{$result = Type.Integer;}
	| DATE_TYPE
		{$result = Type.Date;}
	| DECIMAL_TYPE
		{$result = Type.Decimal;}
	| MONEY_TYPE
		{$result = Type.Money;}
	| STRING_TYPE
		{$result = Type.String;}
	;
string returns [string result]
	: STRING
		{$result = UnEscapeQLSString($STRING.text);}
	;
