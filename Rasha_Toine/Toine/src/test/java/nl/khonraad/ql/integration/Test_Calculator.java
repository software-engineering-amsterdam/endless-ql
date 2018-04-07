package nl.khonraad.ql.integration;

import static org.junit.Assert.assertEquals;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.ql.language.QLInterpretor;
import nl.khonraad.ql.language.QLLanguage;

public class Test_Calculator {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            QLAbstractSyntaxTreeBuilder.class, 
            ExtendedQLBaseVisitor.class, 
            QLLanguage.class, 
//            Memory.class, 
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/Calculator.ql" );
        QLInterpretor interpretor = weld.select( QLLanguage.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        interpretor.visitSource( visitor );

        assertEquals( "a", 14, Integer.parseInt( interpretor.queryComputedQuestion( new Identifier( "a" ) ).get().value().string() ) );
        assertEquals( "b", 20, Integer.parseInt( interpretor.queryComputedQuestion( new Identifier( "b" ) ).get().value().string() ) );
        assertEquals( "c", true, Boolean.parseBoolean( interpretor.queryComputedQuestion( new Identifier( "c" ) ).get().value().string() ) );
    }
}