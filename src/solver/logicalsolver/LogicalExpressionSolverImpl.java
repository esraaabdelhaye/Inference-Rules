package solver.logicalsolver;

import solver.expression.Expression;

import java.util.*;

public class LogicalExpressionSolverImpl implements LogicalExpressionSolver {
    Map<Character, Boolean> values = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    @Override
    public boolean evaluateExpression(Expression expression) {
        String cleanExpression = checkSyntax(expression);
//        System.out.println(cleanExpression);
        if (cleanExpression == null) {
            throw  new RuntimeException("Syntax error");
        }
        String parsed = parseExpression(cleanExpression);
        return solve(parsed);
    }

    private String checkSyntax(Expression expression)
    {
        char prev = '0';
        int openParentheses = 0;
        String exp = expression.getRepresentation().replaceAll("\\s+", "");
        StringBuilder cleanExp = new StringBuilder();
        for (char c : exp.toCharArray()) {
            if (isOperator(c))
            {
                if (isOperator(prev) || prev == '0') {
                    return null;
                }
                cleanExp.append(c);
            }
            else if (Character.isLetter(c))
            {
                if (Character.isLetter(prev) && prev != 'v')
                {
                    return null;
                }
                cleanExp.append(c);
            }
            else if (c == '~')
            {
                if (prev == '~')
                {
                    cleanExp.deleteCharAt(cleanExp.length() - 1);
                    prev = '0';
                    continue;
                }
                else
                    cleanExp.append(c);
            }
            else if (c == '(')
            {
                openParentheses++;
                cleanExp.append(c);
            }
            else if (c == ')')
            {
                openParentheses--;
                if (openParentheses < 0)
                {
                    return null;
                }
                cleanExp.append(c);
            }
            else {
                return null;
            }

            prev = c;
        }
        if ((openParentheses != 0) || isOperator(prev) || prev == '~')
            return null;
        if (cleanExp.length() == 1)
        {
            Character c = cleanExp.charAt(0);
            cleanExp.append("^").append(c);
        }
        return cleanExp.toString();
    }

    private String parseExpression(String expression) {
        StringBuilder postfix =  new StringBuilder();
        Stack<Character> operators = new Stack<>();
//        Map<Character, Boolean> values = new HashMap<>();
        Map<Character, Integer> weights = new HashMap<>();
        weights.put('(', 0);
        weights.put(')', 0);
        weights.put('>', 1);
        weights.put('v', 2);
        weights.put('^', 3);
        weights.put('~', 4);
        String exp = expression.replaceAll("\\s+", "");
        for (char c : exp.toCharArray()) {
            if (isOperator(c) || c == '~') {
                if (operators.isEmpty() || weights.get(c) > weights.get(operators.peek())) {
                    operators.push(c);
                }
                else {
                    while(!operators.isEmpty() && weights.get(c) > weights.get(operators.peek())) {
                        postfix.append(operators.pop());
                    }
                }
            }
            else if (Character.isLetter(c)) {
                postfix.append(c);
                if (!values.containsKey(c))
                {
                    System.out.print(c + " = ");
                    boolean value = sc.nextBoolean();
                    values.put(c, value);
                }
            }
            else if (c == '(') {
                operators.push('(');
            }
            else if (c == ')') {
                while(!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                operators.pop();
            }
        }
        sc.close();
        while(!operators.isEmpty()) {
            postfix.append(operators.pop());
        }
//        System.out.println(postfix.toString());
        return  postfix.toString();
    }

    private boolean solve(String post) {
        Stack<Boolean> result = new Stack<>();
        for (char c : post.toCharArray()) {
            if (Character.isLetter(c) && c != 'v'){
                result.push(values.get(c));
            }
            else if (c == '~') {
                result.push(!result.pop());
            }
            else{
                boolean b =  result.pop();
                boolean a = result.pop();
                result.push(performOperation(a, b, c));
            }
        }
        return result.peek();
    }

    private boolean isOperator(char ch) {
        if (ch == '>' || ch == 'v' || ch == '^') {
            return true;
        }
        return false;
    }
    private boolean performOperation(boolean a, boolean b, char op) {
        switch (op) {
            case '>':
                return !(a) || b;
            case 'v':
                return a || b;
            case '^':
                return a && b;
            default:
                return false;
        }
    }
}
