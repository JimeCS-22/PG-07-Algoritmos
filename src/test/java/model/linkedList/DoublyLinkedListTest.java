package model.linkedList;

import pg07algoritmos.model.linkedList.DoublyLinkedList;

public class DoublyLinkedListTest {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.add(45);
        list.add(42);
        list.add(31);
        list.add(31);
        list.add(34);
        list.add(2);
        list.add(38);
        list.add(2);
        list.add(20);
        list.add(44);

        System.out.println(list);
        System.out.println("__________________________________________________");

        System.out.println("addAtPosK(100, 1)");
        list.addAtPosK(100, 1);
        System.out.println(list);

        System.out.println("addAtPosK(200, 5)");
        list.addAtPosK(200, 5);
        System.out.println(list);

        System.out.println("addAtPosK(300, 10)");
        list.addAtPosK(300, 10);
        System.out.println(list);

        System.out.println("addAtPosK(400, 14)");
        list.addAtPosK(400, 14);
        System.out.println(list);

        System.out.println("addAtPosK(500, 20)");
        list.addAtPosK(500, 20);
        System.out.println(list);

    }
}
