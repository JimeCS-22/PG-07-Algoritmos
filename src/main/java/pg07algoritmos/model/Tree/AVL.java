package pg07algoritmos.model.Tree;
/**
 * Árbol AVL (Adelson-Velsky & Landis, 1962).

 * Árbol BST auto-balanceado que mantiene el Factor de Equilibrio (FE)
 * de cada nodo dentro del rango {−1, 0, +1}.

 * FE(nodo) = altura(izquierdo) − altura(derecho)

 * Si |FE| > 1 tras una inserción/eliminación se aplica:
 *   LL → Rotación simple derecha
 *   RR → Rotación simple izquierda
 *   LR → Rotación doble: rotateLeft(y) + rotateRight(z)
 *   RL → Rotación doble: rotateRight(y) + rotateLeft(z)

 * Todas las operaciones garantizan O(log n).
 */
public class AVL <T extends Comparable<T>> extends BST<T>{

    @Override
    public void add(T element) {
        this.root = add(root, element, "root");
    }

    private BTreeNode<T> add(BTreeNode<T> node, T element, String path) {
        if (node == null) {
            node =  new BTreeNode<>(element, path);
        }else  if(compareElement(element, node.data)<0)
            node.left = add(node.left, element, path+"/left");
        else if (compareElement(element, node.data)>0)
            node.right = add(node.right, element, path+"/right");

        //------------------- Luego de insertar, se debe rebalancear ---------------//
        //obtenemos el factor de balanceo
        int balance = getBalanceFactor(node);
        // Caso 1: Left Left Case - LL
        if (balance > 1 && compareElement(element, node.left.data)<0){
            node.path = path+"/→LL-Simple Right Rotate (Rebalancing by insertion)";
            return rightRotate(node);
        }
        // Caso 2: Right Right Case - RR
        if (balance < -1 && compareElement(element, node.right.data)>0){
            node.path = path+"/→RR-Simple Left Rotate (Rebalancing by insertion)";
            return leftRotate(node);
        }
        // Caso 3: Left Right Case - LR
        if (balance > 1 && compareElement(element, node.left.data)>0) {
            node.path = path+"/→LR-Double Left-Right Rotate (Rebalancing by insertion)";
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Caso 4: Right Left Case - RL
        if (balance < -1 && compareElement(element, node.right.data)<0) {
            node.path = path+"/→RL-Double Right-Left Rotate (Rebalancing by insertion)";
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node; //en todos los casos retorna un nuevo nodo
    }

    //get balance factor for node
    int getBalanceFactor(BTreeNode<T> node){
        if(node==null){
            return 0;
        }else{
            return height(node.left) - height(node.right); //clase BTree: public int height(BTreeNode<T> node)
        }
    }
    private BTreeNode<T> leftRotate(BTreeNode<T> node) {
        BTreeNode<T> node1 = node.right;
        BTreeNode<T> node2 = node1.left;
        //se realiza la rotacion (perform rotation)
        node1.left = node;
        node.right = node2;
        return node1;
    }
    private BTreeNode<T> rightRotate(BTreeNode<T> node) {
        BTreeNode<T> node1 = node.left;
        BTreeNode<T> node2 = node1.right;
        //se realiza la rotacion (perform rotation)
        node1.right = node;
        node.left = node2;
        return node1;
    }

    @Override
    public void remove(T element) throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Search Tree is empty");
        root = remove(root, element);
        // Recalcular FE en todos los nodos para visualización
        //recalcBalanceFactors(root);
    }

    //método interno
    private BTreeNode<T> remove(BTreeNode<T> node, T element) throws TreeException{
        if(node!=null){
            if(compareElement(element, node.data)<0)
                node.left = remove(node.left, element);
            else if(compareElement(element, node.data)>0)
                node.right = remove(node.right, element);
            else if(equals(element, node.data)){ //ya lo encontro
                //Caso1. El nodo a suprimir no tiene hijos
                if(node.left==null && node.right==null) return null;
                    //Caso2. El nodo a suprimir solo tiene un hijo
                    //en este caso, el nodo es reemplazado por su hijo
                else if(node.left!=null && node.right==null) return node.left;
                else if(node.left==null && node.right!=null) return node.right;
                else{ //if(node.left!=null && node.right!=null){
                    //Caso 3: El nodo tiene 2 hijos
                    //buscamos el valor min del subárbol der
                    //se reemplaza la data del nodo con ese valor
                    //luego se suprime el valor min del subárbol der
                    T minValue =  min(node.right);
                    node.data = minValue;
                    node.right = remove(node.right, minValue);
                }//fin de if(node.left!=null && node.right!=null)
            }//fin de if(node.data==element)
        }//fin de if(node!=null)

        //------------------- Luego de eliminar, se debe rebalancear ---------------//
        int balance = getBalanceFactor(node);
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            node.path += "/→LL-Simple Right Rotate (Rebalancing by elimination)";
            return rightRotate(node);
        }
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.path += "/→LR-Double Left-Right Rotate (Rebalancing by elimination)";
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            node.path += "/→RR-Simple Left Rotate (Rebalancing by elimination)";
            return leftRotate(node);
        }
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.path += "/→RL-Double Right-Left Rotate (Rebalancing by elimination)";
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public String getRebalancingInfo() throws TreeException {
        if(isEmpty()) throw new TreeException("Binary AVL Tree is empty");
        return getRebalancingInfo(root);
    }
    //Recorrido: N-L-R
    private String getRebalancingInfo(BTreeNode<T> node) throws TreeException {
        String result = "";
        if(node!=null){
            result  = "Node Rebalancing Info ["+node.data+"]: "+node.path+"\n";
            result += getRebalancingInfo(node.left);
            result += getRebalancingInfo(node.right);
        }
        return result;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BTreeNode<T> node) {

        if (node == null)
            return true;

        int balance = Math.abs(getBalanceFactor(node));

        if (balance > 1)
            return false;

        return isBalanced(node.left)
                && isBalanced(node.right);
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

}
