public class tmp {

    public void start(){
        Object x = true;

        Class a = Integer.class;
        Class b = Number.class;
        Class c = Boolean.class;
        Boolean y = null;

        Integer i1 = 5;
        Double i2 = 6.0;

        Double i3 = (double) i1;

        System.out.println(haveSameType(b,a));
    }

    private boolean haveSameType(Class value, Class type){
        return value.isAssignableFrom(type);
    }

    public static  void main(String[] args){
        new tmp().start();
    }
}
