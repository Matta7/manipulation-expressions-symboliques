package expression.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> implements Iterable<TreeNode<T>> {

    private T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<>();

    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    /**
     * Add a child to the node, and return the child node
     */
    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    /**
     * Return true if the node is root.
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Return true if the node is a leaf
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * Return the level of the node
     */
    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return new TreeNodeIter<>(this);
    }

}