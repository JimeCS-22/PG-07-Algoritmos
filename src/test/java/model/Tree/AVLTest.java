package model.Tree;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Tree.AVL;
import pg07algoritmos.model.Tree.TreeException;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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

}