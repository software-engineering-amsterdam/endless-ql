package nl.khonraad.cdi.accessors;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

import nl.khonraad.ql.language.QuestionsInterpretor;
import nl.khonraad.ql.language.QuestionsLanguage;

public interface QuestionsInterpretorAccessor {

    default QuestionsInterpretor questionsInterpretor() {

        BeanManager beanManager = CDI.current().getBeanManager();

        @SuppressWarnings( "unchecked" )
        Bean<QuestionsLanguage> bean = (Bean<QuestionsLanguage>) beanManager.getBeans( QuestionsLanguage.class ).iterator().next();
        CreationalContext<QuestionsLanguage> ctx = beanManager.createCreationalContext( bean );
        return (QuestionsInterpretor) beanManager.getReference( bean, QuestionsLanguage.class, ctx );
    }
}
