package models.guestions.graph.elements;

public class ReferenceEdge {
    private QuestionVertex destinationQuestionVertex;

    public ReferenceEdge(QuestionVertex destinationQuestionVertex) {
        this.destinationQuestionVertex = destinationQuestionVertex;
    }

    public QuestionVertex getDestinationQuestionVertex() {
        return destinationQuestionVertex;
    }

    public void setDestinationQuestionVertex(QuestionVertex destinationQuestionVertex) {
        this.destinationQuestionVertex = destinationQuestionVertex;
    }
}
