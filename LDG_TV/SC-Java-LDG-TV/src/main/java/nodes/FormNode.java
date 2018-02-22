package nodes;

public class FormNode {

    private String formIdentifier;
    private FormData formData;
    public FormNode(String formIdentifier){
        this.formIdentifier = formIdentifier;
        this.formData = new FormData();
    }

    public String getFormIdentifier() {
        return formIdentifier;
    }
    public FormData getFormData() {
        return this.formData;
    }
}
