package model.Tree;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Tree.BST;
import pg07algoritmos.model.Tree.TreeException;

import java.util.Random;

class BSTTest {

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