form ExpressionTest {
    int1: "1" integer
    int2: "2" integer
    if(int1 + int2) {
        int3: "3" integer
        if(int1 - int3) {
            int4: "4" integer
        }
    }
}