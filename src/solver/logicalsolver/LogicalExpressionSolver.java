package solver.logicalsolver;

import solver.expression.Expression;

public interface LogicalExpressionSolver {
    boolean evaluateExpression(Expression expression);
}
