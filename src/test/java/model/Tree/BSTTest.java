package model.Tree;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Tree.AVL;
import pg07algoritmos.model.Tree.BST;
import pg07algoritmos.model.Tree.BTree;
import pg07algoritmos.model.Tree.TreeException;

import java.util.Random;

class BSTTest {

    @Test
    public void insert() {
        BST<Integer> bstTree = new BST<>();
        bstTree.add(10);
        bstTree.add(20);
        bstTree.add(30);
        bstTree.add(40);
        for (int i = 0; i < 10; i++) {

            int value = new Random().nextInt(10, 50);
            bstTree.add(value);

        }
        System.out.println(bstTree);
        try {
            System.out.println("Tree size: " + bstTree.size());
            System.out.println("Min value: "+ bstTree.min());
            System.out.println("Min value: "+ bstTree.max());
            for (int i = 0; i < 10; i++) {

                int value = new Random().nextInt(10 , 50);
                System.out.println(bstTree.contains(value) ? " [ " + value + " ] exists. Height "+ bstTree.height(value) : value + " does not exist");
                System.out.println();

            }

            //Revision
            System.out.println("Metodo 1 para la practica de examen:");
            System.out.println(bstTree.printNodesWithChildren());
            System.out.println("Metodo 2 para la practica de examen:");
            System.out.println(bstTree.printNodes1Child());
            System.out.println("Metodo 3 para la practica de examen:");
            System.out.println(bstTree.printNodes2Child());
            System.out.println("Metodo 4 para la practica de examen");
            System.out.println(bstTree.printLeaves());
            System.out.println("Metodo 5 para la practica de examen:");
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(10, 50);

                Object gf = bstTree.grandFather(value);
                System.out.println("Metodo 5 (Abuelo) de " + value + ": " + (gf instanceof String ? gf : "es " + gf));

                Object f = bstTree.father(value);
                System.out.println("Metodo 6 (Padre) de " + value + ": " + (f instanceof String ? f : "es " + f));

                Object b = bstTree.brother(value);
                System.out.println("Metodo 7 (Hermano) de " + value + ": " + (b instanceof String ? b : "es " + b));

                Object c = bstTree.cousins(value);
                System.out.println("Metodo 8 (Primos) de " + value + ": " + (c instanceof String ? c : "son " + c));

                System.out.println("Metodo 9 (Subárbol) de " + value + ":");
                System.out.println(bstTree.printSubtree(value));
                System.out.println("-----------------------------------");
            }
            System.out.println("Metodo 10 para la practica de examen:");
            System.out.println(bstTree.totalLeaves());


            System.out.println();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testBTreeSumAVL() {
        System.out.println("Metodo 11 para la practica de examen:");
        BST<Integer> tree1 = new BST<>();
        BST<Integer> tree2 = new BST<>();

        for(int i = 1; i <= 5; i++){
            tree1.add(i * 10);
            tree2.add(i);
        }

        System.out.println("Tree 1: " + tree1 + "\n");

        System.out.println("Tree 2: " + tree2 + "\n");

        BTree<Integer> result = tree1.bTreeSum(tree1, tree2);

        System.out.println("Tree Sum" + result);
    }

    @Test
    void testBtNodeSumAVL() {
        System.out.println("Metodo 12 para la practica de examen: " + "\n");
        BST<Integer> tree = new BST<>();

        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(2);
        tree.add(8);

        System.out.println("Original Tree: " + tree + "\n");

        BTree<Integer> result = tree.btNodeSum(tree);

        System.out.println("Node Sum Tree: " + result);
    }

    @Test
    void testTightenAVL() {
        System.out.println("Metodo 13 para la practica de examen:" + "\n");
        BST<Integer> tree = new BST<>();

        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);

        System.out.println("Before Tighten: " + tree + "\n");
        tree.tighten();

        System.out.println("After Tighten: " + tree);

    }

    @Test
    void testIsABMAVL() {
        System.out.println("Metodo 14.a para la practica de examen:" + "\n");
        BST<Integer> tree = new BST<>();

        tree.add(10);
        tree.add(20);
        tree.add(30);

        System.out.println(tree);
        System.out.println("Is ABM: " + tree.isABM(tree));
    }

