package expression;

import expression.operator.OperatorEnum;
import expression.tree.TreeNode;

import java.util.Iterator;


public class ArithmeticExpression implements IExpression {


    private TreeNode<String> tree;

    public ArithmeticExpression() {}

    public String getToken() {
        return "arith";
    }

    public void buildTree(String exp) {
        this.tree = new TreeNode<>(exp);
    }

    public void save(String fileName) {

    }

    public String getString() {
        StringBuilder treeStr = new StringBuilder();
        Iterator<TreeNode<String>> iterator = this.tree.iterator();
        while (iterator.hasNext()) {
            treeStr.append(iterator.next().getData());
            if (iterator.hasNext()) {
                treeStr.append(" ");
            }
        }
        return treeStr.toString();
    }
}
