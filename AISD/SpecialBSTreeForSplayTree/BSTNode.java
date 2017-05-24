package AiSD.SpecialBSTreeForSplayTree;

public class BSTNode {
    public int value;
    public BSTNode left, right, parent;

    public BSTNode(int value, BSTNode parent) {
        this.value = value;
        this.parent = parent;
    }
}
