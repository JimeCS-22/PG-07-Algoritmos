package pg07algoritmos.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import pg07algoritmos.model.Tree.*;

import static pg07algoritmos.model.Tree.TreePainter.drawSimpleNode;
import static pg07algoritmos.model.Tree.TreePainter.drawTreeNode;


public class MainController {

    @FXML
    private TabPane mainTabs;

    ///-------AVL TREE------------
    @FXML
    private Button btnAddAvl;
    @FXML
    private Button btnPlayAvl;
    @FXML
    private Button btnRemoveAvl;
    @FXML
    private Button btnClearAvl;
    @FXML
    private Button btnSearchAvl;
    @FXML
    private Label lblAvlInfo;
    @FXML
    private Label lblRotacion;
    @FXML
    private Canvas canvasAVL;
    @FXML
    private TextField txtAvlValue;
    @FXML
    private ComboBox<String> cbRecorridos;
    @FXML
    private Label lblTours;
    @FXML
    private Label lblAvlInfo1;
    private AVL<Integer> avl;

    ///-------BST TREE------------

    @FXML
    private Button btnClearBst;
    @FXML
    private Label lblBstInfo2;
    @FXML
    private Label lblTours2;
    @FXML
    private Label lblBstEncontrado;
    @FXML
    private Canvas canvasBst;
    @FXML
    private TextField txtBstValue;
    @FXML
    private ComboBox cbRecorridosBst;
    @FXML
    private Button btnAddBst;
    @FXML
    private Button btnPlayBst;
    @FXML
    private Button btnSearchBst;
    @FXML
    private Label lblBstInfo;
    @FXML
    private Button btnRemoveBst;
    @FXML
    private HBox lblRecorridosBst;
    private BST<Integer> bst;

    /////---------BTREE----------////

    @FXML
    private TextField txtValueBTree;
    @FXML
    private Label lblTours1;
    @FXML
    private Canvas canvasBTree;
    @FXML
    private Label lblBTreeinfo;
    @FXML
    private Button btnClearBTree;
    @FXML
    private ComboBox cbRecorridosBTree;
    @FXML
    private Button btnAddBTree;
    @FXML
    private Button btnPlayBTree;
    @FXML
    private Button btnRemoveBTree;
    @FXML
    private HBox RecorridosBTree;
    @FXML
    private Label lblBTreeDelete;
    private BTree<Integer> bTree;

//  -------------


    @FXML
    public void initialize() {
        setupBinaryTree();
        setupBST();
        setupAVL();
    }

    /// Methods Controller for BST TREE tab - Alexander

    private void setupBST(){

        bst = new BST<>();

        btnAddBst.setOnAction(e -> addBST());

        btnSearchBst.setOnAction(e -> runSearchBST());

        btnRemoveBst.setOnAction(e -> removeBST());

        btnClearBst.setOnAction(e -> clearBST());

        btnPlayBst.setOnAction(e -> {
            try {
                playToursBST();
            } catch (TreeException ex) {
                throw new RuntimeException(ex);
            }
        });

        cbRecorridosBst.setItems(
                FXCollections.observableArrayList(
                        "PreOrder",
                        "InOrder",
                        "PostOrder"
                )
        );

        cbRecorridosBst.getSelectionModel().selectFirst();
    }

    private void addBST() {

        try {

            int value =
                    Integer.parseInt(
                            txtBstValue.getText().trim()
                    );

            if (value < 0) {

                showAlert(
                        "Error",
                        "Debe ingresar un número positivo"
                );

                return;
            }

            bst.add(value);

            lblBstInfo2.setText(
                    "Node " + value + " agregado del árbol"
            );

            updateBSTInfo();

            drawBST();

        } catch (NumberFormatException e) {

            showAlert(
                    "Error",
                    "Ingrese un número válido"
            );

        } catch (Exception e) {

            showAlert(
                    "Error",
                    "Error al insertar nodo"
            );
        }
    }

