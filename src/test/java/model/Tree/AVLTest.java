package model.Tree;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Tree.AVL;
import pg07algoritmos.model.Tree.BTree;
import pg07algoritmos.model.Tree.BTreeNode;
import pg07algoritmos.model.Tree.TreeException;

import java.util.Random;

class AVLTest {
    //Punto 3 PG-5
    //a. Cree una clase de testeo que permita insertar en forma balanceada 30
    //números aleatorios entre 20 y 200.
    @Test
    void avlTest() {
        AVL<Integer> avl = new AVL<>();
        for (int i = 0; i < 30; i++) {
            int value = new Random().nextInt(20, 200);
            avl.add(value);
        }
        //b. Muestre el contenido del árbol por consola.
        System.out.println(avl);

        //c. Pruebe los métodos: size(), min(), max().
        try {
            System.out.println("Tree size: " + avl.size());
            System.out.println("Min value: "+ avl.min());
            System.out.println("Max value: "+ avl.max());

            //d. Indique si el árbol está balanceado utilizando un método isBalanced() que indique si el árbol está balanceado.
            System.out.println("The AVL tree is balanced? : " + avl.isBalanced());

            //e. Elimine 5 elementos del árbol. Utilice el método remove(element).
            int removedElements = 0;

            while(removedElements < 5){
                int value = new Random().nextInt(20, 200);
                if (avl.contains(value)) {
                    avl.remove(value);
                    System.out.println("Removed value: "+ value);
                    removedElements++;
                }
            }
            //f. Muestre el contenido del árbol por consola.
            System.out.println(avl);
            //g. Vuelva a comprobar si el árbol está balanceado.
            System.out.println( avl.getRebalancingInfo());
            System.out.println("The AVL tree is balanced? : " + avl.isBalanced());


        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    //h. Luego de eliminar varios elementos, el árbol AVL puede quedar o no
    //balanceado. Modifique el método remove de la clase AVL o haga un nuevo
    //método que permita re-equilibrar el árbol para que siga siendo un árbol AVL.
    //i. Muestre el contenido del árbol por consola.
    //j. Compruebe nuevamente si el árbol está balanceado.

    @Test
    public void insert() {
        AVL<Integer> avlTree = new AVL<>();
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(30);
        avlTree.add(40);
        for (int i = 0; i < 10; i++) {

            int value = new Random().nextInt(10, 50);
            avlTree.add(value);

        }
        System.out.println(avlTree);
        try {
            System.out.println("Tree size: " + avlTree.size());
            System.out.println("Min value: "+ avlTree.min());
            System.out.println("Min value: "+ avlTree.max());
            for (int i = 0; i < 10; i++) {

                int value = new Random().nextInt(10 , 50);
                System.out.println(avlTree.contains(value) ? " [ " + value + " ] exists. Height "+ avlTree.height(value) : value + " does not exist");
                System.out.println();

            }

            //Revision
            System.out.println("Metodo 1 para la practica de examen:");
            System.out.println(avlTree.printNodesWithChildren());
            System.out.println("Metodo 2 para la practica de examen:");
            System.out.println(avlTree.printNodes1Child());
            System.out.println("Metodo 3 para la practica de examen:");
            System.out.println(avlTree.printNodes2Child());
            System.out.println("Metodo 4 para la practica de examen");
            System.out.println(avlTree.printLeaves());
            System.out.println("Metodo 5 para la practica de examen:");
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(10, 50);

                Object gf = avlTree.grandFather(value);
                System.out.println("Metodo 5 (Abuelo) de " + value + ": " + (gf instanceof String ? gf : "es " + gf));

                Object f = avlTree.father(value);
                System.out.println("Metodo 6 (Padre) de " + value + ": " + (f instanceof String ? f : "es " + f));

                Object b = avlTree.brother(value);
                System.out.println("Metodo 7 (Hermano) de " + value + ": " + (b instanceof String ? b : "es " + b));

                Object c = avlTree.cousins(value);
                System.out.println("Metodo 8 (Primos) de " + value + ": " + (c instanceof String ? c : "son " + c));

                System.out.println("Metodo 9 (Subárbol) de " + value + ":");
                System.out.println(avlTree.printSubtree(value));
                System.out.println("-----------------------------------");
            }
            System.out.println("Metodo 10 para la practica de examen:");
            System.out.println(avlTree.totalLeaves());


            System.out.println();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testBTreeSumAVL() {
        System.out.println("Metodo 11 para la practica de examen:");
        AVL<Integer> tree1 = new AVL<>();
        AVL<Integer> tree2 = new AVL<>();

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
        AVL<Integer> tree = new AVL<>();

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
        AVL<Integer> tree = new AVL<>();

        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);

        System.out.println("Before Tighten: " + tree + "\n");
        tree.tighten();

        System.out.println("After Tighten: " + tree);

        System.out.println("Balance: " + tree.isBalanced());
    }

    @Test
    void testIsABMAVL() {
        System.out.println("Metodo 14.a para la practica de examen:" + "\n");
        AVL<Integer> tree = new AVL<>();

        tree.add(10);
        tree.add(20);
        tree.add(30);

        System.out.println(tree);
        System.out.println("Is ABM: " + tree.isABM(tree));
    }

    @Test
    void testJoinABMAVL() {
        System.out.println("Metodo 14.b para la practica de examen:" + "\n");

        AVL<Integer> tree1 = new AVL<>();
        AVL<Integer> tree2 = new AVL<>();

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

}