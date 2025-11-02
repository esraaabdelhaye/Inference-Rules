package solver.expression;

public interface Expression {
    String getRepresentation();
    void setRepresentation(String representation);

    public static boolean areNegations(String e1, String e2){
        return e1.equals("~" + e2) || e2.equals("~" + e1);
    }

    public static String getNegation(String e1){
        return (e1.charAt(0) == '~')? e1.substring(1): "~" + e1;
    }
}
