package inference.rules;

import solver.expression.Expression;
import solver.expression.ExpressionImpl;

public class ModusPonens implements InferenceRule {

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        // removing spaces

        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        // it can't be Modus Ponens without ">"
        if(!e1.contains(">")) return false;

        String[] pq = e1.split(">");
        return (pq[0].equals(e2));
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2){
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String[] pq = e1.split(">");
        Expression ans = new ExpressionImpl();
        ans.setRepresentation(pq[1] + " (Modus Ponens)");
        return ans;
    }
}
