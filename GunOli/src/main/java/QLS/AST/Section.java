/*package QLS.AST;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.AST.Statements.Statement;

import java.util.ArrayList;

public class Section extends QLSNode {

   private String sectionName;
   private ArrayList<Section> sections;
   private ArrayList<Statement> statements;

   public Section(String sectionName, ArrayList<Section> sections, ArrayList<Statement> statements, int line){
       super(line);
       this.sectionName = sectionName;
       this.sections = sections;
       this.statements = statements;
   }

   public String getSectionName() { return sectionName; }
   public ArrayList<Section> getSections() { return sections; }
   public ArrayList<Statement> getStatements() { return statements; }

   @Override
   public <T> T accept(WidgetVisitorInterface<T> visitor) { return visitor.visit(this); }
}*/
