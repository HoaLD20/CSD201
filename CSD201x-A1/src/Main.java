/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author TrongDuyDao
 */

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    //contains a list of MyFile
    private static MyFile[] files;//add static from Hoa

    public Main() {
        files = null;
    }

    //get information of all text files under given folder name
    public void loadFiles(String folder) {
        List<MyFile> listFiles = new ArrayList<>();
        loadFiles(folder, listFiles);
        files = listFiles.stream().toArray(MyFile[]::new);
    }

    public void loadFiles(String folder, List<MyFile> listFiles) {
        /*insert the code for listing all text files under given folder here*/

        File getfiles = new File(folder);

        File[] content = getfiles.listFiles();

        for (File file : content) {
            if (file.isFile() == true) {
                if (file.getName().endsWith("docx") || file.getName().endsWith("txt")) {
                    listFiles.add(new MyFile(file.getName(), file.length(), file.getAbsolutePath()));
                }
                /*listFiles.add(new MyFile(file.getName(), file.length(), file.getAbsolutePath()));*/
            } else {
                loadFiles(file.getAbsolutePath(), listFiles);
            }
        }

    }

    //list information of all loaded files
    public void list(MyFile[] files) {
        if (files != null && files.length > 0) {
            //output heading
            System.out.println(String.format("%-20s%-10s", "Name", "Size(in byte)"));
            for (MyFile f : files) {

                System.out.println(f);
            }
        } else {
            System.out.println("List of files is empty...");
        }
    }

    //sort the list of files ascending by size (use selection sort)
    public void selectionSort() {
        /*You should insert code for sorting here, you are going to sort the list of
         loaded files named "files" ascending by file size.*/

        for (int i = 0; i < files.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < files.length; j++) {
                if (files[j].getSize() < files[min].getSize()) {
                    min = j;
                }
            }
            MyFile temp = files[min];
            files[min] = files[i];
            files[i] = temp;

        }

    }

    //sort the list of files ascending by size (use insertion sort)
    public void insertionSort() {
        //do nothing if list of files is empty
        /*You should insert code for sorting here, you are going to sort the list of
         loaded files named "files" ascending by file size.*/
        for (int i = 0; i < files.length; i++) {
            MyFile current = files[i];
            int j = i - 1;
            while (j >= 0 && files[j].getSize() >= current.getSize()) {
                files[j + 1] = files[j];
                j--;
            }
            files[j + 1] = current;
        }

    }

    //sort and output sorted list of text files
    public void sort(SortType st) {
        if (st == SortType.INSERTTIONSORT) {
            insertionSort();
        } else if (st == SortType.SELECTIONSORT) {
            selectionSort();
        }
        //output result after sorting
        list(files);
    }

    //return true if given MyFile contains given keyword, otherwise return false
    public boolean searchFile(MyFile mf, String keyword) throws IOException, FileNotFoundException {

        if (mf.getName().toLowerCase().endsWith(".docx")) {
            try {
                
                FileInputStream fis = new FileInputStream(new File(mf.getFullPath()));
                XWPFDocument document = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphs = document.getParagraphs();
                
                String line;
                for(XWPFParagraph para : paragraphs){
                    if((line = para.getText()) != null){
                        if(line.contains(keyword.toUpperCase()) || line.contains(keyword.toLowerCase())){
                            return true;
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        } 
        if (mf.getName().toLowerCase().endsWith(".txt")) {
            try {
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(mf.getFullPath()));
                String line;
                while ((line = lineNumberReader.readLine()) != null) {
                    if(line.contains(keyword.toUpperCase()) || line.contains(keyword.toLowerCase())){
                            return true;
                        }
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        }

        return false;
    }

    //output information of all files which content has given keyword
    public void searchFile(String keyword) throws IOException {
        //save all files which matched given keyword to the list and output the list
        List<MyFile> listFiles = new ArrayList<>();
        for (MyFile f : files) {
            if (searchFile(f, keyword)) {
                listFiles.add(f);
            }
        }
        MyFile[] foundFiles = listFiles.stream().toArray(MyFile[]::new);
        list(foundFiles);
    }

    /**
     *
     *
     *
     * @return
     */
    public static int menu() {
        System.out.println("Menu");
        System.out.println("1.Load files");
        System.out.println("2.Sort files");
        System.out.println("3.Search files");
        System.out.println("0.Exit");
        System.out.print("Enter your choice: ");

        int choice = checkInput(0, 3);

        return choice;
    }

    /**
     *
     *
     *
     * @param from
     * @param to
     * @return
     */
    public static int checkInput(int from, int to) {
        while (true) {

            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input < from || input > to) {
                    throw new NumberFormatException();
                }

                return input;

            } catch (NumberFormatException e) {
                System.out.println("Please input in range[" + from + ", " + to + "]");

                System.out.print("Please enter again: ");

            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        while (true) {
            int choice = main.menu();
            switch (choice) {
                case 1: {
                    System.out.print("Enter a folder: ");
                    main.loadFiles(scanner.nextLine());
                    main.list(files);
                    break;
                }
                case 2: {
                    System.out.println("Sort the list of file by using");
                    System.out.println("1.Selection sort");
                    System.out.println("2.Insection sort");
                    System.out.print("Your choice: ");
                    int subchoice = checkInput(1, 2);

                    if (subchoice == 1) {
                        main.sort(SortType.SELECTIONSORT);
                    } else if (subchoice == 2) {
                        main.sort(SortType.INSERTTIONSORT);
                    }

                    break;
                }
                case 3: {
                    System.out.print("Enter any keyword to search: ");
                    main.searchFile(scanner.nextLine());
                    break;
                }
                case 0: {
                    return;
                }

            }
        }

    }
}
