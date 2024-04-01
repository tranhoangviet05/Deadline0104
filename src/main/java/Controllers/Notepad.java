package Controllers;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Models.NotepadModel;
import Views.NotepadView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class Notepad {
    private NotepadModel model;
    private NotepadView view;

    public Notepad(NotepadModel model, NotepadView view) {
        this.model = model;
        this.view = view;

        view.addSaveButtonListener((ActionEvent e) -> saveFile());
        view.addOpenButtonListener((ActionEvent e) -> openFile());
        view.addBrowseButtonListener((ActionEvent e) -> browseDirectory());
    }

    private void saveFile() {
        String content = view.getContent();
        String fileName = JOptionPane.showInputDialog(view, "Enter file name to save:");
        if (fileName != null && !fileName.isEmpty()) {
            model.saveToFile(content, fileName);
            JOptionPane.showMessageDialog(view, "File saved successfully.");
        }
    }

    private void openFile() {
        String fileName = JOptionPane.showInputDialog(view, "Enter file name to open:");
        if (fileName != null && !fileName.isEmpty()) {
            String content = model.loadFromFile(fileName);
            view.setContent(content);
        }
    }

    private void browseDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(directory.getName());
            addFilesToNode(directory, rootNode);
            view.setTreeData(rootNode);
        }
    }

    private void addFilesToNode(File directory, DefaultMutableTreeNode parentNode) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file.getName());
                parentNode.add(fileNode);
                if (file.isDirectory()) {
                    addFilesToNode(file, fileNode);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotepadModel model = new NotepadModel();
            NotepadView view = new NotepadView();
            Notepad c = new Notepad(model, view);
            view.setVisible(true);
        });
    }
}
