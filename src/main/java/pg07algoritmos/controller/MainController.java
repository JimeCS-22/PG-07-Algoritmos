package pg07algoritmos.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pg07algoritmos.model.Node;
import pg07algoritmos.model.Queue.QueueException;
import pg07algoritmos.model.Tree.*;
import pg07algoritmos.model.graph.AdjacencyListGraph;
import pg07algoritmos.model.graph.AdjacencyMatrixGraph;
import pg07algoritmos.model.graph.LinkedGraph;
import pg07algoritmos.model.linkedList.ListException;
import pg07algoritmos.model.stack.StackException;

import java.util.Random;


public class MainController {

    @FXML
    private ComboBox<String> cbGrafos;
    @FXML
    private ComboBox<String> cbTree;
    @FXML
    private Canvas canvasTree1;
    @FXML
    private ComboBox<String> cbTreeMethods;
    @FXML
    private Canvas canvasTree2;
    @FXML
    private Canvas canvasTree3;
    @FXML
    private Button addAristas;
    @FXML
    private TabPane mainTabs;
    @FXML
    private Button generateTree;
    @FXML
    private TextArea txtOutput;

    private BTree<Integer> bTree;
    private BST<Integer> bst;
    private AVL<Integer> avl;

    private GraphicsContext gc;
    private Random random;
    @FXML
    private Button btnReset;
    //---------graph--------

    @FXML
    private Button btnAddEdgesGraph;
    @FXML
    private Button btnGenerateGraph;
    @FXML
    private Button btnRestartGraph;
    @FXML
    private CheckBox checkBoxIsDirected;
    @FXML
    private Canvas canvasGraph;
    @FXML
    private TextArea txtGraph;
    private AdjacencyListGraph<Integer> graphAdjList;
    private AdjacencyMatrixGraph<Integer> graphMatrix;
    private LinkedGraph<Integer> graphLinked;
    @FXML
    private TextField txtElement;
    private final double NODE_RADIUS = 20;
    private GraphicsContext gcGraph;
    @FXML
    private TextField txtEdges;


    @FXML
    public void initialize(){
      setupGraph();
      setupTree();
    }

