package model.Tree;

/**
 * LAB01 y Lexis son la misma persona pero en computadora diferente, una es en la universidad y la otra desde la casa.
 */

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Tree.BTree;
import pg07algoritmos.model.Tree.BTreeNode;
import pg07algoritmos.model.Tree.TreeException;

import java.util.Random;
class BTreeTest {

    @Test
    public void insert() {
        BTree<Integer> bTree= new BTree();
        bTree.add(10);
        bTree.add(20);
        bTree.add(30);
        bTree.add(40);
        for (int i = 0; i < 10; i++) {

            int value = new Random().nextInt(10, 50);
            bTree.add(value);

        }
        System.out.println(bTree);
        try {
            System.out.println("Tree size: " + bTree.size());
            System.out.println("Min value: "+ bTree.min());
            System.out.println("Min value: "+ bTree.max());
            for (int i = 0; i < 10; i++) {

                int value = new Random().nextInt(10 , 50);
                System.out.println(bTree.contains(value) ? " [ " + value + " ] exists. Height "+bTree.height(value) : value + " does not exist");
                System.out.println();

            }

            //Revision
            System.out.println("Metodo 1 para la practica de examen:");
            System.out.println(bTree.printNodesWithChildren());
            System.out.println("Metodo 2 para la practica de examen:");
            System.out.println(bTree.printNodes1Child());
            System.out.println("Metodo 3 para la practica de examen:");
            System.out.println(bTree.printNodes2Child());
            System.out.println("Metodo 4 para la practica de examen");
            System.out.println(bTree.printLeaves());
            System.out.println("Metodo 5 para la practica de examen:");
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(10, 50);

                Object gf = bTree.grandFather(value);
                System.out.println("Metodo 5 (Abuelo) de " + value + ": " + (gf instanceof String ? gf : "es " + gf));

                Object f = bTree.father(value);
                System.out.println("Metodo 6 (Padre) de " + value + ": " + (f instanceof String ? f : "es " + f));

                Object b = bTree.brother(value);
                System.out.println("Metodo 7 (Hermano) de " + value + ": " + (b instanceof String ? b : "es " + b));

                Object c = bTree.cousins(value);
                System.out.println("Metodo 8 (Primos) de " + value + ": " + (c instanceof String ? c : "son " + c));

                System.out.println("Metodo 9 (Subárbol) de " + value + ":");
                System.out.println(bTree.printSubtree(value));
                System.out.println("-----------------------------------");
            }
            System.out.println("Metodo 10 para la practica de examen:");
            System.out.println(bTree.totalLeaves());


            System.out.println();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testBTreeSum(){
        System.out.println("Metodo 11 para la practica de examen:");
        BTree<Integer> tree1 = new BTree<>();
        BTree<Integer> tree2 = new BTree<>();

        for(int i = 1; i <= 5; i++){
            tree1.addBFS(i * 10);
            tree2.addBFS(i);
        }

        System.out.println("Tree 1: " + tree1 + "\n");

        System.out.println("Tree 2: " + tree2 + "\n");

        BTree<Integer> result = tree1.bTreeSum(tree1, tree2);

        System.out.println("Tree Sum" + result);
    }

    @Test
    void testBtNodeSum(){
        System.out.println("Metodo 12 para la practica de examen: " + "\n");
        BTree<Integer> tree = new BTree<>();

        tree.addBFS(10);
        tree.addBFS(5);
        tree.addBFS(8);
        tree.addBFS(2);
        tree.addBFS(3);

        System.out.println("Original Tree: " + tree + "\n");

        BTree<Integer> result = tree.btNodeSum(tree);

        System.out.println("Node Sum Tree: " + result);
    }

    @Test
    void testTighten(){
        System.out.println("Metodo 13 para la practica de examen:" + "\n");
        BTree<Integer> tree = new BTree<>();

        tree.root = new BTreeNode<>(10);
        tree.root.left = new BTreeNode<>(20);
        tree.root.left.right = new BTreeNode<>(30);
        tree.root.left.right.left = new BTreeNode<>(40);

        System.out.println("Before Tighten: " + tree + "\n");
        tree.tighten();

        System.out.println("After Tighten: " + tree);
    }

    @Test
    void testIsABM(){
        System.out.println("Metodo 14.a para la practica de examen:" + "\n");
        BTree<Integer> tree = new BTree<>();

        tree.root = new BTreeNode<>(1);
        tree.root.left = new BTreeNode<>(5);
        tree.root.right = new BTreeNode<>(8);
        tree.root.left.left = new BTreeNode<>(10);

        System.out.println(tree);
        System.out.println("Is ABM: " + tree.isABM(tree));
    }

    @Test
    void testJoinABM(){
        System.out.println("Metodo 14.b para la practica de examen:" + "\n");

        BTree<Integer> tree1 = new BTree<>();

        tree1.root = new BTreeNode<>(1);
        tree1.root.left = new BTreeNode<>(4);
        tree1.root.right = new BTreeNode<>(6);

        BTree<Integer> tree2 = new BTree<>();

        tree2.root = new BTreeNode<>(2);
        tree2.root.left = new BTreeNode<>(7);
        tree2.root.right = new BTreeNode<>(9);

        System.out.println("Tree 1: " + tree1);
        System.out.println("Tree 2: " + tree2);

        BTree<Integer> result = tree1.joinABM(tree1, tree2);

        System.out.println("Join ABM: " + result);
    }


    @Test
    void testHeight(){
        BTree<Integer> bTree = new BTree<>();
        for (int i = 0; i < 6; i++) {
            int value = new Random().nextInt(1,30);
            bTree.add(value);
        }
            try {
                System.out.println(bTree);
                System.out.println("Tree size: " + bTree.size());
                System.out.println("Min value: "+ bTree.min());
                System.out.println("Max value: "+ bTree.max());
                System.out.println("Tree height: "+ bTree.height());
            } catch (TreeException e) {
                throw new RuntimeException(e);
            }

    }


    @Test
    void testRemove() {
        BTree<Integer> bTree = new BTree<>();
        for (int i = 0; i < 10; i++) {
            int value = new Random().nextInt(1, 30);
            bTree.add(value);
        }
        System.out.println(bTree);

        try {
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(1, 30);
                bTree.add(value);
                if (bTree.contains(value)) {
                    bTree.remove(value);
                    System.out.println("Removed value: "+ value);
                }
            }
                System.out.println(bTree);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void nodeHeightTest() {
        BTree<Integer> bTree = new BTree<>();
        for (int i = 0; i < 5; i++) {
            int value = new Random().nextInt(1, 30);
            bTree.add(value);
        }
        System.out.println(bTree);

        try {
            for (int i = 0; i < 5; i++) {
                int value = new Random().nextInt(1, 30);
                if (bTree.contains(value)) {
                    System.out.println("Nodes Height: \n"+bTree.nodeHeight());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}