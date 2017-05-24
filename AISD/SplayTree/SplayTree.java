package AiSD.SplayTree;

import AiSD.BSTree.BSTree;
import AiSD.BSTree.TreeNode;

public class SplayTree extends BSTree {
    @Override
    public void add(Integer value) {
        super.add(value);

        TreeNode<Integer> currentNode = super.findNode(value, root);
        /**switch (Comparator(currentNode.getValue(), value)) {
         case 1:
         splay(currentNode.getRight());
         break;
         case -1:
         splay(currentNode.getLeft());
         break;
         case 0:
         break;
         }*/
        splay(currentNode);
    }

    @Override
    public void remove(Integer value) {
        super.remove(value);
    }

    @Override
    public TreeNode<Integer> findNode(Integer value, TreeNode<Integer> node) {
        return super.findNode(value, node);

        /**switch (Comparator(value, root.getValue())){
         case 1:
         if (root.hasRight()){

         }
         }*/
    }

    public void splay(TreeNode<Integer> node) {
        while (node != root) {
            if (node.hasParent()) {
                if (node.getParent().hasParent()) {
                    zigzig(node);
                    zigzag(node);
                } else
                    zig(node);
            }
        }
    }

    public void zig(TreeNode<Integer> node) {
        TreeNode<Integer> tempNode = node;
        switch (Comparator(node.getValue(), root.getValue())) {
            case 1:
                node = root;
                node.setRight(tempNode.getRight());
                node.setLeft(root);
                root = tempNode;
                root.setParent(node);
                root.setRight(tempNode.getLeft());
                root.setLeft(root.getRight());
                break;
            case -1:
                node = root;
                node.setRight(root);
                node.setLeft(tempNode.getLeft());
                root.setParent(node);
                root.setRight(root.getRight());
                root.setLeft(tempNode.getRight());
                break;
            case 0:
                break;
        }
    }

    public void zigzig(TreeNode<Integer> node) {
        TreeNode<Integer> tempNode = node;
        TreeNode<Integer> tempParent = node.getParent();
        switch (Comparator(node.getValue(), tempParent.getValue())) {
            case 1:
                switch (Comparator(tempParent.getValue(), root.getValue())) {
                    case 1:
                        node = root;
                        node.setRight(tempNode.getRight());
                        node.setLeft(tempParent);
                        tempParent.setParent(node);
                        tempParent.setRight(tempNode.getLeft());
                        tempParent.setLeft(root);
                        root.setParent(tempParent);
                        root.setRight(tempParent.getLeft());
                        root.setLeft(root.getLeft());
                        break;
                    case -1:
                        break;
                    case 0:
                        break;
                }
            case -1:
                switch (Comparator(tempParent.getValue(), root.getValue())) {
                    case 1:
                        break;
                    case -1:
                        node = root;
                        node.setRight(tempParent);
                        node.setLeft(tempNode.getLeft());
                        tempParent.setParent(node);
                        tempParent.setRight(root);
                        tempParent.setLeft(tempNode.getRight());
                        root.setParent(tempParent);
                        root.setRight(root.getRight());
                        root.setLeft(tempParent.getRight());
                        break;
                    case 0:
                        break;
                }
            case 0:
                break;
        }
    }

    public void zigzag(TreeNode<Integer> node) {
        TreeNode<Integer> tempNode = node;
        TreeNode<Integer> tempParent = node.getParent();
        switch (Comparator(node.getValue(), tempParent.getValue())) {
            case 1:
                switch (Comparator(tempParent.getValue(), root.getValue())) {
                    case 1:
                        break;
                    case -1:
                        node = root;
                        node.setRight(root);
                        node.setLeft(tempParent);
                        tempParent.setParent(node);
                        tempParent.setRight(tempNode.getLeft());
                        tempParent.setLeft(tempParent.getLeft());
                        root.setParent(node);
                        root.setRight(root.getRight());
                        root.setLeft(tempNode.getRight());
                        break;
                    case 0:
                        break;
                }
            case -1:
                switch (Comparator(tempParent.getValue(), root.getValue())) {
                    case 1:
                        node = root;
                        node.setRight(tempParent);
                        node.setLeft(root);
                        tempParent.setParent(node);
                        tempParent.setRight(tempParent.getRight());
                        tempParent.setLeft(tempNode.getRight());
                        root.setParent(node);
                        root.setRight(tempNode.getLeft());
                        root.setLeft(root.getLeft());
                        break;
                    case -1:
                        break;
                    case 0:
                        break;
                }
            case 0:
                break;
        }
    }
}