    ///----------------GRAPH----------------
    private void setupGraph(){
        gcGraph = canvasGraph.getGraphicsContext2D();

        random = new Random();

        graphAdjList = new AdjacencyListGraph<>(15,checkBoxIsDirected.isSelected());
        graphMatrix = new AdjacencyMatrixGraph<>(15,checkBoxIsDirected.isSelected());
        graphLinked = new LinkedGraph<>(checkBoxIsDirected.isSelected());

        cbGrafos.getItems().addAll(
                "Adjacency Matrix",
                "Adjacency List",
                "Linked Graph"
        );
        txtElement.setText("1");
        cbGrafos.getSelectionModel().selectFirst();
        btnAddEdgesGraph.setOnAction(e -> {
            try {
                addEdges();
            } catch (ListException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnGenerateGraph.setOnAction(e-> {
            try {
                generateRandomGraph();
            } catch (QueueException ex) {
                throw new RuntimeException(ex);
            } catch (ListException ex) {
                throw new RuntimeException(ex);
            } catch (StackException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnRestartGraph.setOnAction(actionEvent -> restartGraph());
    }

    private void restartGraph() {
        if (graphMatrix != null) graphMatrix.clear();
        if (graphLinked != null) graphLinked.clear();
        if (graphAdjList != null) graphAdjList.clear();
        canvasGraph.getGraphicsContext2D().clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());
    }

    private void addEdges() throws ListException {
        String tipo = cbGrafos.getValue().toString();
        String text = txtEdges.getText().trim();
        switch (tipo){

            case "Adjacency Matrix":
                // Parsear aristas (u-v, x-y)

                if (!text.isEmpty()) {
                    String[] pairs = text.split(",");
                    for (String pair : pairs) {
                        String[] parts = pair.trim().split("-");
                        if (parts.length == 2) {
                            graphMatrix.addEdge(Integer.parseInt(parts[0]) , Integer.parseInt(parts[1]));
                        }
                    }
                }

                drawGraphAnimated();
                break;

            case "Adjacency List":
                // Parsear aristas (u-v, x-y)

                if (!text.isEmpty()) {
                    String[] pairs = text.split(",");
                    for (String pair : pairs) {
                        String[] parts = pair.trim().split("-");
                        if (parts.length == 2) {
                            graphAdjList.addEdge(Integer.parseInt(parts[0]) , Integer.parseInt(parts[1]));
                        }
                    }
                }
                drawAdjList();
                break;

            case "Linked Graph":
                // Parsear aristas (u-v, x-y)

                if (!text.isEmpty()) {
                    String[] pairs = text.split(",");
                    for (String pair : pairs) {
                        String[] parts = pair.trim().split("-");
                        if (parts.length == 2) {
                            graphLinked.addEdge(Integer.parseInt(parts[0]) , Integer.parseInt(parts[1]));
                        }
                    }
                }
                drawLinked();
                break;
        }
    }

    private void generateRandomGraph() throws QueueException, ListException, StackException {
        graphAdjList = new AdjacencyListGraph<>(15,checkBoxIsDirected.isSelected());
        graphMatrix = new AdjacencyMatrixGraph<>(15,checkBoxIsDirected.isSelected());
        graphLinked = new LinkedGraph<>(checkBoxIsDirected.isSelected());

        String tipo = cbGrafos.getValue().toString();

        switch (tipo){

            case "Adjacency Matrix":
                generateMatrix();
                break;

            case "Adjacency List":
                generateAdjList();
                break;

            case "Linked Graph":
                generateLinked();
                break;
        }

    }
    private void generateMatrix() throws ListException, QueueException, StackException {
        graphMatrix.clear();

        for (int i = 1; i <= 10; i++) {
            graphMatrix.addVertex(i);
        }

        // Agregar aristas con pesos
        graphMatrix.addEdgeAndWeight(1, 5, 3);
        graphMatrix.addEdgeAndWeight(2, 4, 6);
        graphMatrix.addEdgeAndWeight(2, 6, 5);
        graphMatrix.addEdgeAndWeight(3, 4, 2);
        graphMatrix.addEdgeAndWeight(3, 7, 8);
        graphMatrix.addEdgeAndWeight(4, 5, 9);

        drawMatrixGraph();

        showMatrixGraphMethods();
    }

    private void generateAdjList() throws ListException, QueueException, StackException {
        graphAdjList.clear();

        for (int i = 1; i <= 10; i++) {
            graphAdjList.addVertex(i);
        }

        // Agregar aristas con pesos
        graphAdjList.addEdgeAndWeight(1, 5, 3);
        graphAdjList.addEdgeAndWeight(2, 4, 6);
        graphAdjList.addEdgeAndWeight(2, 6, 5);
        graphAdjList.addEdgeAndWeight(3, 4, 2);
        graphAdjList.addEdgeAndWeight(3, 7, 8);
        graphAdjList.addEdgeAndWeight(4, 5, 9);

        drawAdjList();

        showAdjListMethods();
    }

    private void generateLinked() throws ListException, QueueException, StackException {
        graphLinked.clear();

        for (int i = 1; i <= 10; i++) {
            graphLinked.addVertex(i);
        }

        // Agregar aristas con pesos
        graphLinked.addEdgeAndWeight(1, 5, 3);
        graphLinked.addEdgeAndWeight(2, 4, 6);
        graphLinked.addEdgeAndWeight(2, 6, 5);
        graphLinked.addEdgeAndWeight(3, 4, 2);
        graphLinked.addEdgeAndWeight(3, 7, 8);
        graphLinked.addEdgeAndWeight(4, 5, 9);

        drawLinked();

        showgraphLinkedMethods();
    }
    private void drawMatrixGraph() {
        gcGraph.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

        drawGraphAnimated();
    }

    private void drawAdjList() {

        gcGraph.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

        drawGraphAnimatedL();
    }

    private void drawGraphAnimatedL() {
        GraphicsContext gc = canvasGraph.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

        double centerX = canvasGraph.getWidth() / 2;
        double centerY = canvasGraph.getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 50;

        int size = graphAdjList.counter;
        double[][] positions = new double[size][2];
        for (int i = 0; i < size; i++) {
            double angle = 2 * Math.PI * i / size;
            positions[i][0] = centerX + radius * Math.cos(angle);
            positions[i][1] = centerY + radius * Math.sin(angle);
        }

        new AnimationTimer() {
            private long lastUpdate = 0;
            private int edgeIndex = 0;
            private int frameDelay = 15;

            @Override
            public void handle(long now) {
                if (now - lastUpdate < 50_000_000 * frameDelay) return;
                lastUpdate = now;

                gcGraph.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

                for (int i = 0; i < size; i++) {
                    var v = graphAdjList.getVertexByIndex(i);
                    if (v == null) continue;

                    gcGraph.setFill(Color.web("#1e293b"));
                    gcGraph.fillOval(positions[i][0] - NODE_RADIUS, positions[i][1] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                    gcGraph.setStroke(Color.web("#4ade80"));
                    gcGraph.strokeOval(positions[i][0] - NODE_RADIUS, positions[i][1] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                    gcGraph.setFill(Color.WHITE);
                    gcGraph.fillText(String.valueOf(graphAdjList.getVertexByIndex(i).data), positions[i][0] - 8, positions[i][1] + 5);
                }

                gcGraph.setStroke(Color.web("#60a5fa"));
                gcGraph.setLineWidth(2);
                int count = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        try {
                            if (graphAdjList.containsEdge(graphAdjList.getVertexByIndex(i).data, graphAdjList.getVertexByIndex(j).data)) {
                                if (count <= edgeIndex) {
                                    if (graphAdjList.directed) {
                                        drawArrow(gcGraph, positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
                                    } else {
                                        gcGraph.strokeLine(positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
                                    }
                                }
                                count++;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }

                edgeIndex++;
                if (edgeIndex >= count) stop();
            }
        }.start();
    }

    private void drawLinked() throws ListException {

        gcGraph.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

        drawGraphAnimatedLi();
    }

    private void drawGraphAnimatedLi() throws ListException {
        GraphicsContext gc = canvasGraph.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

        double centerX = canvasGraph.getWidth() / 2;
        double centerY = canvasGraph.getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 50;

        int size = graphLinked.size();
        double[][] positions = new double[size][2];
        for (int i = 0; i < size; i++) {
            double angle = 2 * Math.PI * i / size;
            positions[i][0] = centerX + radius * Math.cos(angle);
            positions[i][1] = centerY + radius * Math.sin(angle);
        }

        new AnimationTimer() {
            private long lastUpdate = 0;
            private int edgeIndex = 0;
            private int frameDelay = 15;

            @Override
            public void handle(long now) {
                if (now - lastUpdate < 50_000_000 * frameDelay) return;
                lastUpdate = now;

                gcGraph.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

                for (int i = 0; i < size; i++) {
                    Node<Integer> v = null;
                    try {
                        v = graphLinked.getNodeByIndex(i);
                    } catch (ListException e) {
                        throw new RuntimeException(e);
                    }
                    if (v == null) continue;

                    gcGraph.setFill(Color.web("#1e293b"));
                    gcGraph.fillOval(positions[i][0] - NODE_RADIUS, positions[i][1] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                    gcGraph.setStroke(Color.web("#4ade80"));
                    gcGraph.strokeOval(positions[i][0] - NODE_RADIUS, positions[i][1] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                    gcGraph.setFill(Color.WHITE);
                    try {
                        gcGraph.fillText(String.valueOf(graphLinked.getNodeByIndex(i).data), positions[i][0] - 8, positions[i][1] + 5);
                    } catch (ListException e) {
                        throw new RuntimeException(e);
                    }
                }

                gcGraph.setStroke(Color.web("#60a5fa"));
                gcGraph.setLineWidth(2);
                int count = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        try {
                            if (graphLinked.containsEdge(graphLinked.getNodeByIndex(i).data, graphLinked.getNodeByIndex(j).data)) {
                                if (count <= edgeIndex) {
                                    if (graphLinked.directed) {
                                        drawArrow(gcGraph, positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
                                    } else {
                                        gcGraph.strokeLine(positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
                                    }
                                }
                                count++;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }

                edgeIndex++;
                if (edgeIndex >= count) stop();
            }
        }.start();
    }


    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
    private void showMatrixGraphMethods() throws ListException, StackException, QueueException {

        if (txtElement == null)
            showError("Ingresa un número");
        int element = Integer.parseInt(txtElement.getText());

        StringBuilder sb = new StringBuilder();

        sb.append("=====Adjancency Matrix Graph =====\n\n");

        sb.append(graphMatrix.toString()).append("\n");

        sb.append("1. Recorrido dfs()\n");
        sb.append(graphMatrix.dfs()).append("\n");

        sb.append("2. Recorrido bfs()\n");
        sb.append(graphMatrix.bfs()).append("\n");

        sb.append("3. getVertexDegree("+element +")\n");
        sb.append(graphMatrix.getVertexDegree(element)).append("\n");

        sb.append("4.  getGraphDegree()\n");
        sb.append(graphMatrix.getGraphDegree()).append("\n");

        sb.append("5. totalEdges()\n");
        sb.append(graphMatrix.totalEdges()).append("\n");

        sb.append("6. totalEdges("+element +")\n");
        sb.append(graphMatrix.totalEdges(element)).append("\n");

        sb.append("7. getEdges("+element +")\n");
        sb.append(graphMatrix.getEdges(element)).append("\n");

        txtGraph.setText(sb.toString());

    }
    private void showAdjListMethods() throws ListException, StackException, QueueException {
        if (txtElement == null)
            showError("Ingresa un número");
        int element = Integer.parseInt(txtElement.getText());

        StringBuilder sb = new StringBuilder();

        sb.append("=====Adjancency List Graph =====\n\n");

        sb.append(graphAdjList.toString()).append("\n");

        sb.append("1. Recorrido dfs()\n");
        sb.append(graphAdjList.dfs()).append("\n");

        sb.append("2. Recorrido bfs()\n");
        sb.append(graphAdjList.bfs()).append("\n");

        sb.append("3. getVertexDegree("+element +")\n");
        sb.append(graphAdjList.getVertexDegree(element)).append("\n");

        sb.append("4.  getGraphDegree()\n");
        sb.append(graphAdjList.getGraphDegree()).append("\n");

        sb.append("5. totalEdges()\n");
        sb.append(graphAdjList.totalEdges()).append("\n");

        sb.append("6. totalEdges("+element +")\n");
        sb.append(graphAdjList.totalEdges(element)).append("\n");

        sb.append("7. getEdges("+element +")\n");
        sb.append(graphAdjList.getEdges(element)).append("\n");

        txtGraph.setText(sb.toString());

    }

    private void showgraphLinkedMethods() throws ListException, StackException, QueueException {

        if (txtElement == null)
            showError("Ingresa un número");
        int element = Integer.parseInt(txtElement.getText());

        StringBuilder sb = new StringBuilder();

        sb.append("=====Linked Graph =====\n\n");

        sb.append(graphLinked.toString()).append("\n");

        sb.append("1. Recorrido dfs()\n");
        sb.append(graphLinked.dfs()).append("\n");

        sb.append("2. Recorrido bfs()\n");
        sb.append(graphLinked.bfs()).append("\n");

        sb.append("3. getVertexDegree("+element +")\n");
        sb.append(graphLinked.getVertexDegree(element)).append("\n");

        sb.append("4.  getGraphDegree()\n");
        sb.append(graphLinked.getGraphDegree()).append("\n");

        sb.append("5. totalEdges()\n");
        sb.append(graphLinked.totalEdges()).append("\n");

        sb.append("6. totalEdges("+element +")\n");
        sb.append(graphLinked.totalEdges(element)).append("\n");

        sb.append("7. getEdges("+element +")\n");
        sb.append(graphLinked.getEdges(element)).append("\n");

        txtGraph.setText(sb.toString());

    }

    private void drawGraphAnimated() {
        GraphicsContext gc = canvasGraph.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

        double centerX = canvasGraph.getWidth() / 2;
        double centerY = canvasGraph.getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 50;

        int size = graphMatrix.counter;
        double[][] positions = new double[size][2];
        for (int i = 0; i < size; i++) {
            double angle = 2 * Math.PI * i / size;
            positions[i][0] = centerX + radius * Math.cos(angle);
            positions[i][1] = centerY + radius * Math.sin(angle);
        }

        new AnimationTimer() {
            private long lastUpdate = 0;
            private int edgeIndex = 0;
            private int frameDelay = 15;

            @Override
            public void handle(long now) {
                if (now - lastUpdate < 50_000_000 * frameDelay) return;
                lastUpdate = now;

                gcGraph.clearRect(0, 0, canvasGraph.getWidth(), canvasGraph.getHeight());

                for (int i = 0; i < size; i++) {
                    var v = graphMatrix.getVertexByIndex(i);
                    if (v == null) continue;

                    gcGraph.setFill(Color.web("#1e293b"));
                    gcGraph.fillOval(positions[i][0] - NODE_RADIUS, positions[i][1] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                    gcGraph.setStroke(Color.web("#4ade80"));
                    gcGraph.strokeOval(positions[i][0] - NODE_RADIUS, positions[i][1] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                    gcGraph.setFill(Color.WHITE);
                    gcGraph.fillText(String.valueOf(graphMatrix.getVertexByIndex(i).data), positions[i][0] - 8, positions[i][1] + 5);
                }

                gcGraph.setStroke(Color.web("#60a5fa"));
                gcGraph.setLineWidth(2);
                int count = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        try {
                            if (graphMatrix.containsEdge(graphMatrix.getVertexByIndex(i).data, graphMatrix.getVertexByIndex(j).data)) {
                                if (count <= edgeIndex) {
                                    if (graphMatrix.directed) {
                                        drawArrow(gcGraph, positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
                                    } else {
                                        gcGraph.strokeLine(positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
                                    }
                                }
                                count++;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }

                edgeIndex++;
                if (edgeIndex >= count) stop();
            }
        }.start();
    }
    //metodo aux para los grafos dirigidos
    private void drawArrow(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowLength = 10;
        double arrowAngle = Math.PI / 8;
        double xArrow1 = x2 - arrowLength * Math.cos(angle - arrowAngle);
        double yArrow1 = y2 - arrowLength * Math.sin(angle - arrowAngle);
        double xArrow2 = x2 - arrowLength * Math.cos(angle + arrowAngle);
        double yArrow2 = y2 - arrowLength * Math.sin(angle + arrowAngle);
        gc.strokeLine(x2, y2, xArrow1, yArrow1);
        gc.strokeLine(x2, y2, xArrow2, yArrow2);
    }
    ///------------- TREE ---------------
    private void setupTree(){
        gc = canvasTree1.getGraphicsContext2D();

        random = new Random();

        bTree = new BTree<>();
        bst = new BST<>();
        avl = new AVL<>();

        cbTree.getItems().addAll(
                "Árbol Binario",
                "BST",
                "AVL"
        );

        cbTree.getSelectionModel().selectFirst();

        generateTree.setOnAction(e->generateRandomTree());

        cbTreeMethods.getItems().addAll(
                "Ninguno",
                "Método 11",
                "Método 12",
                "Método 13",
                "Método 14"
        );

        cbTreeMethods.getSelectionModel().selectFirst();
    }

    private void generateRandomTree(){

        String method = cbTreeMethods.getValue();

        switch(method){

            case "Ninguno":
                generateNormalTree();
                break;

            case "Método 11":
                drawMethod11();
                break;

            case "Método 12":
                drawMethod12();
                break;

            case "Método 13":
                drawMethod13();
                break;

            case "Método 14":
                drawMethod14();
                break;
        }
    }

    private void generateNormalTree(){

        String tipo = cbTree.getValue();

        switch (tipo){

            case "Árbol Binario":
                generateBinaryTree();
                break;

            case "BST":
                generateBST();
                break;

            case "AVL":
                generateAVL();
                break;
        }

        clearCanvas(canvasTree2);
        clearCanvas(canvasTree3);
    }

    private void generateBinaryTree(){

        bTree.clear();

        for(int i=0;i<15;i++){

            bTree.addBFS(random.nextInt(100)+1);

        }

        drawBinaryTree();

        showBinaryMethods(bTree);

    }

    private void generateBST(){

        bst.clear();

        for(int i=0;i<15;i++){

            bst.add(random.nextInt(100)+1);

        }

        drawBST();

        showBSTMethods();

    }

    private void generateAVL(){

        avl.clear();

        for(int i=0;i<15;i++){

            avl.add(random.nextInt(100)+1);

        }

        drawAVL();

        showAVLMethods();

    }

    private void drawBinaryTree() {

        gc.clearRect(0, 0, canvasTree1.getWidth(), canvasTree1.getHeight());

        drawBackground();

        TreePainter.drawSimpleNode(
                gc,
                bTree.root,
                canvasTree1.getWidth() / 2,
                60,
                160,
                true
        );
    }

    private void drawBST() {

        gc.clearRect(0, 0, canvasTree1.getWidth(), canvasTree1.getHeight());

        drawBackground();

        TreePainter.drawSimpleNode(
                gc,
                bst.root,
                canvasTree1.getWidth() / 2,
                60,
                160,
                true
        );
    }

    private void drawAVL() {

        gc.clearRect(0, 0, canvasTree1.getWidth(), canvasTree1.getHeight());

        drawBackground();

        TreePainter.drawTreeNode(
                gc,
                avl.root,
                canvasTree1.getWidth() / 2,
                60,
                160,
                avl,
                true
        );
    }

    private void drawBackground() {

        gc.setFill(Color.web("#071A2F"));

        gc.fillRect(
                0,
                0,
                canvasTree1.getWidth(),
                canvasTree1.getHeight()
        );

        gc.setStroke(Color.web("#1B3554"));

        for (int x = 0; x < canvasTree1.getWidth(); x += 40) {

            gc.strokeLine(
                    x,
                    0,
                    x,
                    canvasTree1.getHeight()
            );
        }

        for (int y = 0; y < canvasTree1.getHeight(); y += 40) {

            gc.strokeLine(
                    0,
                    y,
                    canvasTree1.getWidth(),
                    y
            );
        }
    }

    private void showBinaryMethods(BTree<Integer> tree) {

        StringBuilder sb = new StringBuilder();

        sb.append("===== Binary Tree =====\n\n");

        sb.append(tree.toString()).append("\n");

        sb.append("1. printNodesWithChildren()\n");
        sb.append(tree.printNodesWithChildren()).append("\n");

        sb.append("2. printNodes1Child()\n");
        sb.append(tree.printNodes1Child()).append("\n");

        sb.append("3. printNodes2Child()\n");
        sb.append(tree.printNodes2Child()).append("\n");

        sb.append("4. printLeaves()\n");
        sb.append(tree.printLeaves()).append("\n");

        sb.append("5. grandFather()\n");

        printGrandFather(tree.root, tree, sb);

        sb.append("\n");

        sb.append("6. father()\n");

        printFather(tree.root, tree, sb);

        sb.append("\n");

        sb.append("7. brother()\n");

        printBrother(tree.root, tree, sb);

        sb.append("\n");

        sb.append("8. cousins()\n");

        printCousins(tree.root, tree, sb);

        sb.append("\n");

        sb.append("9. printSubtree()\n");

        printSubtrees(tree.root, tree, sb);

        sb.append("\n");

        sb.append("10. totalLeaves()\n");
        sb.append(tree.totalLeaves());

        txtOutput.setText(sb.toString());

    }
    private void showBSTMethods() {

        StringBuilder sb = new StringBuilder();

        sb.append("== BST ==\n\n");

        sb.append(bst.toString()).append("\n");

        sb.append("1. printNodesWithChildren()\n");
        sb.append(bst.printNodesWithChildren()).append("\n");

        sb.append("2. printNodes1Child()\n");
        sb.append(bst.printNodes1Child()).append("\n");

        sb.append("3. printNodes2Child()\n");
        sb.append(bst.printNodes2Child()).append("\n");

        sb.append("4. printLeaves()\n");
        sb.append(bst.printLeaves()).append("\n");

        sb.append("5. grandFather()\n");

        printGrandFather(bst.root, bst, sb);

        sb.append("\n");

        sb.append("6. father()\n");

        printFather(bst.root, bst, sb);

        sb.append("\n");

        sb.append("7. brother()\n");

        printBrother(bst.root, bst, sb);

        sb.append("\n");

        sb.append("8. cousins()\n");

        printCousins(bst.root, bst, sb);

        sb.append("\n");

        sb.append("9. printSubtree()\n");

        printSubtrees(bst.root, bst, sb);

        sb.append("\n");

        sb.append("10. totalLeaves()\n");
        sb.append(bst.totalLeaves());

        txtOutput.setText(sb.toString());

    }

    private void showAVLMethods() {

        StringBuilder sb = new StringBuilder();

        sb.append("== AVL ==\n\n");

        sb.append(avl.toString()).append("\n");

        sb.append("1. printNodesWithChildren()\n");
        sb.append(avl.printNodesWithChildren()).append("\n");

        sb.append("2. printNodes1Child()\n");
        sb.append(avl.printNodes1Child()).append("\n");

        sb.append("3. printNodes2Child()\n");
        sb.append(avl.printNodes2Child()).append("\n");

        sb.append("4. printLeaves()\n");
        sb.append(avl.printLeaves()).append("\n");

        sb.append("5. grandFather()\n");

        printGrandFather(avl.root, avl, sb);

        sb.append("\n");

        sb.append("6. father()\n");

        printFather(avl.root, avl, sb);

        sb.append("\n");

        sb.append("7. brother()\n");

        printBrother(avl.root, avl, sb);

        sb.append("\n");

        sb.append("8. cousins()\n");

        printCousins(avl.root, avl, sb);

        sb.append("\n");

        sb.append("9. printSubtree()\n");

        printSubtrees(avl.root, avl, sb);

        sb.append("\n");

        sb.append("10. totalLeaves()\n");
        sb.append(avl.totalLeaves());

        txtOutput.setText(sb.toString());

    }

    private void printGrandFather(BTreeNode<Integer> node,
                                  BTree<Integer> tree,
                                  StringBuilder sb){

        if(node == null)
            return;

        sb.append("grandFather(")
                .append(node.data)
                .append("): ")
                .append(tree.grandFather(node.data))
                .append("\n");

        printGrandFather(node.left, tree, sb);
        printGrandFather(node.right, tree, sb);

    }

    private void printFather(BTreeNode<Integer> node,
                             BTree<Integer> tree,
                             StringBuilder sb){

        if(node == null)
            return;

        sb.append("father(")
                .append(node.data)
                .append("): ")
                .append(tree.father(node.data))
                .append("\n");

        printFather(node.left, tree, sb);
        printFather(node.right, tree, sb);

    }

    private void printBrother(BTreeNode<Integer> node,
                              BTree<Integer> tree,
                              StringBuilder sb){

        if(node == null)
            return;

        sb.append("brother(")
                .append(node.data)
                .append("): ")
                .append(tree.brother(node.data))
                .append("\n");

        printBrother(node.left, tree, sb);
        printBrother(node.right, tree, sb);

    }

    private void printCousins(BTreeNode<Integer> node,
                              BTree<Integer> tree,
                              StringBuilder sb){

        if(node == null)
            return;

        sb.append("cousins(")
                .append(node.data)
                .append("): ")
                .append(tree.cousins(node.data))
                .append("\n");

        printCousins(node.left, tree, sb);
        printCousins(node.right, tree, sb);

    }

    private void printSubtrees(BTreeNode<Integer> node,
                               BTree<Integer> tree,
                               StringBuilder sb){

        if(node == null)
            return;

        sb.append("Subtree(")
                .append(node.data)
                .append("): ")
                .append(tree.printSubtree(node.data))
                .append("\n");

        printSubtrees(node.left, tree, sb);
        printSubtrees(node.right, tree, sb);

    }

    private void clearCanvas(Canvas canvas){

        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        gc.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );
    }


    private void drawMethod11(){

        BTree<Integer> tree1 = new BTree<>();
        BTree<Integer> tree2 = new BTree<>();

        int size = random.nextInt(6) + 10; // 10-15 nodos

        for(int i = 0; i < size; i++){
            tree1.addBFS(random.nextInt(100) + 1);
            tree2.addBFS(random.nextInt(100) + 1);
        }

        BTree<Integer> result =
                tree1.bTreeSum(tree1, tree2);

        drawTreeOnCanvas(tree1, canvasTree1);
        drawTreeOnCanvas(tree2, canvasTree2);
        drawTreeOnCanvas(result, canvasTree3);

        txtOutput.setText(
                "Método 11 - bTreeSum\n\n" +
                        "Tree1:\n" + tree1 +
                        "\n\nTree2:\n" + tree2 +
                        "\n\nResultado:\n" + result
        );
    }

    private void drawMethod12(){

        BTree<Integer> tree = new BTree<>();

        int size = random.nextInt(6) + 10;

        for(int i = 0; i < size; i++){
            tree.addBFS(random.nextInt(100) + 1);
        }

        BTree<Integer> result =
                tree.btNodeSum(tree);

        drawTreeOnCanvas(tree, canvasTree1);
        drawTreeOnCanvas(result, canvasTree2);

        canvasTree3.getGraphicsContext2D()
                .clearRect(
                        0,
                        0,
                        canvasTree3.getWidth(),
                        canvasTree3.getHeight()
                );

        txtOutput.setText(
                "Método 12 - btNodeSum\n\n" +
                        "Original:\n" + tree +
                        "\n\nResultado:\n" + result
        );
    }

    private void drawMethod13(){

        BTree<Integer> tree = new BTree<>();

        int totalNodes = random.nextInt(8) + 8;

        for(int i=0;i<totalNodes;i++){

            if(tree.root == null){

                tree.root = new BTreeNode<>(
                        random.nextInt(100)+1
                );

            }else{

                insertRandom(tree.root,
                        random.nextInt(100)+1);
            }
        }

        drawTreeOnCanvas(tree, canvasTree1);

        tree.tighten();

        drawTreeOnCanvas(tree, canvasTree2);

        canvasTree3.getGraphicsContext2D()
                .clearRect(
                        0,
                        0,
                        canvasTree3.getWidth(),
                        canvasTree3.getHeight()
                );

        txtOutput.setText(
                "Método 13 - Tighten ejecutado"
        );
    }

    private void insertRandom(
            BTreeNode<Integer> node,
            Integer value){

        if(random.nextBoolean()){

            if(node.left == null){

                node.left = new BTreeNode<>(value);

            }else{

                insertRandom(node.left,value);
            }

        }else{

            if(node.right == null){

                node.right = new BTreeNode<>(value);

            }else{

                insertRandom(node.right,value);
            }
        }
    }

    private BTree<Integer> generateRandomABM(int levels){

        BTree<Integer> tree = new BTree<>();

        tree.root = generateABMNode(
                levels,
                random.nextInt(20)+1
        );

        return tree;
    }

    private BTreeNode<Integer> generateABMNode(
            int level,
            int parentValue){

        if(level == 0)
            return null;

        if(level != 4 && random.nextDouble() < 0.3)
            return null;

        int value = parentValue;

        BTreeNode<Integer> node =
                new BTreeNode<>(value);

        if(random.nextBoolean()){

            node.left = generateABMNode(
                    level - 1,
                    value + random.nextInt(20)
            );
        }

        if(random.nextBoolean()){

            node.right = generateABMNode(
                    level - 1,
                    value + random.nextInt(20)
            );
        }

        return node;
    }

    private void drawMethod14(){

        BTree<Integer> tree1 =
                generateRandomABM(4);

        BTree<Integer> tree2 =
                generateRandomABM(4);

        BTree<Integer> result =
                tree1.joinABM(tree1, tree2);

        drawTreeOnCanvas(tree1, canvasTree1);
        drawTreeOnCanvas(tree2, canvasTree2);

        if(result != null)
            drawTreeOnCanvas(result, canvasTree3);
        else
            clearCanvas(canvasTree3);

        txtOutput.setText(
                "isABM Tree1: "
                        + tree1.isABM(tree1)
                        + "\n"
                        + "isABM Tree2: "
                        + tree2.isABM(tree2)
                        + "\n\n"
                        + "JoinABM:\n"
                        + result
        );
    }

    private void drawBackground(GraphicsContext gc, Canvas canvas){

        gc.setFill(Color.web("#071A2F"));

        gc.fillRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        gc.setStroke(Color.web("#1B3554"));

        for(int x=0;x<canvas.getWidth();x+=40){
            gc.strokeLine(x,0,x,canvas.getHeight());
        }

        for(int y=0;y<canvas.getHeight();y+=40){
            gc.strokeLine(0,y,canvas.getWidth(),y);
        }
    }

    private void drawTreeOnCanvas(
            BTree<Integer> tree,
            Canvas canvas){

        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        gc.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        drawBackground(gc, canvas);

        TreePainter.drawSimpleNode(
                gc,
                tree.root,
                canvas.getWidth()/2,
                60,
                160,
                true
        );
    }

}