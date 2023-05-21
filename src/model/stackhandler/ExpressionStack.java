package model.stackhandler;

import model.expression.IExpression;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionStack {

    /**
     * Stack of IExpression.
     */
    private Stack<IExpression> stack = new Stack<>();

    public ExpressionStack() {}

    /**
     * Push the expression in the stack.
     * @param expression the IExpression to push.
     */
    public void push(IExpression expression) {
        stack.push(expression);
    }

    /**
     * Pop the expression at the top of the stack.
     * @return the IExpression popped.
     */
    public IExpression pop() {
        return stack.pop();
    }

    /**
     * Return the top of the stack.
     * @return the top of the stack.
     */
    public IExpression peek() throws EmptyStackException {
        return stack.peek();
    }

    /**
     * Return the size of the stack.
     * @return the size of the stack.
     */
    public int size() {
        return stack.size();
    }

    /**
     * Return a String that represents the state of the stack.
     * @return The String built.
     */
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
