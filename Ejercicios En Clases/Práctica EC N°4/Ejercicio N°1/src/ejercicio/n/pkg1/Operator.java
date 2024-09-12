package ejercicio.n.pkg1;
public class Operator {
    private static Float num1 = 0.0f;
    private static Float num2 = 0.0f;
    public static void setNumbers(Float _num1, Float _num2) {
        num1 = _num1;
        num2 = _num2;
    }
    public static Float add() {
        return num1 + num2;
    }
    
    public static Float substract() {
        return num1 - num2;
    }
    
    public static Float multiply() {
        return num1 * num2;
    }
    
    public static Float divide() {
        return (num2 == 0.0f) ? null : num1.floatValue() / num2.floatValue();
    }
}