    private void runSearchBST() {

        try {

            int value =
                    Integer.parseInt(
                            txtBstValue.getText().trim()
                    );

            if (value < 0 ||
                    txtBstValue.getText().isBlank()) {

                showAlert(
                        "Error",
                        "Debe ingresar un número positivo"
                );

                return;
            }

            boolean found = bst.contains(value);

            if (found) {

                lblBstEncontrado.setText(
                        "Encontrado: " + value
                );

                lblBstInfo2.setText(
                        "Node " + value + " encontrado en el árbol"
                );

                showAlert(
                        "Resultado de búsqueda",
                        "El valor " + value + " fue encontrado en el árbol"
                );

            } else {

                lblBstEncontrado.setText(
                        "Encontrado: --"
                );

                lblBstInfo2.setText(
                        "Node " + value + " no encontrado en el árbol"
                );

                showAlert(
                        "Resultado de búsqueda",
                        "El valor " + value + " no se encuentra en el árbol"
                );
            }

            updateBSTInfo();

            drawBST();

        } catch (NumberFormatException e) {

            showAlert(
                    "Error",
                    "Ingrese un número válido"
            );

        } catch (Exception e) {

            showAlert(
                    "Error",
                    "Error al buscar el valor"
            );
        }
    }

    private void removeBST() {

        try {

            int value =
                    Integer.parseInt(
                            txtBstValue.getText().trim()
                    );

            if (value < 0) {

                showAlert(
                        "Error",
                        "Debe ingresar número positivo"
                );

                return;
            }

            bst.remove(value);

            lblBstInfo2.setText(
                    "Node " + value + " eliminado del árbol"
            );

            updateBSTInfo();

            drawBST();

        } catch (NumberFormatException e) {

            showAlert(
                    "Error",
                    "Valor inválido"
            );

        } catch (Exception e) {

            showAlert(
                    "Error",
                    "Error al eliminar nodo"
            );
        }
    }

    private void clearBST() {

        bst.clear();

        txtBstValue.clear();

        lblTours2.setText("--");

        lblBstInfo.setText("--");

        lblBstEncontrado.setText("--");

        lblBstInfo2.setText("--");

        GraphicsContext gc =
                canvasBst.getGraphicsContext2D();

        gc.clearRect(
                0,
                0,
                canvasBst.getWidth(),
                canvasBst.getHeight()
        );
    }

    private void updateBSTInfo() {

        try {

            lblBstInfo.setText(
                    "Nodos: "
                            + bst.size()
                            + " | Altura: "
                            + bst.height()
                            + " | BST Válido: true"
            );

        } catch (Exception e) {

            lblBstInfo.setText(
                    "Nodos: 0 | Altura: 0 | BST Válido: false"
            );
        }
    }

    private void playToursBST() throws TreeException {

        String recorrido =
                (String) cbRecorridosBst
                        .getSelectionModel()
                        .getSelectedItem();

        switch (recorrido) {

            case "PreOrder":

                lblTours2.setText(
                        "PreOrder: ["
                                + bst.preOrder()
                                + "]"
                );
                break;

            case "InOrder":

                lblTours2.setText(
                        "InOrder: ["
                                + bst.inOrder()
                                + "]"
                );
                break;

            case "PostOrder":

                lblTours2.setText(
                        "PostOrder: ["
                                + bst.postOrder()
                                + "]"
                );
                break;
        }
    }

    private void drawBST() {

        GraphicsContext gc =
                canvasBst.getGraphicsContext2D();

        gc.clearRect(
                0,
                0,
                canvasBst.getWidth(),
                canvasBst.getHeight()
        );

        if (bst.root != null) {

            drawSimpleNode(
                    gc,
                    bst.root,
                    canvasBst.getWidth() / 2,
                    60,
                    canvasBst.getWidth() / 4,
                    true
            );
        }
    }



