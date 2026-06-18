package model.Tree;

/**
 * LAB01 y Lexis son la misma persona pero en computadora diferente, una es en la universidad y la otra desde la casa.
 */

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Tree.BTree;
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