package it.unibo.mvc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    static private final String SEPARATOR = System.getProperty("file.separator");
    static private final String DEFAULT_FILE = "output.txt";
    static private final String DEFAULT_PATH = System.getProperty("user.home") + SEPARATOR + DEFAULT_FILE;

    private File currentFile;

    public Controller() {
        this.currentFile = new File(DEFAULT_PATH);
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    public void setCurrentFile(final File file) {
        this.currentFile = file;
    }

    public void setCurrentFile(final String fileName) {
        this.currentFile = new File(fileName);
    }

    public String getCurrentFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    public void save(final String message) throws IOException {
        try (final FileWriter fw = new FileWriter(this.currentFile)) {
            fw.write(message);
        }
    }

    public String load() {
        try {
            return Files.readString(this.currentFile.toPath());
        } catch (final NoSuchFileException e1) {
            return "";
        } catch (final IOException e2) {
            return "";
        }
    }

}