        /// Methods Controller for AVL TREE tab - Camila
    private void setupAVL() {
        avl = new AVL<>();

        //config botones de operaciones
        btnAddAvl.setOnAction(e -> {
            try {
                addAVL();
            } catch (TreeException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnSearchAvl.setOnAction(e -> runSearchAVL());
        btnRemoveAvl.setOnAction(e -> removeAVL());
        btnClearAvl.setOnAction(e -> clearAVL());
        btnPlayAvl.setOnAction(e -> {
            try {
                playTours();
            } catch (TreeException ex) {
                throw new RuntimeException(ex);
            }
        });

        cbRecorridos.setItems(FXCollections.observableArrayList("PreOrder",
                "InOrder", "PostOrder"
        ));
        cbRecorridos.getSelectionModel().selectFirst();

    }

    private void addAVL() throws TreeException {
        int input = Integer.parseInt(txtAvlValue.getText().trim());

        if (input < 0 || txtAvlValue.getText() == null) {
            showAlert("Error", "Debe ingresar un número positivo");
            return;
        }

        avl.add(input);
        String result = avl.toString();

        // colocar el registro de operaciones
        try {
            registrarOperacion(avl.size(),avl.height(input),avl.isBalanced());
            lblRotacion.setText(avl.getRebalancingInfo());
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

        lblAvlInfo1.setText("Node "+input+" agregado del árbol");
        // agregar nodo al arbol
        drawAVL(avl);//dibujar la acción de add en el Canvas

    }

    private void runSearchAVL() {
        int input = Integer.parseInt(txtAvlValue.getText().trim());

        // Validación
        if (input < 0 || txtAvlValue.getText().isBlank()) {
            showAlert("Error", "Debe ingresar un número positivo");
            return;
        }

        try {
            // Buscar en la lista enlazada
            boolean posicion = avl.contains(input);

            if (posicion) {
                // Valor encontrado
                lblAvlInfo1.setText("Node "+input+" encontrado en el árbol");
                showAlert("Resultado de búsqueda",
                        "El valor \"" + input + "\" fue encontrado en el árbol");
            } else {
                // Valor NO encontrado
                lblAvlInfo1.setText("Node "+input+" no encontrado en el árbol");
                showAlert("Resultado de búsqueda","El valor \"" + input + "\" no se encuentra en el árbol");

            }
            // registrar operación
            registrarOperacion(avl.size(),avl.height(input),avl.isBalanced());
            drawAVL(avl);
        } catch (Exception e) {
            showAlert("Error", "Error al buscar el valor");
        }
    }
    private void removeAVL() {
        try {
            int input = Integer.parseInt(txtAvlValue.getText().trim());

            if (input < 0) {
                showAlert("Error", "Debe ingresar número positivo");
                return;
            }

            //  eliminar de la estructura
            avl.remove(input);

            // actualizar representación textual
            //listViewRecorridos. setText(avl.toString());

            // registrar operación
            registrarOperacion(avl.size(),avl.height(input),avl.isBalanced());
            lblAvlInfo1.setText("Node "+input+" eliminado del árbol");
            drawAVL(avl);

        } catch (NumberFormatException e) {
            showAlert("Error", "Valor inválido");
        } catch (Exception e) {
            showAlert("Error", "Error al eliminar nodo");
            e.printStackTrace();
        }
    }
    private void clearAVL() {
        avl.root = null;//vaciar árbol
        txtAvlValue.clear();
        lblTours.setText("--");
        lblAvlInfo.setText("--");
        lblRotacion.setText("--");
        lblAvlInfo1.setText("--");
        clearCanvasList();
    }

    private void clearCanvasList() {
        GraphicsContext gc = canvasAVL.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasAVL.getWidth(), canvasAVL.getHeight());
    }

    private void playTours() throws TreeException {
       String tour = cbRecorridos.getSelectionModel().getSelectedItem();
        switch (tour) {
            case "PreOrder":
                lblTours.setText("PreOrder: ["+avl.preOrder() +"]");
                break;
            case "InOrder":
                lblTours.setText("InOrder: ["+avl.inOrder()+"]");
                break;
            case "PostOrder":
                lblTours.setText("PostOrder: ["+avl.postOrder()+"]");
                break;
        }
    }

    private void drawAVL(AVL<Integer> tree) throws TreeException {
        // graphicContext:forma de "dibujar" es como un objeto
        GraphicsContext treeGraphic = canvasAVL.getGraphicsContext2D();
        // limpiar cada vez antes de entrar al if
        treeGraphic.clearRect(0, 0, canvasAVL.getWidth(), canvasAVL.getHeight());

        // avl tree
        if (avl.root != null) {// si hay raiz entonces permite llamar metodo drawBTreeNodes
            // getWidth()/2 para que se centrara el arbol
            if (tree.root.equals(avl.root)) {
                drawTreeNode(treeGraphic, avl.root, canvasAVL.getWidth() / 2, 40, canvasAVL.getWidth() / 4,tree, true);
            }else{
                drawTreeNode(treeGraphic, avl.root, canvasAVL.getWidth() / 2, 40, canvasAVL.getWidth() / 4,tree, false);
            }

        }

    }

    private void registrarOperacion(int nodos, int altura, boolean isBalanced) {
        String texto = "Nodos: " +nodos + "| Altura: " + altura + "| Balanceado:  " + isBalanced;
        lblAvlInfo.setText(texto);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /// Methods Controller for BTREE tab - Jimena
    private void setupBinaryTree() {

        bTree = new BTree<>();

        btnAddBTree.setOnAction(e -> addBTree());

        btnRemoveBTree.setOnAction(e -> removeBTree());

        btnClearBTree.setOnAction(e -> clearBTree());

        btnPlayBTree.setOnAction(e -> {
            try {
                playToursBTree();
            } catch (TreeException ex) {
                throw new RuntimeException(ex);
            }
        });

        cbRecorridosBTree.setItems(
                FXCollections.observableArrayList(
                        "PreOrder",
                        "InOrder",
                        "PostOrder"
                )
        );

        cbRecorridosBTree.getSelectionModel().selectFirst();
    }

    private void addBTree() {

        try {

            int value = Integer.parseInt(
                    txtValueBTree.getText().trim()
            );

            bTree.addBFS(value);

            lblBTreeDelete.setText(
                    "Insertado: " + value
            );

            updateBTreeInfo();

            drawBTree();

        } catch (Exception e) {

            showAlert(
                    "Error",
                    "Ingrese un número válido"
            );
        }
    }

    private void removeBTree() {

        try {

            int value = Integer.parseInt(
                    txtValueBTree.getText().trim()
            );

            bTree.remove(value);

            lblBTreeDelete.setText(
                    "Eliminado: " + value
            );

            updateBTreeInfo();

            drawBTree();

            canvasBTree.getScene().getWindow().requestFocus();
        } catch (Exception e) {

            showAlert(
                    "Error",
                    "No se pudo eliminar"
            );
        }
    }

    private void clearBTree() {

        bTree.clear();

        txtValueBTree.clear();

        lblTours1.setText("");

        lblBTreeinfo.setText("");

        lblBTreeDelete.setText("");

        GraphicsContext gc =
                canvasBTree.getGraphicsContext2D();

        gc.clearRect(
                0,
                0,
                canvasBTree.getWidth(),
                canvasBTree.getHeight()
        );
    }

    private void updateBTreeInfo() {

        try {

            lblBTreeinfo.setText(
                    "Nodos: "
                            + bTree.size()
                            + " | Altura: "
                            + bTree.height()
            );

        } catch(Exception e){

            lblBTreeinfo.setText(
                    "Nodos: 0 | Altura: 0"
            );
        }
    }

    private void playToursBTree() throws TreeException {

        String recorrido =
                (String) cbRecorridosBTree
                        .getSelectionModel()
                        .getSelectedItem();

        switch (recorrido) {

            case "PreOrder":

                lblTours1.setText(
                        "PreOrder: ["
                                + bTree.preOrder()
                                + "]"
                );
                break;

            case "InOrder":

                lblTours1.setText(
                        "InOrder: ["
                                + bTree.inOrder()
                                + "]"
                );
                break;

            case "PostOrder":

                lblTours1.setText(
                        "PostOrder: ["
                                + bTree.postOrder()
                                + "]"
                );
                break;
        }
    }

    private void drawBTree() {

        GraphicsContext gc =
                canvasBTree.getGraphicsContext2D();

        gc.clearRect(
                0,
                0,
                canvasBTree.getWidth(),
                canvasBTree.getHeight()
        );

        if(bTree.root != null){

            TreePainter.drawSimpleNode(
                    gc,
                    bTree.root,
                    canvasBTree.getWidth()/2,
                    60,
                    canvasBTree.getWidth()/4,
                    true
            );
        }
    }



}