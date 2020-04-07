/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 
import java.io.Serializable;

/**
 *
 * @author TrongDuyDao
 */
public class MyFile implements Serializable {
    
    //contains information of a File
    
    private String name;
    private long size;
    private String fullPath;

    public MyFile() {
    }

    public MyFile(String name, long size, String fullPath) {
        this.name = name;
        this.size = size;
        this.fullPath = fullPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-10d", name, size);
    }
  
}
