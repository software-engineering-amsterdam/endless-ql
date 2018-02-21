public enum Type {
    BOOL (0),
    STRING (1),
    INT (2),
    DATA (3),
    DECIMAL (4),
    MONEY (5);

    private final int value;

    Type(int value){
        this.value = value;
    }

}
