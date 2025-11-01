package inference.rules;

import solver.expression.Expression;
import solver.expression.ExpressionImpl;

public class DisjunctiveSyllogism implements InferenceRule {

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        // removing spaces
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        // it can't be Disjunctive Syllogism without "v" in e1
        if(!e1.contains("v")) return false;

        String[] pq = e1.split("v");

       return (e2.equals("~" + pq[0]) || e2.equals("~" + pq[1]) || pq[0].equals("~" + e2) || pq[1].equals("~" + e2));
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2){
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        String[] pq = e1.split("v");
        Expression ans = new ExpressionImpl();
        if(e2.equals("~" + pq[0]) || pq[0].equals("~" + e2))
            ans.setRepresentation(pq[1] + " (Disjunctive-Syllogism)");
        else if(e2.equals("~" + pq[1]) || pq[1].equals("~" + e2))
            ans.setRepresentation(pq[0] + " (Disjunctive-Syllogism)");

        return ans;
    }
}
