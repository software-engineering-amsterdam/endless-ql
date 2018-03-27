import QLS.classes.Page;
import QLS.classes.Stylesheet;
import QLS.classes.blocks.Block;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Question;
import QLS.classes.blocks.Section;

public class TestPrinter {

    public TestPrinter() {

    }



//    public void printQLSStyleSheet(Stylesheet stylesheet){
//        for(Page page : stylesheet.getPages()){
//            for(Block block : page.getSections()){
//                printBlock(block);
//            }
//        }
//    }
//    public void printBlock(Block block) {
//        for(Element element : block.getBlockElements()) {
//            if (element instanceof Section) {
//                for (Block block2 : ((Section) element).getElements()) {
//                    printBlock(block2);
//                }
//            } else if (element instanceof Question) {
//                System.out.println((element).getName());
//                System.out.println(((Question) element).getWidget().getWidgetType().toString());
//                System.out.println("---------------------------------");
//
//            }
//        }
//    }
}
