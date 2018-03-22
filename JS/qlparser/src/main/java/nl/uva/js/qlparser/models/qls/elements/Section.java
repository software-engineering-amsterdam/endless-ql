package nl.uva.js.qlparser.models.qls.elements;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;

@Builder
@Data
public class Section {
    String name;
    LinkedList<QuestionReference> questions;
}
