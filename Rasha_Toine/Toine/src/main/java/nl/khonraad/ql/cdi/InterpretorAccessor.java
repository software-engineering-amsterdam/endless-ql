package nl.khonraad.ql.cdi;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

import nl.khonraad.ql.language.QLInterpretor;
import nl.khonraad.ql.language.QLLanguage;

public interface InterpretorAccessor {

    default QLInterpretor interpretor() {

        BeanManager bm = CDI.current().getBeanManager();

        @SuppressWarnings( "unchecked" )
        Bean<QLLanguage> bean = (Bean<QLLanguage>) bm.getBeans( QLLanguage.class ).iterator().next();

        CreationalContext<QLLanguage> ctx = bm.createCreationalContext( bean );

        return (QLInterpretor) bm.getReference( bean, QLLanguage.class, ctx );
    }
}
