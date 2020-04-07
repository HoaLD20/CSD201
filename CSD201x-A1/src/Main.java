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

    private static MyFile[] filesText;
    private static final List<MyFile> listFileText = new ArrayList<>();
    /*default element of MyFile[] = null when restart or renew program*/

    public Main() {
        files = null;
        filesText = null;
    }

    //get information of all text files under given folder name
    public void loadFiles(String folder) {
        /*MyFile - contain name, size, fullPath*/
        List<MyFile> listFiles = new ArrayList<>();//contain content of folder
        loadFiles(folder, listFiles);
        //khi mo files -> tao thanh 1 luong - stream -> chuyen thanh mang de truy cap vao nhung phan tu trong mang
        files = listFiles.stream().toArray(MyFile[]::new);/*files is array - so change MyFile (arraylist) to array */

        filesText = listFileText.stream().toArray(MyFile[]::new);
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

                listFiles.add(new MyFile(file.getName(), file.length(), file.getAbsolutePath()));//add name, size, path

                if (/*file.getName().endsWith("docx") || */file.getName().endsWith("txt")) {
                    listFileText.add(new MyFile(file.getName(), file.length(), file.getAbsolutePath()));
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
        //check all of element in files[] from 0 -> n - 1
        for (int i = 0; i < filesText.length - 1; i++) {
            int min = i;//default first element is min
            for (int j = i + 1; j < filesText.length; j++) {//get all of elements from 1 to n
                if (filesText[j].getSize() < filesText[min].getSize()) {
                    min = j;// get min element
                }
            }
            //swap two element
            MyFile temp = filesText[min];
            filesText[min] = filesText[i];
            filesText[i] = temp;

        }

    }

    //sort the list of files ascending by size (use insertion sort)
    public void insertionSort() {
        //do nothing if list of files is empty
        /*You should insert code for sorting here, you are going to sort the list of
         loaded files named "files" ascending by file size.*/
        for (int i = 0; i < files.length; i++) {//get all of element in files[]
            MyFile current = files[i];//to begin first element is condidered fully sorted
            int j = i - 1;//from the remaining numbers the leftmost - 1 is taken out and compare to the already sorted number to its right
            while (j >= 0 && files[j].getSize() >= current.getSize()) {//if the already sorted element is larger the two numbers -> swap
                files[j + 1] = files[j];//loop until j < 0 - swap
                j--;
            }
            files[j + 1] = current;//insert ~ swap
        }
    }

    public void quickSort(MyFile[] files, int left, int right) {

        if (left >= right) {//in case array have 1 element
            return;
        }

        int pivot = (int) files[(left + right) / 2].getSize();//pivot = middle element
        int i = left;//first element
        int j = right;//last element

        do {
            while (files[i].getSize() < pivot) {//compare first element and middle element -> if true -> increase 1 from i
                i++;
            }
            while (files[j].getSize() > pivot) {//compare last element and middle element -> if true -> decrease 1 from j
                j--;
            }
            //loop above stop -> swapppppppppp
            if (i <= j) {
                MyFile temp = files[i];
                files[i] = files[j];
                files[j] = temp;
                i++;
                j--;
            }
        } while (i < j); //stop when j = i
        //recusion -> save stack in line 145                                  
        quickSort(files, left, j);
        quickSort(files, i, right); //lưu chỉ thị lệnh và giá trị của các biến local
    }

    //sort and output sorted list of text files
    public void sort(SortType st) {
        if (st == SortType.INSERTTIONSORT) {
            insertionSort();
        } else if (st == SortType.SELECTIONSORT) {
            selectionSort();
        } else if (st == SortType.QUICKSORT) {
            quickSort(files, 0, files.length - 1);
        }
        //output result after sorting
        list(filesText);
    }

    //return true if given MyFile contains given keyword, otherwise return false
    public boolean searchFile(MyFile mf, String keyword) throws IOException, FileNotFoundException {

        //get name in form lower and get extension. . .
        if (mf.getName().toLowerCase().endsWith(".txt")) {
            try {
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(mf.getFullPath()));
                String line;
                //run to last line 
                while ((line = lineNumberReader.readLine()) != null) {
                    //check each line if contain keyword
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
        List<MyFile> listFiles = new ArrayList<>();//save file found if pass condition
        for (MyFile f : files) { //take each element in files[]
            if (searchFile(f, keyword)) {
                listFiles.add(f);
            }
        }
        //change thread of file to array
        MyFile[] foundFiles = listFiles.stream().toArray(MyFile[]::new);
        list(foundFiles);//show file found
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
                    System.out.println("Thank you <3 !!!");
                    return;
                }

            }
        }

    }
}
