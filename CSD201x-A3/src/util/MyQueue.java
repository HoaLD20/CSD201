/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.LinkedList;

/**
 *
 * @author TrongDuyDao
 */
public class MyQueue {
    
    LinkedList a = null;
    
    public MyQueue(){
        a = new LinkedList();
    }
    
    public void enqueue(Object obj){
        a.addLast(obj);
    }
    
    public boolean isEmpty(){
        return a.isEmpty();
    }
    
    public Object dequeue(){
        if(isEmpty()) return null;
        else return a.removeFirst();
    }
    
    public Object front(){
        if(isEmpty()) return null;
        else return a.getFirst();
    }
}
