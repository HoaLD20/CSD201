/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
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
    private static MyFile[] files;/*add static from Hoa - key static  - needn't create object Main*/

    /*default element of MyFile[] = null when restart or renew program*/

    public Main() {
        files = null;
    }

    //get information of all text files under given folder name
    public void loadFiles(String folder) {
        /*MyFile - contain name, size, fullPath*/
        List<MyFile> listFiles = new ArrayList<>();//contain content of folder
        loadFiles(folder, listFiles);
        //khi mo files -> tao thanh 1 luong - stream -> chuyen thanh mang de truy cap vao nhung phan tu trong mang
        files = listFiles.stream().toArray(MyFile[]::new);/*files is array - so change MyFile (arraylist) to array */

    }

    /**
     * @param folder : name or path to this folder
     * @param listFiles : contain the thing exist in folder
     */
    public void loadFiles(String folder, List<MyFile> listFiles) {
        /*insert the code for listing all text files under given folder here*/
        //create new object File - contain name or path to foler
        File getfiles = new File(folder);//open this folder
        File[] content = getfiles.listFiles();//save everything in folder in content[]

        //check content[] element
        for (File file : content) {
            if (file.isFile() == true) {
                if (file.getName().endsWith("docx") || file.getName().endsWith("txt")) {
                    listFiles.add(new MyFile(file.getName(), file.length(), file.getAbsolutePath()));//add name, size, path
                }
            } else {
                loadFiles(file.getAbsolutePath(), listFiles);//if file is not -> it's irectory -> recall loadFiles(x, y) method -> load 
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

    public void quickSort(MyFile[] files, int left, int right) {
        
       if(left >= right){
           return;
       }
       
       int pivot = (int) files[(left + right) / 2].getSize();
       int i = left;
       int j = right;
       while(i < j){
           while(files[i].getSize() < pivot){
               i++;
           }
           while(files[j].getSize() > pivot){
               j--;
           }
           if(i <= j){
               MyFile temp = files[i];
               files[i] = files[j];
               files[j] = temp;
               i++;
               j--;
           }
       }
        quickSort(files, left, j);
        quickSort(files, i, right);
    }

   

    //sort and output sorted list of text files
    public void sort(SortType st) {
        if (st == SortType.INSERTTIONSORT) {
            insertionSort();
        } else if (st == SortType.SELECTIONSORT) {
            selectionSort();
        } else if (st == SortType.QUICKSORT) {
            quickSort(files, 0, files.length - 1) ;
        }
        //output result after sorting
        list(files);
    }

    //return true if given MyFile contains given keyword, otherwise return false
    public boolean searchFile(MyFile mf, String keyword) throws IOException, FileNotFoundException {

        //lay name cua phan tu trong mang luc loadfile -> chuyen thanh chu thuong . . .
        if (mf.getName().toLowerCase().endsWith(".txt")) {
            try {
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(mf.getFullPath()));
                String line;
                //chay den cuoi dong 
                while ((line = lineNumberReader.readLine()) != null) {
                    //kiem tra tung dong neu co chua ki tu keyword
                    if (line.contains(keyword.toUpperCase()) || line.contains(keyword.toLowerCase())) {//
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
        List<MyFile> listFiles = new ArrayList<>();//luu cac file minh tim ra neu thoa dieu kien
        for (MyFile f : files) { //lay tung phan tu trong mang files
            if (searchFile(f, keyword)) {
                listFiles.add(f);
            }
        }
        //tim ra file do nhung file dang la luong nen phai chuyen thanh mang de show ra man hinh
        MyFile[] foundFiles = listFiles.stream().toArray(MyFile[]::new);
        list(foundFiles);//hien thi fille da tim ra
    }

    /**
     * @return choice from user
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
     * Check input limit and another word
     *
     * @param from: min value
     * @param to: max value
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
            int choice = menu();
            switch (choice) {
                case 1: {
                    while (true) {
                        try {
                            System.out.print("Enter a folder: ");
                            String foldername = scanner.nextLine();
                            main.loadFiles(foldername);
                            main.list(files);
                            break;
                        } catch (Exception e) {
                            System.out.println("Error - File not found - Please try another name!");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Sort the list of file by using");
                    System.out.println("1.Selection sort");
                    System.out.println("2.Insection sort");
                    System.out.println("3.Quick sort");
                    System.out.print("Your choice: ");
                    int subchoice = checkInput(1, 3);

                    if (subchoice == 1) {
                        main.sort(SortType.SELECTIONSORT);
                    } else if (subchoice == 2) {
                        main.sort(SortType.INSERTTIONSORT);
                    } else if (subchoice == 3) {
                        main.sort(SortType.QUICKSORT);
                    }
                    break;
                }
                case 3: {
                    while (true) {
                        try {
                            System.out.print("Enter any keyword to search: ");
                            main.searchFile(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("File not found!!!");
                        }
                    }
                    break;
                }
                case 0: {
                    return;
                }

            }
        }

    }
}
