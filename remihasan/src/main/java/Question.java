public class Question {

    private final String name;
    private final String text;
    private final Answer answer;

    public Question(String name, String text, Answer answer) {
        this.name = name;
        this.text = text.substring(1, text.length() - 1);
        this.answer = answer;

        System.out.println("debugquestion");
        System.out.println(answer);
    }
}