    @Test
    void testJoinABMAVL() {
        System.out.println("Metodo 14.b para la practica de examen:" + "\n");

        BST<Integer> tree1 = new BST<>();
        BST<Integer> tree2 = new BST<>();

        tree1.add(1);
        tree1.add(4);
        tree1.add(6);

        tree2.add(2);
        tree2.add(7);
        tree2.add(9);

        System.out.println("Tree 1: " + tree1);
        System.out.println("Tree 2: " + tree2);

        BTree<Integer> result = tree1.joinABM(tree1, tree2);

        System.out.println("Join ABM: " + result);
    }



    @Test
    void add() {
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 10; i++) {
            int value = new Random().nextInt(1, 30);
            bst.add(value);
        }
        System.out.println(bst);
        try {
            System.out.println("Tree size: " + bst.size());
            System.out.println("Min value: " + bst.min());
            System.out.println("Min value: " + bst.max());
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(10, 50);
                System.out.println(bst.contains(value) ? "[" + value + "] exists. Height " + bst.height(value) : "[" + value + "] does not exist");


            }
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testRemove() {
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 10; i++) {
            int value = new Random().nextInt(1, 30);
            bst.add(value);
        }
        System.out.println(bst);

        try {
            for (int i = 0; i < 5; i++) {
                int value = new Random().nextInt(1, 30);
                bst.add(value);
                if (bst.contains(value)) {
                    bst.remove(value);
                    System.out.println("Removed value: "+ value);
                }
            }
            System.out.println(bst);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Test BST Lab 5
    @Test
    void testBST() {

        BST<Integer> bst = new BST<>();

        Random random = new Random();

        // =========================================
        // A) Agregar 30 números NO repetidos
        // entre 0 y 50
        // =========================================

        while (true) {

            int value = random.nextInt(51);

            try {

                // solo agrega si NO existe
                if (!bst.contains(value)) {
                    bst.add(value);
                }

            } catch (TreeException e) {

                // el árbol está vacío al inicio
                bst.add(value);
            }

            try {

                if (bst.size() == 30) {
                    break;
                }

            } catch (TreeException e) {
                throw new RuntimeException(e);
            }
        }

        // =========================================
        // Mostrar árbol
        // =========================================

        System.out.println("\n========= BST =========");
        System.out.println(bst);

        try {

            // =========================================
            // B) Probar:
            // size(), height(), height(element),
            // min(), max()
            // =========================================

            System.out.println("========= TREE INFO =========");

            System.out.println(
                    "Size: " + bst.size()
            );

            System.out.println(
                    "Tree Height: " + bst.height()
            );

            System.out.println(
                    "Root Height: " +
                            bst.height(bst.root.data)
            );

            System.out.println(
                    "Min Value: " + bst.min()
            );

            System.out.println(
                    "Max Value: " + bst.max()
            );

            // =========================================
            // C) Generar 20 valores aleatorios
            // entre 0 y 50.
            // Si existen -> eliminarlos
            // =========================================

            System.out.println(
                    "\n========= SEARCH & REMOVE ========="
            );

            for (int i = 0; i < 20; i++) {

                int value = random.nextInt(51);

                if (bst.contains(value)) {

                    System.out.println(
                            value + " exists -> removed"
                    );

                    bst.remove(value);

                } else {

                    System.out.println(
                            value + " does not exist"
                    );
                }
            }

            // =========================================
            // Mostrar árbol actualizado
            // =========================================

            System.out.println(
                    "\n========= BST AFTER REMOVE ========="
            );

            System.out.println(bst);

            // =========================================
            // D) Mostrar altura de cada elemento
            // en recorrido PreOrder
            // =========================================

            System.out.println(
                    "\n========= NODE HEIGHTS ========="
            );

            System.out.println(
                    bst.nodeHeight()
            );

        } catch (TreeException e) {

            throw new RuntimeException(e);
        }
    }
}