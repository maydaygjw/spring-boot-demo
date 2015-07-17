package hello.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Junwen on 7/16/15.
 */
public class ReverseLink {

    @Data
    @AllArgsConstructor
    static class Node<T> {
        T data;
        Node<T> next;
    }

    public static void main(String[] args) {

        Node d = new Node("D", null);
        Node c = new Node("C", d);
        Node b = new Node("B", c);
        Node a = new Node("A", b);

        print(a);
        Node newRoot = reverse(a);
        print(newRoot);
    }

    private static void print(Node node) {
        if(node != null)
        {
            System.out.print(node.getData() + "->");
            print(node.next);
        } else {
            System.out.println("NULL");
        }

    }


    public static Node reverse(Node node) {

        Node prev = node;
        node = node.next;
        prev.next = null;


        while(node != null) {
            Node tmp = node.next;
            node.next = prev;
            prev = node;
            node = tmp;
        }

        return prev;
    }
}
