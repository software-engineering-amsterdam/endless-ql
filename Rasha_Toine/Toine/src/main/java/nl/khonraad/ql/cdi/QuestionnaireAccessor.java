package nl.khonraad.ql.cdi;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

import nl.khonraad.ql.ast.data.Questionnaire;

public interface QuestionnaireAccessor {

    default Questionnaire questionnaire() {

        BeanManager bm = CDI.current().getBeanManager();

        @SuppressWarnings( "unchecked" )
        Bean<Questionnaire> bean = (Bean<Questionnaire>) bm.getBeans( Questionnaire.class ).iterator().next();

        CreationalContext<Questionnaire> ctx = bm.createCreationalContext( bean );

        return (Questionnaire) bm.getReference( bean, Questionnaire.class, ctx );
    }
}
