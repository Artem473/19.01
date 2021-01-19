package ru.sapteh;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class MyFiles extends SimpleFileVisitor<Path> {
    private final List<File> files = new ArrayList<>();

    public List<File> getFiles() {
        return files;
    }
    @Override
    public FileVisitResult preVisitDirectory (Path dir , BasicFileAttributes attr){
        files.add(dir.toFile());
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
        files.add(file.toFile());
        return FileVisitResult.CONTINUE;
    }
}
