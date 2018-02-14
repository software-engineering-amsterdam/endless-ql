import expression.Expression;
import model.Block;
import model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class VisitorBlock extends QLBaseVisitor<Block> {

    private final ArrayList<Expression> conditions;

    VisitorBlock(ArrayList<Expression> conditions){
        this.conditions = conditions;
    }

    @Override
    public Block visitBlock(QLParser.BlockContext ctx) {

        VisitorBlockElement visitorBlockElement = new VisitorBlockElement(conditions);

        Collection<ArrayList<Question>> questionLists = ctx
                .blockElement().stream()
                .map(x -> visitorBlockElement.visit(x))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Question> questions = new ArrayList<>();
        for(ArrayList<Question> questionList : questionLists){
            questions.addAll(questionList);

            // Add every condition to every question within this block
            conditions.forEach(condition -> questionList.forEach(q -> q.addCondition(condition)));
        }
        return new Block(questions);
    }
}
