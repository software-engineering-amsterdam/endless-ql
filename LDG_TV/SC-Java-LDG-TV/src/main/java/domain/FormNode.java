package domain;

public class FormNode {

    private String formIdentifier;
    private FormData formData;
    public FormNode(){
        this.formData = new FormData();
    }

    public void setFormIdentifier(String formIdentifier) {
        this.formIdentifier = formIdentifier;
    }
    public String getFormIdentifier() {
        return formIdentifier;
    }
    public FormData getFormData() {
        return this.formData;
    }

}
