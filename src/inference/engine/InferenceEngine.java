package inference.engine;

import inference.rules.InferenceRule;
import solver.expression.Expression;

public interface InferenceEngine {
    void addRule(InferenceRule rule);
    void addExpression(Expression exp);
    Expression applyRules();
}
