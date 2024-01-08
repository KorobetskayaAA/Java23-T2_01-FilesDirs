package ru.teamscore.java23.filesdirs.fileattr;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Scanner;

public class FileAttributesMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу: ");
        Path file = Paths.get(sc.nextLine());

        System.out.println("Absolute path: " + file.toAbsolutePath());
        try {
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            System.out.println("isDirectory: " + attr.isDirectory());
            System.out.println("isOther: " + attr.isOther());
            System.out.println("isRegularFile: " + attr.isRegularFile());
            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
            System.out.println("size: " + attr.size() + " bytes");
        } catch (IOException x) {
            System.err.println("Cannot read basic file attributes: " + x.getMessage());
        }

        try {
            DosFileAttributes attr = Files.readAttributes(file, DosFileAttributes.class);
            System.out.println("DOS attributes");
            System.out.println("isReadOnly is " + attr.isReadOnly());
            System.out.println("isHidden is " + attr.isHidden());
            System.out.println("isArchive is " + attr.isArchive());
            System.out.println("isSystem is " + attr.isSystem());
        } catch (UnsupportedOperationException x) {
            System.err.println("DOS file attributes not supported:" + x);
        } catch (IOException x) {
            System.err.println("Some I/O error:" + x);
        }

        try {
            PosixFileAttributes attr = Files.readAttributes(file, PosixFileAttributes.class);
            System.out.println("POSIX attributes");
            System.out.format("%s %s %s%n",
                    attr.owner().getName(),
                    attr.group().getName(),
                    PosixFilePermissions.toString(attr.permissions()));
        } catch (UnsupportedOperationException x) {
            System.err.println("POSIX file attributes not supported:" + x);
        } catch (IOException x) {
            System.err.println("Some I/O error:" + x);
        }
    }
}
