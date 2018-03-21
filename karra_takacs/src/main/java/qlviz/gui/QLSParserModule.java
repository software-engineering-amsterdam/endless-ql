package qlviz.gui;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import qlviz.QLSVisitor;
import qlviz.interpreter.QuestionTypeTranslator;
import qlviz.interpreter.QuestionTypeVisitor;
import qlviz.interpreter.style.*;
import qlviz.interpreter.style.ParameterVisitor;
import qlviz.model.style.*;

public class QLSParserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(new TypeLiteral<QLSVisitor<Parameter>>(){}).to(ParameterVisitor.class);
		bind(new TypeLiteral<QLSVisitor<PropertySetting>>(){}).to(PropertySettingVisitor.class);
		bind(new TypeLiteral<QLSVisitor<Widget>>(){}).to(WidgetVisitor.class);
		bind(new TypeLiteral<QLSVisitor<Page>>(){}).to(PageVisitor.class);
		bind(new TypeLiteral<QLSVisitor<Section>>(){}).to(SectionVisitor.class);
		bind(new TypeLiteral<QLSVisitor<Question>>(){}).to(QuestionVisitor.class);
		bind(QuestionTypeTranslator.class).to(QuestionTypeVisitor.class);
		bind(new TypeLiteral<QLSVisitor<DefaultWidgetDeclaration>>(){}).to(DefaultWidgetVisitor.class);
		bind(new TypeLiteral<QLSVisitor<Stylesheet>>(){}).to(StylesheetVisitor.class);
	}
}
