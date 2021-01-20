/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    private static Thread thread01,thread02,thread03;

    public static void main(String a[]){
        thread01 = new Thread(new CountThread(0, 99));
        thread02 = new Thread(new CountThread(99, 199));
        thread03 = new Thread(new CountThread(200, 299));

        start();
    }

    public static void start(){
        thread01.start();
        thread02.start();
        thread03.start();
    }

    public static void run(){
        thread01.run();
        thread02.run();
        thread03.run();
    }
}
