package frontend.model;


/**
 * Created by Toshiba on 01/04/2018.
 */
public class InterpretedOutput {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "InterpretedOutput{" + "content='" + content + '\'' + '}';
    }
}
