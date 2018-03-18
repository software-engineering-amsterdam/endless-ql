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
	: STYLESHEET ID OPEN_CB pages CLOSE_CB
		{$result = new StyleSheet($pages.result);}
	;
pages returns [List<Page> result]
	@init {
	$result = new List<Page>();
	}
	: page
		{$result.Add($page.result);}
	;
page returns [Page result]
	: PAGE ID statements
		{$result = new Page($ID.text, $statements.result);}
	;
statements returns [List<Statement> result]
	@init {
	$result = new List<Statement>();
	}
	: OPEN_CB statements CLOSE_CB
		{$result.AddRange($statements.result);}
	| section
		{$result.Add($section.result);}
	| questionStyle
		{$result.Add($questionStyle.result);}
	| defaultStyle
		{$result.Add($defaultStyle.result);}
	;
section returns [Section result]
	: SECTION string statements
		{$result = new Section($string.result, $statements.result);}
	;
questionStyle returns [QuestionStyle result]
	: QUESTION ID styles
		{$result = new QuestionStyle($ID.text, $styles.result);}
	| QUESTION ID
		{$result = new QuestionStyle($ID.text);}
	;
styles returns [List<IStyle> result]
	@init {
	$result = new List<IStyle>();
	}
	: OPEN_CB styles CLOSE_CB
		{$result = $styles.result;}
	| COLOR SEP HEXCOLORCODE
		{$result.Add(new Color(System.Drawing.ColorTranslator.FromHtml($HEXCOLORCODE.text)));}
	| FONT SEP string
		{$result.Add(new Font($string.result));}
	| FONTSIZE SEP NUMBER
		{$result.Add(new FontSize(int.Parse($NUMBER.text)));}
	| WIDTH SEP NUMBER
		{$result.Add(new Width(int.Parse($NUMBER.text)));}
	| widget
		{$result.Add($widget.result);}
	;
widget returns [IWidget result]
	: WIDGET CHECKBOX
		{$result = new CheckBox();}
	| WIDGET DROPDOWN OPEN_BR yes=string COMMA no=string CLOSE_BR
		{$result = new DropDown($yes.result, $no.result);}
	| WIDGET RADIO OPEN_BR yes=string COMMA no=string CLOSE_BR
		{$result = new Radio($yes.result, $no.result);}
	| WIDGET SLIDER
		{$result = new Slider();}
	| WIDGET SPINBOX
		{$result = new SpinBox();}
	| WIDGET TEXTBOX
		{$result = new TextBox();}
	;
defaultStyle returns [DefaultStyle result]
	: DEFAULT type styles
		{$result = new DefaultStyle($type.result, $styles.result);}
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
