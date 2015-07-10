package hello.data.struct.tree;

import lombok.Data;

/**
 * Created by Junwen on 7/9/15.
 */
public class BTree <T extends Comparable<T>>{

    int count;

    public Node root;

    @Data
    private static class Node <T> {
        Node left;
        Node right;
        Node cousin;
        T data;

        public Node(T data)
        {
            this.data = data;
        }
    }

    void insert(T data)
    {
        Node node = new Node(data);
        node.setData(data);
    }

    void traversal(Node node)
    {

        System.out.println(node.data);
        if(node.left != null)
        {
            traversal(node.left);
        }
        if(node.right != null)
        {
            traversal(node.right);
        }

    }

    public Node search(T data, Node from)
    {
        T currData = (T) from.getData();
        System.out.println(currData.compareTo(data));
        if(currData.compareTo(data) == 0)
        {
            return from;
        }
        if(currData.compareTo(data) > 0)
        {
            if(from.getLeft() != null)
            {
                return search(data, from.getLeft());
            }


        } else
        {
            if(null != from.getRight())
            {
                return search(data, from.getRight());
            }
        }

        return null;

    }

    Node setCousin(Node node)
    {
        if(null == node)
            return node;
        if(node.getLeft() != null)
        {
            if(null != node.getRight())
            {
                node.setCousin(node.getRight());
            } else {

                Node pNode = node.cousin;
                Node cousin = null;
                while(pNode != null && cousin == null) {
                    cousin = pNode.getLeft() == null? (pNode.getRight() == null? null: pNode.getRight()): pNode.getLeft();
                    pNode = pNode.cousin;
                }
            }


        }

        if(node.getRight() != null)
        {
            Node pNode = node.cousin;
            Node cousin = null;
            while(pNode != null && cousin == null)
            {
                cousin = pNode.getLeft() == null? (pNode.getRight() == null? null: pNode.getRight()): pNode.getLeft();
                pNode = pNode.cousin;
            }
        }

        setCousin(node.getLeft());
        setCousin(node.getRight());

        return node;
    }


    public static void main(String[] args) {
        Node node = new Node("a");
        Node node2 = new Node("b");
        Node node3 = new Node("c");
        Node node4 = new Node("d");
        Node node5 = new Node("e");
        Node node6 = new Node("f");
        Node node7 = new Node("g");
        Node node8 = new Node("h");
        Node node9 = new Node("i");

        node.setLeft(node2);
        node.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node4.setLeft(node8);
        node4.setRight(node9);

        BTree tree = new BTree<>();
        tree.root = node;
        tree.traversal(tree.root);

        Node nodeRs = tree.search("i", tree.root);
        System.out.println(nodeRs.getData());
    }
}
