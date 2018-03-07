package qlviz.gui.renderer.layout;

import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Page;
import qlviz.model.style.Section;
import qlviz.model.style.Stylesheet;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * I'm calling this one Naive because it walks the whole stylesheet every time it gets asked for a location.
 * We should implement a more efficient version if time permits. I'd reference a JIRA ticket if we had one.
 * TODO
 */
public class NaiveQuestionLocator implements QuestionLocator{

    private final Stylesheet stylesheet;

    public NaiveQuestionLocator(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
    }


    private boolean hasQuestion(Section section, QuestionViewModel questionViewModel) {
        if (section.getQuestions().stream().anyMatch(question -> question.getName().equals(questionViewModel.getName()))) {
            return true;
        }
        return section.getSections().stream().anyMatch(s -> hasQuestion(s, questionViewModel));
    }

    private boolean hasQuestion(Page page, QuestionViewModel questionViewModel) {
        return page.getSections().stream().anyMatch(s -> hasQuestion(s, questionViewModel));
    }

    @Override
    public Optional<Page> getPage(QuestionViewModel questionViewModel) {
        return stylesheet.getPages().stream().filter(p -> hasQuestion(p, questionViewModel)).findFirst();
    }

    @Override
    public Optional<Section> getSection(QuestionViewModel questionViewModel) {
        Optional<Page> page = this.getPage(questionViewModel);
        return page.flatMap(p -> p.getSections().stream().filter(s -> this.hasQuestion(s, questionViewModel)).findFirst());
    }
}
