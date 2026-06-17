package pg07algoritmos.model.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BTree<T extends Comparable<T>> implements Tree<T> {
    public BTreeNode<T> root; //representa la unica entrada al árbol

    //Constructor
    public BTree(){
        this.root =null;
    }
    @Override
    public int size() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return size(root);
    }

    private int size(BTreeNode<T> nodo){
        if(nodo == null) return 0;
        return size(nodo.left) + size(nodo.right) + 1;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return  this.root == null;
    }

    @Override
    public boolean contains(T element) throws TreeException {
        if (isEmpty()) throw new TreeException("Binary Tree is empty");
        return binarySearch(this.root, element);
    }

    public boolean binarySearch( BTreeNode<T> node, T element){
        if (node == null) return false;
        else if (equals(node.data, element)) return true;
        else return  binarySearch(node.left, element) || binarySearch(node.right, element);
    }


    @Override
    public void add(T element) {
        this.root = add(root, element, "root");
    }

    private BTreeNode<T>add(BTreeNode<T> node, T element){

        if(node == null){

            node = new BTreeNode<>(element);

        }else{
            //debemos establecer algún criterio para insertar elementos
            int value = new Random().nextInt(10);
            if (value % 2==0) //si el valor es para enserte por la izquierda
                node.left = add(node.left, element);
            else node.right = add(node.right, element);
        }

        return node;

    }

    private BTreeNode<T>add(BTreeNode<T> node, T element, String path){

        if(node == null){

            node = new BTreeNode<>(element, path);

        }else{
            //debemos establecer algún criterio para insertar elementos
            int value = new Random().nextInt(10);
            if (value % 2==0) //si el valor es para enserte por la izquierda
                node.left = add(node.left, element,  path + " /left");
            else node.right = add(node.right, element,   path + " /right");
        }

        return node;

    }


    @Override
    public void remove(T element) throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        root = remove(root, element);
    }

    private BTreeNode<T> remove(BTreeNode<T> node, T element){
        if (node == null) {
            return null; // caso base: no hay nada que eliminar
        }

        if (equals(node.data, element)) { // encontró el elemento
            // Caso 1: sin hijos
            if (node.left == null && node.right == null) {
                return null;
            }
            // Caso 2: un solo hijo
            else if (node.left != null && node.right == null) {
                node.left = newPath(node.left, node.path);
                return node.left;
            } else if (node.left == null && node.right != null) {
                node.right = newPath(node.right, node.path);
                return node.right;
            }
            // Caso 3: dos hijos
            else {
                T minValue = minNode(node.right);
                node.data = minValue;
                node.right = remove(node.right, minValue);
            }
        } else {
            // recorrer recursivamente
            node.left = remove(node.left, element);
            node.right = remove(node.right, element);
        }

        return node;
    }


    /**
     * Este metodo sirve para actualizar la ruta de todos los nodos del subarbol
     */
    private BTreeNode<T> newPath(BTreeNode<T> node, String path) {
        if (node != null) {
            node.path = path;
            newPath(node.left, path+"/left");
            newPath(node.right, path+"/right");
        }
        return node;
    }


    //devuelve la altura de un elemento especifico dentro del arbol
    @Override
    public int height(T element) throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return height(root, element,0);
    }
    private int height(BTreeNode<T> node, T element, int count){
        if (node == null) {
            return 0;
        } else if (equals(node.data, element)) {
            return count;
        }else return Math.max(height(node.left,element,++count), height(node.right,element,count));
        //Con el método propio amerita cambios
       //return (int) maxElement(height(node.left,element,++count), height(node.right,element,count));
    }

    //Devuelve la altura del árbol
    @Override
    public int height() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return height(root)-1;//-1 porque la altura de la raiz es 0
    }

    public int height(BTreeNode<T> node){
        if (node == null) {
            return 0;
        }else return Math.max(height(node.left),height(node.right))+1;
    }
    @Override
    public T min() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return minNode(root);
    }
    //distinto nombre al de BST para que se pueda llamar correctamente en el AVL
    private T minNode(BTreeNode<T> node){

        if(node.left != null && node.right != null)//Caso 1. Cuando el nodo tiene los dos hijos
            return minElement(node.data,minElement(minNode(node.left), minNode(node.right)));
         else if (node.left != null) //Caso 2. Cuando el nodo tiene un solo hijo izquierdo
            return minElement(node.data, minNode(node.left));
        else if (node.right != null) //Caso 3. Cuando el nodo tiene un solo hijo derecho
            return minElement(node.data, minNode(node.right));
        else
        return node.data;// es una hoja
    }

    private T minElement(T a, T b){

        if(a == null) return b;
        else if(b == null) return a;
        return compareElement(a,b) <= 0 ? a : b;

    }



    @Override
    public T max() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return max(root);
    }
    private T max(BTreeNode<T> node){

        if(node.left != null && node.right != null)//Caso 1. Cuando el nodo tiene los dos hijos
            return maxElement(node.data,maxElement(max(node.left), max(node.right)));
        else if (node.left != null) //Caso 2. Cuando el nodo tiene un solo hijo izquierdo
            return maxElement(node.data, max(node.left));
        else if (node.right != null) //Caso 3. Cuando el nodo tiene un solo hijo derecho
            return maxElement(node.data, max(node.right));
        else
            return node.data;// es una hoja
    }

    private T maxElement(T a, T b){
        if(a == null) return b;
        else if(b == null) return a;
        return compareElement(a,b) >= 0 ? a : b;

    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return preOrder(root);
    }

    //Recorrido: N-L-R
    private String preOrder(BTreeNode<T> node){
        String result = "";
        if(node != null) {
            result  = node.data + "( " + node.path + " ) ";
            result += preOrder(node.left);
            result += preOrder(node.right);

        }
        return result;
    }

    @Override
    public String inOrder() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return inOrder(root);
    }

    //Recorrido: L-N-R
    private String inOrder(BTreeNode<T> node){
        String result = "";
        if(node != null) {
            result  = inOrder(node.left);
            result += node.data + ", " ;
            result += inOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");
        return postOrder(root);
    }

    //Recorrido: L-R-N
    private String postOrder(BTreeNode<T> node){
        String result = "";
        if(node != null) {
            result  = postOrder(node.left);
            result += postOrder(node.right);
            result += node.data + ", " ;

        }
        return result;
    }

    //TAREA
    //muestra por consola la altura de cada elemento del arbol
    @Override
    public String nodeHeight() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Tree is empty");

        StringBuilder result = new StringBuilder();

        nodeHeight(root, result);

        return result.toString();
    }
    private void nodeHeight(BTreeNode<T> node, StringBuilder result)
            throws TreeException {

        if (node != null) {

            result.append("Elemento: ")
                    .append(node.data)
                    .append(" -> Altura: ")
                    .append(height(node.data))
                    .append("\n");

            nodeHeight(node.left, result);
            nodeHeight(node.right, result);
        }
    }
    @Override
    public String toString() {
        if(isEmpty()) return "Binary Tree is empty";
        String result = "Binary Tree Tour\n";
        result += "PreOrder (N-L-R): " + preOrder(root) + "\n";
        result += "InOrder (L-N-R): "  + inOrder(root) + "\n";
        result += "PostOrder (L-R-N): "  + postOrder(root) + "\n";
        return result;
    }

    /**Metodos de ayuda**/
    public boolean equals(T a, T b)  {
        return a==null ? b==null : a.equals(b);
    }

    //metodo generico de comparacion
    public int compareElement(T a, T b) {
        return a.compareTo(b);
    }

    /**Para que el árbol sea BFS, se necesita un metodo diferente de add**/

    public void addBFS(T element) {

        BTreeNode<T> newNode =
                new BTreeNode<>(element);

        if(root == null){
            root = newNode;
            root.path = "root";
            return;
        }

        Queue<BTreeNode<T>> queue =
                new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){

            BTreeNode<T> current =
                    queue.poll();

            if(current.left == null){

                current.left = newNode;
                newNode.path =
                        current.path + "/left";
                return;
            }

            if(current.right == null){

                current.right = newNode;
                newNode.path =
                        current.path + "/right";
                return;
            }

            queue.offer(current.left);
            queue.offer(current.right);
        }
    }

    /**
     * Practica para examen: árboles binarios
     **/

    /**
     * 1.Devuelve un String con todos los nodos del árbol que
     * tienen hijos(uno o dos hijos), junto con sus hijos
     * Es parecido al preorder
     **/

    public String printNodesWithChildren(){

        if(isEmpty()) return "Binary Tree is empty";

        StringBuilder result = new StringBuilder();
        printNodesWithChildren(root, result);//Inicio revisando la raiz

        return result.toString();
    }

    //Usar recursividad
    private void printNodesWithChildren(BTreeNode<T> node,  StringBuilder result){
        if(node == null)//Caso base
            return;

        if(node.left != null || node.right != null){

            result.append(node.data).append(" -> ");

            if(node.left != null) result.append(node.left.data).append(" ");
            if(node.right != null) result.append(node.right.data).append(" ");
            result.append("\n");
        }

        printNodesWithChildren(node.left, result);
        printNodesWithChildren(node.right, result);


    }

    /**
     *2.Devuelve un string con todos los nodos del árbol
     *que tienen exactamente un hijo, junto con su hijo
     * Puede ser parecido al primero
     **/

    public String printNodes1Child(){

        if(isEmpty()) return "Binary Tree is empty";

        StringBuilder result = new StringBuilder();
        printNodes1Child(root, result);//Inicio revisando la raiz

        return result.toString();
    }

    //Metodo recursivo
    private void printNodes1Child(BTreeNode<T> node,  StringBuilder result){
        if(node == null)
            return;

        if(node.left != null && node.right== null)
            result.append("Dad: ").append(node.data).append("  Child: " ).append(node.left.data).append("  ");
        else if(node.right != null && node.left== null)
            result.append("Dad: ").append(node.data).append("  Child: " ).append(node.left.data).append("  ");

        printNodes1Child(node.left, result);
        printNodes1Child(node.right, result);

    }

    /**
     *3.Devuelve un string con todos los nodos del árbol
     * que tienen dos hijos, junto con su hijo
     * Es parecido a este solo hay que cambiar la condición
     **/
    public String printNodes2Child(){

        if(isEmpty()) return "Binary Tree is empty";

        StringBuilder result = new StringBuilder();
        printNodes2Child(root, result);

        return result.toString();
    }

    //Metodo recursivo
    private void printNodes2Child(BTreeNode<T> node,  StringBuilder result){
        if (node == null) {
            return;
        }

        if(node.left  != null && node.right!= null)
            result.append("Dad: ").append(node.data).append("  Child: ")
                    .append(node.left.data).append("  ").append(node.right.data).append("  ");
        result.append("\n");

        printNodes2Child(node.left, result);
        printNodes2Child(node.right, result);
    }

    /**
     *4.Devuelve un string con todos los nodos del árbol
     * que no tenan hijos
     * La candición es que que izquierda y dereca sean  nulos
     **/
    public String printLeaves(){

        if(isEmpty()) return "Binary Tree is empty";

        StringBuilder result = new StringBuilder();
        printLeaves(root, result);

        return result.toString();
    }

    //Metodo recursivo
    private void printLeaves(BTreeNode<T> node,  StringBuilder result){
        if(node == null)
            return;

        if(node.left == null && node.right == null)
            result.append("Leaves: ").append(node.data).append("\n");


        printLeaves(node.left, result);
        printLeaves(node.right, result);
    }

    /**
     *5.Devuelve el abuelo de elemento dado.Si no tiene abuelo
     * debera de indicar: no tiene abuelo
     **/
    public Object grandFather(T element) {

        if(isEmpty()) return "Binary Tree is empty";
        BTreeNode<T> parent = findFather(root, element);

        if(parent == null || parent == root) return "No tiene abuelo";

        BTreeNode<T> grandFather = findFather(root, parent.data);

        if(grandFather != null) return grandFather.data;
        else return "No tiene abuelo";
    }

    /**
     *6.Devuelve el padre del elemento dado. Si no tiene padre debera
     * de indicar. no tiene padre
     **/
    public Object father(T element) {

        if(isEmpty()) return "Binary Tree is empty";
        if (equals(root.data, element)) return "El nodo es la raiz, no tiene padre";

        BTreeNode<T> node = findFather(root,element);
        if(node == null) return "El nodo no tiene padre";

        return node.data;
    }

    public BTreeNode<T> findFather(BTreeNode<T> node,T element){

        if(node == null) return null;

        if(node.left != null && equals(node.left.data,element) ||
                (node.right != null && equals(node.right.data,element))){
            return node;
        }

        BTreeNode<T> father = findFather(node.left,element);
        if(father != null) return father;

        return findFather(node.right,element);

    }
    /**
     *7.Devuelve el hermano del elemento dado. Si no tiene hermano debera
     * de indicar que no tiene hermano
     **/
    public Object brother(T element) {
        if(isEmpty()) return "Binary Tree is empty";
        if(equals(root.data, element)) return "No tiene hermano";

        BTreeNode<T> node = findFather(root,element);

        if(node == null) return "No tiene hermano";

        if(node.left != null && equals(node.left.data,element)){
            return (node.right != null ? node.right.data : "No tiene hermano");
        }

        if(node.right != null && equals(node.right.data,element)){
            return (node.left != null ? node.left.data : "No tiene hermano");
        }

        return "No tiene hermano";
    }
    /**
     *8.Devuelve el primo de un elemento dado. Si no tienen primo debera
     * de indicar que no tiene primo
     **/
    public Object cousins(T element) {

        if(isEmpty()) return "Binary Tree is empty";

        if(equals(root.data, element)) return "No tiene primos";

        BTreeNode<T> parent = findFather(root, element);

        if(parent == null) return "No tiene primos";

        BTreeNode<T> grandFather = findFather(root, parent.data);

        if(grandFather == null) return "No tiene primos";

        BTreeNode<T> uncle = null;

        if(grandFather.left == parent)
            uncle = grandFather.right;
        else if(grandFather.right == parent)
            uncle = grandFather.left;

        if(uncle == null) return "No tiene primos";

        StringBuilder sb = new StringBuilder();

        addChild(uncle, sb);

        return sb.length() == 0 ? "No tiene primos" : sb.toString();
    }

    private void addChild(BTreeNode<T> node, StringBuilder sb){
        if (node.left != null) sb.append(node.left.data).append(" ");
        if (node.right != null) sb.append(node.right.data).append(" ");
    }
    /**
     *9.Devuelve un String con todos los elementos
     * que conforman el subárbol a partir del nodo del elemento dado.
     **/
    public String printSubtree(T element) {
        BTreeNode<T> subRoot = buscarNodo(root, element);

        if (subRoot == null) return "Elemento no encontrado";
        return preOrder(subRoot);
    }
    private BTreeNode<T> buscarNodo(BTreeNode<T> node, T element) {
        if (node == null) return null;
        if (equals(node.data, element)) return node;

        BTreeNode<T> left = buscarNodo(node.left, element);
        if (left != null) return left;
        return buscarNodo(node.right, element);
    }
    /**
     *10.Devuelve un entero indicando el
     * número de hojas que tienen el árbol
     * La candición es que que izquierda y dereca sean  nulos y ahi sumar
     **/
    public int totalLeaves(){

        if(isEmpty()) return 0;

        return totalLeaves(root);
    }

    private int totalLeaves(BTreeNode<T> node){
        if(node == null) return 0;

        if(node.left == null && node.right == null)
            return 1;

        return totalLeaves(node.left) + totalLeaves(node.right);
    }






}
