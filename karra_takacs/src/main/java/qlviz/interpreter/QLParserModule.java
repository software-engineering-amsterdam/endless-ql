package qlviz.interpreter;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import qlviz.QLVisitor;
import qlviz.interpreter.*;
import qlviz.interpreter.linker.QuestionLinker;
import qlviz.interpreter.linker.QuestionLinkerImpl;
import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

public class QLParserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(new TypeLiteral<QLVisitor<QuestionBlock>>(){}).to(QuestionBlockVisitor.class);
		bind(new TypeLiteral<QLVisitor<Question>>(){}).to(QuestionVisitor.class);
		bind(QuestionTypeTranslator.class).to(QuestionTypeVisitor.class);
		bind(new TypeLiteral<QLVisitor<Form>>(){}).to(FormVisitor.class);
		bind(QuestionLinker.class).to(QuestionLinkerImpl.class);
		bind(TypedQuestionCollector.class).to(TypedQuestionWalker.class);
		install(new FactoryModuleBuilder()
			.implement(new TypeLiteral<QLVisitor<ConditionalBlock>>(){},
					ConditionalBlockVisitor.class)
			.build(ConditionalBlockVisitorFactory.class));
	}
}
