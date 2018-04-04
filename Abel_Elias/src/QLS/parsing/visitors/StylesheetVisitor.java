package QLS.parsing.visitors;

import QL.classes.Question;
import QLS.classes.Page;
import QLS.classes.Stylesheet;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class StylesheetVisitor extends QLSBaseVisitor {
    private HashMap<String, Question> questionMap;
    private BlockVisitor blockVisitor;
    private Stylesheet stylesheet;
    private LinkedHashMap<String, Page> pages;
    private LinkedHashMap<String, Section> sections;
    private LinkedHashMap<String, StyledQuestion> questions;
    private LinkedHashMap<String, Element> parents;


    public StylesheetVisitor(LinkedHashMap<String, Question> questionMap) {
        this.questionMap = questionMap;
        this.blockVisitor = new BlockVisitor(questionMap);
        this.parents = new LinkedHashMap<>();
        this.pages = new LinkedHashMap<>();
        this.sections = new LinkedHashMap<>();
        this.questions = new LinkedHashMap<>();
    }

    // Node visitor
    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext c : ctx.page()) {
            pages.add(visitPage(c));
        }

        setLists();
        this.stylesheet = new Stylesheet(id, pages);
        return this.stylesheet;
    }

    private void setLists() {
        sections = blockVisitor.getSections();
        questions = blockVisitor.getQuestions();
        parents = blockVisitor.getParents();
    }

    // Page visitor
    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Section> sections = new ArrayList<>();

        for (QLSParser.SectionContext c : ctx.section()) {
            sections.add(blockVisitor.visitSection(c));
        }
        Page page = new Page(id, sections);
        pages.put(id, page);
        return page;
    }

    public LinkedHashMap<String, Section> getSections() {
        return sections;
    }

    public LinkedHashMap<String, Page> getPages() {
        return pages;
    }

    public LinkedHashMap<String, StyledQuestion> getQuestions() {
        return questions;
    }

    public LinkedHashMap<String, Element> getParents() {
        return parents;
    }

    public Stylesheet getStylesheet() {
        return stylesheet;
    }
}