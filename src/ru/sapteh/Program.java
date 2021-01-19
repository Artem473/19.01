package ru.sapteh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("Введите путь к файлу");
        String path = reader.readLine();
        Path path1 = Paths.get(path);

        FileOutputStream zipArchive = new FileOutputStream(path + ".zip ");
        ZipOutputStream zip = new ZipOutputStream(zipArchive);


        MyFiles myFiles = new MyFiles();
        Files.walkFileTree(path1,myFiles);

        List<File> files = myFiles.getFiles();

        ZipEntry zipEntry;
        for (File file : files){
            if (file.isDirectory()){
                zipEntry = new ZipEntry(replacePath(file.toString(), path1.getParent().toString()).concat( "/"));
                System.out.println(replacePath(file.toString() , path1.getParent().toString()));
                zip.putNextEntry(zipEntry);
                zip.closeEntry();
            } if (file.isFile()){
                zipEntry = new ZipEntry(replacePath(file.toString(), path1.getParent().toString()));
                System.out.println(replacePath(file.toString() , path1.getParent().toString()));
                zip.putNextEntry(zipEntry);
                Files.copy(file.toPath(),zip);
                zip.closeEntry();
            }
        }
        zip.close();
    }
    public static String replacePath (String path , String replacePath){
        return path.substring(replacePath.length() + 1);
    }
}
