package model.stackhandler;

import model.expression.IExpression;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionStack {
    private Stack<IExpression> stack = new Stack<>();

    public ExpressionStack() {}

    public void push(IExpression expression) {
        stack.push(expression);
    }

    public IExpression pop() {
        return stack.pop();
    }

    public IExpression peek() throws EmptyStackException {
        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public String toString() {
        if (stack.isEmpty()) {
            return "Stack is empty.";
        }

        int stackIndex = stack.size();
        StringBuilder stackStr = new StringBuilder();

        for (IExpression expression : stack) {
            stackIndex-=1;
            stackStr.append(stackIndex)
                    .append(" : ")
                    .append(expression.toString());

            if(stackIndex != 0) {
                stackStr.append("\n");
            }
        }

        return stackStr.toString();
    }
}
