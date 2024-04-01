package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotepadModel {
    public void saveToFile(String content, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public List<String> listFilesInDirectoryRecursive(String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        listFilesInDirectoryRecursiveHelper(new File(directoryPath), fileNames);
        return fileNames;
    }

    private void listFilesInDirectoryRecursiveHelper(File directory, List<String> fileNames) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesInDirectoryRecursiveHelper(file, fileNames);
                } else {
                    fileNames.add(file.getAbsolutePath());
                }
            }
        }
    }
}
