package qlviz.gui;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import qlviz.QLSBaseVisitor;
import qlviz.interpreter.QuestionTypeTranslator;
import qlviz.interpreter.QuestionTypeVisitor;
import qlviz.interpreter.style.*;
import qlviz.interpreter.style.ParameterVisitor;
import qlviz.model.style.*;

public class QLSParserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(new TypeLiteral<QLSBaseVisitor<Parameter>>(){}).to(ParameterVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<PropertySetting>>(){}).to(PropertySettingVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<Widget>>(){}).to(WidgetVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<Page>>(){}).to(PageVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<Section>>(){}).to(SectionVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<Question>>(){}).to(QuestionVisitor.class);
		bind(QuestionTypeTranslator.class).to(QuestionTypeVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<DefaultWidgetDeclaration>>(){}).to(DefaultWidgetVisitor.class);
		bind(new TypeLiteral<QLSBaseVisitor<Stylesheet>>(){}).to(StylesheetVisitor.class);
	}
}
