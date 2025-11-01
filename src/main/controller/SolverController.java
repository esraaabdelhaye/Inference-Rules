package main.controller;

import solver.expression.Expression;
import solver.expression.ExpressionImpl;
import solver.logicalsolver.LogicalExpressionSolver;
import solver.logicalsolver.LogicalExpressionSolverImpl;

import java.util.Scanner;

public class SolverController implements EngineController {
    Expression exp = new ExpressionImpl();
    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {

        System.out.println("=============================================");
        System.out.println("Welcome To The Solver Engine");
        System.out.println("=============================================");
        System.out.println("Please enter an expression: ");
        String e1 = sc.nextLine();
        exp.setRepresentation(e1);
        LogicalExpressionSolver solver = new LogicalExpressionSolverImpl();
        try{
            System.out.println(solver.evaluateExpression(exp));
        }
        catch(Exception e){
            System.out.println("Wrong expression");
        }



        sc.close();
    }


}
