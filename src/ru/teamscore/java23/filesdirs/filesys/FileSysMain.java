package ru.teamscore.java23.filesdirs.filesys;

import java.io.IOException;
import java.nio.file.*;

public class FileSysMain {
    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();

        System.out.println("System separator: " + fs.getSeparator());

        System.out.println("\nFile stores:");
        System.out.format("%-20s %12s %12s %12s\n", "store", "total", "used", "avail");
        for (FileStore store : fs.getFileStores()) {
            try {
                long total = store.getTotalSpace() / 1024;
                long used = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
                long avail = store.getUsableSpace() / 1024;
                System.out.format("%-20s %12d %12d %12d\n", store, total, used, avail);
            } catch (IOException e) {
                System.err.println("Cannot read store '" + store.name() + "' space info: " + e.getMessage());
            }
        }

        System.out.println("\nRoots:");
        for (Path root : fs.getRootDirectories()) {
            System.out.println(root);
        }
    }
}
