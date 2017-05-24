package AiSD.BSTree;

public class TreeNode<T> {
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private T value;

    public boolean hasLeft() {
        return getLeft() != null;
    }

    public boolean hasRight() {
        return getRight() != null;
    }

    public boolean hasParent() {
        return getParent() != null;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
