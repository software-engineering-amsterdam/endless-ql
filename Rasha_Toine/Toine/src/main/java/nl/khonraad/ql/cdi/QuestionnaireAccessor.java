package nl.khonraad.ql.cdi;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Survey;

public interface QuestionnaireAccessor {

    default Questionnaire questionnaire() {

        BeanManager bm = CDI.current().getBeanManager();

        @SuppressWarnings( "unchecked" )
        Bean<Survey> bean = (Bean<Survey>) bm.getBeans( Survey.class ).iterator().next();

        CreationalContext<Survey> ctx = bm.createCreationalContext( bean );

        return (Questionnaire) bm.getReference( bean, Survey.class, ctx );
    }
}
