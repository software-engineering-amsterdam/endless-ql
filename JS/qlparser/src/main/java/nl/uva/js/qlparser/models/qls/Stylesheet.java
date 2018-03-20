package nl.uva.js.qlparser.models.qls;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;

import java.util.LinkedList;

@Data
@Builder
public class Stylesheet {
    private String name;
    private LinkedList<DefaultStyle> defaultStyles;
    private LinkedList<Page> pages;

    public void checkType() {
//
    }
}
