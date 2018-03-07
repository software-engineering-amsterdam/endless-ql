package astvisitor;

class StringValue extends Value<String>{
    final String value;
    StringValue(String value){
        this.value = value;
    }
}
