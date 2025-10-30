package inference.rules;

import solver.expression.Expression;
import solver.expression.ExpressionImpl;

public class HypotheticalSyllogism implements InferenceRule {
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        // removing spaces
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        // it can't be Hypothetical Syllogism without ">" in both
        if(!e1.contains(">") || !e2.contains(">")) return false;

        String[] pq_1 = e1.split(">");
        String[] pq_2 = e2.split(">");

        return (pq_1[1].equals(pq_2[0]));
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2){
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        String[] pq_1 = e1.split(">");
        String[] pq_2 = e2.split(">");

        Expression ans = new ExpressionImpl();
        ans.setRepresentation(pq_1[0] + " > " + pq_2[1] + " (Hypothetical-Syllogism)");
        return ans;
    }
}
