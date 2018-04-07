package nl.khonraad.ql.integration;

import static nl.khonraad.ql.algebra.values.Value.True;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.ql.language.QLInterpretor;
import nl.khonraad.ql.language.QLLanguage;
import nl.khonraad.ql.language.Question;

public class Test_CollegeExample {

    QLInterpretor interpretor;
    
    @Rule
    public WeldInitiator weld = WeldInitiator.from( SourcePathProvider.class, QLAbstractSyntaxTreeBuilder.class, ExtendedQLBaseVisitor.class, QLLanguage.class, LoggerProducer.class ).activate( ApplicationScoped.class ).build();

    private Question questionLabeled( String s ) {
        
        return interpretor.queryAnswerableQuestion( new Identifier( "hasSoldHouse" ) ).get();
    }
    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/CollegeExample.ql" );
        interpretor = weld.select( QLLanguage.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        interpretor.visitSource( visitor );

        interpretor.assign( questionLabeled( "hasSoldHouse" ), True );
        
        interpretor.assign( interpretor.queryAnswerableQuestion( new Identifier( "hasBoughtHouse" ) ).get(), True );
        interpretor.assign( interpretor.queryAnswerableQuestion( new Identifier( "hasMaintLoan" ) ).get(), True );

        assertTrue( !interpretor.queryAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( !interpretor.queryAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        interpretor.visitSource( visitor );

        assertTrue( interpretor.queryAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( interpretor.queryAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        interpretor.assign( interpretor.queryAnswerableQuestion( new Identifier( "sellingPrice" ) ).get(), Value.typed( Type.Money, "1000000.00" ) );
        interpretor.assign( interpretor.queryAnswerableQuestion( new Identifier( "privateDebt" ) ).get(), Value.typed( Type.Money, "800000.00" ) );

        interpretor.visitSource( visitor );

        assertEquals( "a", "200000.00", interpretor.queryComputedQuestion( new Identifier( "valueResidue" ) ).get().value().string() );

    }
}
