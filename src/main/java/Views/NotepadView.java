package Views;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class NotepadView extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;
    private JButton openButton;
    private JButton browseButton;
    private JTree tree;

    public NotepadView() {
        setTitle("Notepad with Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        textArea = new JTextArea();
        JScrollPane textScrollPane = new JScrollPane(textArea);

        tree = new JTree();
        JScrollPane treeScrollPane = new JScrollPane(tree);

        saveButton = new JButton("Save");
        openButton = new JButton("Open");
        browseButton = new JButton("Browse");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        buttonPanel.add(browseButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, textScrollPane);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getContent() {
        return textArea.getText();
    }

    public void setContent(String content) {
        textArea.setText(content);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addOpenButtonListener(ActionListener listener) {
        openButton.addActionListener(listener);
    }

    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }

    public void setTreeData(DefaultMutableTreeNode rootNode) {
        tree.setModel(new DefaultTreeModel(rootNode));
    }

    public void displayFilesInDirectory(String content) {
        textArea.setText(content);
    }
}
