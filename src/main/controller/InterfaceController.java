package main.controller;

import inference.engine.InferenceEngine;
import inference.engine.InferenceEngineImpl;
import inference.rules.*;
import solver.expression.Expression;
import solver.expression.ExpressionImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceController implements EngineController{
    InferenceRule[] rules = {new ModusPonens(), new ModusTollens(), new HypotheticalSyllogism(), new DisjunctiveSyllogism(), new Resolution()};
    InferenceEngine engine = new InferenceEngineImpl();
    Expression exp1 = new ExpressionImpl();
    Expression exp2 = new ExpressionImpl();

    public InterfaceController(){
        for(InferenceRule rule : rules){
            engine.addRule(rule);
        }
    }

    public void run(){
        System.out.println("=============================================");
        System.out.println("Welcome To The Inference Engine");
        System.out.println("=============================================");
        String e1, e2;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the first expression: ");
        e1 = scanner.nextLine();
        exp1.setRepresentation(e1);
        System.out.println("Please enter the second expression: ");
        e2 = scanner.nextLine();
        exp2.setRepresentation(e2);

        engine.addExpression(exp1);
        engine.addExpression(exp2);

        Expression ans = engine.applyRules();
        if(ans != null){
            System.out.println("Result: ");
            System.out.println(ans.getRepresentation());
        }else{
            System.out.println("The input expression can't be inferred");
        }
    }
}
