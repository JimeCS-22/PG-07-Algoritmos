package pg07algoritmos.model.Tree;

public class BST<T extends Comparable<T>> extends BTree<T> {

    @Override
    public void add(T element) {
        this.root = add(root, element);
    }

    //Inicialmente el arbol estará apuntando a Null, luego pasará ser (Null, [Valor]), luego de eso se agregará
    //Despues se llama recursivamente para seguir con el mismo proceso con todos los elementos
    private BTreeNode<T>add(BTreeNode<T> node, T element){
        if(node == null){
            node = new BTreeNode<>(element);
        }else if(compareElement(element, node.data) < 0){
           node.left = add(node.left, element);
        } else if(compareElement(element, node.data) > 0){
            node.right = add(node.right, element);
        }
        return node; //Retorna el árbol modificado

    }


    @Override
    public boolean contains(T element) throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Search Tree is Empty");
        return binarySearch(this.root, element);
    }

    public boolean binarySearch(BTreeNode<T> node, T element){
        if(node == null) return false;
        if(equals(node.data, element)) return true;
        else if(compareElement(element, node.data) < 0)
            return binarySearch(node.left, element);
        else return binarySearch(node.right, element);
    }

    @Override
    public void remove(T element) throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Search Tree is Empty");
        root = remove(root, element);
    }

    private BTreeNode<T>remove(BTreeNode<T> node, T element){
        if(node != null){
            if(compareElement(element, node.data) < 0)
            node.left = remove(node.left, element);
            else if(compareElement(element, node.data) > 0)
            node.right = remove(node.right, element);
            else if(equals(node.data, element)){ //Ya lo encontró
                //Caso 1: El nodo a suprimir no tiene hijos, es una hoja
                if (node.left == null && node.right == null)return null;
                //Caso 2: El nodo a suprimir solo tiene un hijo
                //  En este caso, el nodo es reemplazado por su hijo
                else if (node.left != null && node.right == null) return node.left;
                else if (node.left == null && node.right != null) return node.right;
                //Caso 3: El nodo a suprimir tiene dos hijos
                else{
                    //Se obtiene el elemento menor del subarbol derecho
                    //Se reemplaza la data del nodo por ese valor
                    //Se elimina el valor
                    T minValue = min(node.right);
                    node.data = minValue;
                    node.right = remove(node.right, minValue);
                }
            }
        }
        return node;
    }

    @Override
    public T min() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Search Tree is empty");
        return min(root);
    }
//publico para que se pueda usar en AVL
    public T min(BTreeNode<T> node){
        if(node.left != null) return min(node.left);
        return node.data;
    }

    @Override
    public T max() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Search Tree is empty");
        return max(root);
    }

    public T max(BTreeNode<T> node)  {
        if(node.right != null) return max(node.right);
        return node.data;
    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary Search Tree is empty");
        return preOrder(root);
    }

    //Recorrido: N-L-R
    private String preOrder(BTreeNode<T> node){
        String result = "";
        if(node != null) {
            result  = node.data + ", ";
            result += preOrder(node.left);
            result += preOrder(node.right);

        }
        return result;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Binary Search Tree is empty";
        String result = "Binary Tree Tour\n";
        try {
        result += "PreOrder (N-L-R): " + preOrder() + "\n";
        result += "InOrder (L-N-R): "  + inOrder() + "\n";
        result += "PostOrder (L-R-N): "  + postOrder() + "\n";

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
        return result;
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

        if (node.left != null && node.right == null) {
            result.append("Dad: ").append(node.data).append("  Child: ").append(node.left.data).append("  \n");
        }
        else if (node.right != null && node.left == null) {
            result.append("Dad: ").append(node.data).append("  Child: ").append(node.right.data).append("  \n");
        }
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
