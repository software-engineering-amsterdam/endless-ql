package qlviz.gui;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import qlviz.QLBaseVisitor;
import qlviz.interpreter.*;
import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

public class QLParserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(new TypeLiteral<QLBaseVisitor<QuestionBlock>>(){}).to(QuestionBlockVisitor.class);
		bind(new TypeLiteral<QLBaseVisitor<Question>>(){}).to(QuestionVisitor.class);
		bind(QuestionTypeTranslator.class).to(QuestionTypeVisitor.class);
		bind(new TypeLiteral<QLBaseVisitor<Form>>(){}).to(FormVisitor.class);
		install(new FactoryModuleBuilder()
			.implement(new TypeLiteral<QLBaseVisitor<ConditionalBlock>>(){},
					ConditionalBlockVisitor.class)
			.build(ConditionalBlockVisitorFactory.class));
	}
}
