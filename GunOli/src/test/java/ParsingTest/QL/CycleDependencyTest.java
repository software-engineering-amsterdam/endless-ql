package ParsingTest.QL;

import GUI.Parser;
import QL.AST.Form;
import QL.Analysis.CyclicDependencyDetector;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CycleDependencyTest {
    private String example1 = "./src/test/resources/FailingForm/Cycles/DoubleCycle.ql";
    private String example2 = "./src/test/resources/OfficialExample/example.ql";
    private String example3 = "./src/test/resources/FailingForm/Cycles/OneToOne.ql";
    private String example4 = "./src/test/resources/FailingForm/Cycles/MultyCycle.ql";


    public void CycloDependencyTest(String file){
        Form parsed = new Parser().parseInputToForm(file);
        CyclicDependencyDetector ab = new CyclicDependencyDetector(parsed);
        ab.detectCycles();
    }

    @Test
    public void ShouldDetectCycles(){

        try{
            CycloDependencyTest(example1);
            assertEquals(false, true);
        }
        catch (IllegalArgumentException a){
            assert true;
        }

    }

    @Test
    public void ShouldNotDetectCycles(){
        try{
            CycloDependencyTest(example2);
            assert true;
        }
        catch (IllegalArgumentException a){
            assert false;
        }
    }

    @Test
    public void ShouldDetectOneToOne(){
        try{
            CycloDependencyTest(example3);
            assert false;
        }
        catch (IllegalArgumentException a){
            assert true;
        }
    }

    @Test
    public void  shouldDetectMultyCycles(){
        try{
            CycloDependencyTest(example4);
            assert false;
        }
        catch (IllegalArgumentException a){
            assert true;
        }

    }


}
