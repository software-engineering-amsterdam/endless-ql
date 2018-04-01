package frontend.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Toshiba on 07/03/2018.
 */
public class UserInput {

    @NotNull
    @NotEmpty
    private String htmlRequestInput;
    private String cssRequestInput;

    public String getHtmlRequestInput() {
        return htmlRequestInput;
    }

    public void setHtmlRequestInput(String htmlRequestInput) {
        this.htmlRequestInput = htmlRequestInput;
    }

    public String getCssRequestInput() {
        return cssRequestInput;
    }

    public void setCssRequestInput(String cssRequestInput) {
        this.cssRequestInput = cssRequestInput;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "htmlRequestInput='" + htmlRequestInput + '\'' +
                ", cssRequestInput='" + cssRequestInput + '\'' +
                '}';
    }
}
