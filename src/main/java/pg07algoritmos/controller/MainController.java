package pg07algoritmos.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pg07algoritmos.model.Tree.*;

import java.util.List;
import java.util.Random;


public class MainController {

    @FXML
    private ComboBox cbGrafos;
    @FXML
    private ComboBox cbTree;
    @FXML
    private Canvas canvasTree;
    @FXML
    private Button btnPlayBTree;
    @FXML
    private Canvas canvasBTree;
    @FXML
    private Button addAristas;
    @FXML
    private TabPane mainTabs;
    @FXML
    private Button generateTree;
    @FXML
    private Button btnPlayBst;
    @FXML
    private TextArea txtOutput;

    private BTree<Integer> bTree;
    private BST<Integer> bst;
    private AVL<Integer> avl;

    private GraphicsContext gc;
    private Random random;
    @FXML
    private Button btnReset;

    @FXML
    public void initialize(){

        gc = canvasTree.getGraphicsContext2D();

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



    }

    private void generateRandomTree(){

        String tipo = cbTree.getValue().toString();

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

        gc.clearRect(0, 0, canvasTree.getWidth(), canvasTree.getHeight());

        drawBackground();

        TreePainter.drawSimpleNode(
                gc,
                bTree.root,
                canvasTree.getWidth() / 2,
                60,
                160,
                true
        );
    }

    private void drawBST() {

        gc.clearRect(0, 0, canvasTree.getWidth(), canvasTree.getHeight());

        drawBackground();

        TreePainter.drawSimpleNode(
                gc,
                bst.root,
                canvasTree.getWidth() / 2,
                60,
                160,
                true
        );
    }

    private void drawAVL() {

        gc.clearRect(0, 0, canvasTree.getWidth(), canvasTree.getHeight());

        drawBackground();

        TreePainter.drawTreeNode(
                gc,
                avl.root,
                canvasTree.getWidth() / 2,
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
                canvasTree.getWidth(),
                canvasTree.getHeight()
        );

        gc.setStroke(Color.web("#1B3554"));

        for (int x = 0; x < canvasTree.getWidth(); x += 40) {

            gc.strokeLine(
                    x,
                    0,
                    x,
                    canvasTree.getHeight()
            );
        }

        for (int y = 0; y < canvasTree.getHeight(); y += 40) {

            gc.strokeLine(
                    0,
                    y,
                    canvasTree.getWidth(),
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




}