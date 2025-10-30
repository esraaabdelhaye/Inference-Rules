package inference.rules;

import solver.expression.Expression;
import solver.expression.ExpressionImpl;

public class ModusTollens implements InferenceRule{
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        // removing spaces
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        // it can't be Modus Tollens without ">"
        if(!e1.contains(">")) return false;

        String[] pq = e1.split(">");
        return (e2.equals("~" + pq[1]));
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2){
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String[] pq = e1.split(">");
        Expression ans = new ExpressionImpl();
        ans.setRepresentation("~ " + pq[0] + " (Modus Tollens)");
        return ans;
    }
}
