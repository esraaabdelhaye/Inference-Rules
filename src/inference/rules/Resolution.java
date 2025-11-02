package inference.rules;

import solver.expression.Expression;
import solver.expression.ExpressionImpl;

public class Resolution implements InferenceRule {
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        // removing spaces
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        // it can't be Resolution Syllogism without "v" in both
        if(!e1.contains("v") || !e2.contains("v")) return false;

        String[] pq_1 = e1.split("v");
        String[] pq_2 = e2.split("v");

        return (Expression.areNegations(pq_1[0], pq_2[0]) || Expression.areNegations(pq_1[1], pq_2[1]) || Expression.areNegations(pq_1[0], pq_2[1]));
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2){

        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        String[] pq_1 = e1.split("v");
        String[] pq_2 = e2.split("v");
        Expression ans = new ExpressionImpl();
        String ansText = "";
        if(Expression.areNegations(pq_1[0], pq_2[0]))
            ansText = pq_1[1] + " v " + pq_2[1];
        else if(Expression.areNegations(pq_1[0], pq_2[1]))
            ansText = pq_1[1] + " v " + pq_2[0];
        else if(Expression.areNegations(pq_1[1], pq_2[1]))
            ansText = pq_1[0] + " v " + pq_2[0];
        ans.setRepresentation(ansText + " (Resolution)");
        return ans;
    }
}
