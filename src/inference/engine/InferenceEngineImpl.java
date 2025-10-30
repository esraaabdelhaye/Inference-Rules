package inference.engine;

import inference.rules.*;
import solver.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class InferenceEngineImpl implements InferenceEngine {
    List<Expression> expressions = new ArrayList<>();
    List<InferenceRule> rules = new ArrayList<>();
    @Override
    public void addRule(InferenceRule rule){
        rules.add(rule);
    }
    @Override
    public void addExpression(Expression exp){
        expressions.add(exp);
    }

    @Override
    public Expression applyRules() {
        for(InferenceRule rule : rules){
            if(rule.matches(expressions.get(0), expressions.get(1))) return rule.apply(expressions.get(0), expressions.get(1));
        }

        return null;
    }
}
