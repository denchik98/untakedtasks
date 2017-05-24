package AiSD.SpecialBSTreeForSplayTree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BSTree {
    private BSTNode head;
    private boolean isLeft;

    public void add(int value) {
        if (head == null)
            head = new BSTNode(value, null);
        else
            add(head, value);
    }

    public void add(BSTNode node, int value) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new BSTNode(value, node);
                splay(node.left);
            } else
                add(node.left, value);
        } else {
            if (node.right == null) {
                node.right = new BSTNode(value, node);
                splay(node.right);
            } else
                add(node.right, value);
        }
    }

    public boolean remove(int value) {
        BSTNode current = find(value);

        if (current == null)
            return false;
        if (current.left == null && current.right == null) {
            if (current == head)
                head = null;
            else if (isLeft)
                current.parent.left = null;
            else
                current.parent.right = null;
        } else if (current.right == null) {     //До этого был случай, когда узел не имел потомков, теперь когда есть левый
            if (current == head)
                head = current.left;
            else if (isLeft) {
                current.parent.left = current.left;
                current.left.parent = current.parent;
            } else {
                current.parent.right = current.left;
                current.left.parent = current.parent;
            }
        } else if (current.left == null) {      //Теперь узел имеет правого потомка, но не левого
            if (current == head)
                head = current.right;
            else if (isLeft) {
                current.parent.left = current.right;
                current.right.parent = current.parent;
            } else {
                current.parent.right = current.right;
                current.right.parent = current.parent;
            }
        } else {                                //Узел с двумя потомками
            BSTNode child = getChild(current);

            if (current == head)
                head = child;
            else if (isLeft) {
                current.parent.left = child;
                child.parent = current.parent;
            } else {
                current.parent.right = child;
                child.parent = current.parent;
            }

            child.left = current.left;
            current.left.parent = child;
        }
        return true;
    }

    public BSTNode find(int value) {
        BSTNode current = head;

        while (current.value != value) {
            if (current.value > value) {
                isLeft = true;
                current = current.left;
            } else {
                isLeft = false;
                current = current.right;
            }
            if (current == null)
                return null;
        }

        splay(current);
        return current;
    }

    public BSTNode getChild(BSTNode node) {
        BSTNode child = node;
        BSTNode current = node.right;

        while (current != null) {
            child = current;
            current = current.left;
        }

        if (child != node.right) {
            child.parent.left = child.right;
            child.right.parent = child.parent;
            child.right = node.right;
            node.right.parent = child;
        }

        return child;
    }

    public void splay(BSTNode node) {
        if (node.parent == null) {
            head = node;
            return;
        }
        if (node.parent.parent == null) {
            zig(node);
            return;
        }

        BSTNode parent = node.parent.parent;
        BSTNode child = node.parent;
        if ((child.left == node && parent.left == child) || (child.right == node && parent.right == child))
            zigzig(node);
        else
            zigzag(node);
    }

    public void zig(BSTNode node) {
        if (node == head.right) {
            head.parent = node;
            node.parent = null;
            head.right = node.left;
            node.left = head;

            if (node.left.right != null)
                node.left.right.parent = head;

            head = node;
        } else {
            head.parent = node;
            node.parent = null;
            head.left = node.right;
            node.right = head;

            if (node.right.left != null)
                node.right.left.parent = head;

            head = node;
        }
    }

    public void zigzig(BSTNode node) {
        BSTNode a = node.parent.parent;
        BSTNode b = node.parent;
        BSTNode t = node;

        if (check(a))
            t = null;
        else {
            if (a == a.parent.left) {
                a.parent.left = t;
                t = a.parent;
            } else {
                a.parent.right = t;
                t = a.parent;
            }
        }

        if (node == node.parent.right) {
            b.right = node.left;

            if (node.left != null)
                node.left.parent = b;

            b.parent = node;
            a.right = b.left;

            if (b.left != null)
                b.left.parent = a;

            a.parent = b;
            b.left = a;
            node.left = b;
            node.parent = t;
            splay(node);
        } else {
            b.left = node.right;
            if (node.right != null)
                node.right.parent = b;

            b.parent = node;
            a.left = b.right;

            if (b.right != null)
                b.right.parent = a;

            a.parent = b;
            b.right = a;
            node.right = b;
            node.parent = t;
            splay(node);
        }
    }

    public void zigzag(BSTNode node) {
        BSTNode a = node.parent.parent;
        BSTNode b = node.parent;
        BSTNode t = node;

        if (check(a))
            t = null;
        else {

            if (a == a.parent.left) {
                a.parent.left = t;
                t = a.parent;
            } else {
                a.parent.right = t;
                t = a.parent;
            }
        }

        if (node.parent.left == node) {
            a.right = node.left;

            if (node.left != null)
                node.left.parent = a;

            a.parent = node;
            b.left = node.right;

            if (node.right != null)
                node.right.parent = b;

            b.parent = node;
            node.left = a;
            node.right = b;
            node.parent = t;
            splay(node);
        } else {
            a.left = node.right;
            if (node.right != null)
                node.right.parent = a;

            a.parent = node;
            b.right = node.left;

            if (node.left != null)
                node.left.parent = b;

            b.parent = node;
            node.right = a;
            node.left = b;
            node.parent = t;
            splay(node);
        }
    }

    public boolean check(BSTNode node) {
        if (node.parent == null)
            return true;
        else
            return false;
    }

    public void bfs() {
        Queue<BSTNode> tree = new ArrayDeque<>();
        tree.add(head);
        while (!tree.isEmpty()) {
            BSTNode node = tree.remove();
            System.out.println(node.value);

            if (node.left != null)
                tree.add(node.left);

            if (node.right != null)
                tree.add(node.right);
        }
    }
}
