package AiSD.BSTree;

public class BSTree {
    public TreeNode<Integer> root;

    public void add(Integer value) {
        if (root == null) {
            root = new TreeNode<>();
            root.setValue(value);
        }

        TreeNode<Integer> currentNode = findNode(value, root);
        switch (Comparator(currentNode.getValue(), value)) {
            case 1:
                currentNode.setRight(new TreeNode());
                currentNode.getRight().setParent(currentNode);
                currentNode.getRight().setValue(value);
                break;
            case -1:
                currentNode.setLeft(new TreeNode());
                currentNode.getLeft().setParent(currentNode);
                currentNode.getLeft().setValue(value);
                break;
            case 0:
                break;
        }
    }

    public TreeNode<Integer> findNode(Integer value, TreeNode<Integer> node) {
        switch (Comparator(node.getValue(), value)) {
            case 1:
                if (node.hasRight())
                    findNode(value, node.getRight());
                else
                    return node.getRight();
                break;
            case -1:
                if (node.hasLeft())
                    findNode(value, node.getLeft());
                else
                    return node.getLeft();
                break;
            case 0:
                return node ;
        }
        return null;
    }

    public int Comparator(Integer Value1, Integer Value2) {
        if (Value1 > Value2)
            return 1;
        else if (Value1 < Value2)
            return -1;
        return 0;
    }

    public void remove(Integer value) {
        TreeNode<Integer> currentNode = findNode(value, root);
        if (currentNode == root) {
            currentNode = currentNode.getRight();
            while (currentNode.hasLeft()) {
                currentNode = currentNode.getLeft();
            }
            currentNode.setLeft(root.getLeft());
            root = root.getRight();
        }

        if (currentNode.getValue() == value) {
            TreeNode<Integer> parentNode = currentNode.getParent();
            if (parentNode.getValue() > currentNode.getValue()) {
                TreeNode<Integer> temp = currentNode.getRight();
                parentNode.setLeft(currentNode.getRight());
                while (temp.hasLeft()) {
                    temp = temp.getLeft();
                }
                temp.setLeft(currentNode.getLeft());
            }
            if (parentNode.getValue() < currentNode.getValue()) {
                TreeNode<Integer> temp = currentNode.getLeft();
                parentNode.setRight(currentNode.getLeft());
                while (temp.hasLeft()) {
                    temp = temp.getRight();
                }
                temp.setRight(currentNode.getRight());
            }
        }
    }

}
