package Informatics.MultyThread.MatrixMultiplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int a = 2;
    private static int b = 3;
    private static int c = 4;
    private static int[][] x = new int[a][b];
    private static int[][] y = new int[b][c];
    private static int[][] z = new int[a][c];

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int threads = sc.nextInt();
        int t = (a * c) / threads;
        create();
        List<Thread1> list = new ArrayList<>();
        int i = 0;

        for (int k = 0; k < threads; k++) {
            int j = t + i;

            if (k == threads - 1)
                j = a * c;

            Thread1 thread1 = new Thread1(i, j);
            thread1.start();
            list.add(thread1);
            i = j;
        }

        for (Thread1 thread1 : list) {
            thread1.join();
        }

        result();
    }

    private static void create() {
        //Scanner sc = new Scanner(System.in);
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                //x[i][j] = sc.nextInt();
                x[i][j] = (int) (Math.random() * 10);
                System.out.println(x[i][j] + " ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < c; j++) {
                //y[i][j] = sc.nextInt();
                y[i][j] = (int) (Math.random() * 10);
                System.out.println(y[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static class Thread1 extends Thread {
        int i;
        int j;

        Thread1(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            System.out.println("Thread: " + getName() + " From: " + i + " To: " + j);
            for (int index = i; index < j; index++) {
                multiplicate(index / c, index / c);
            }
            System.out.println("Thread: " + getName() + " finished");
        }

        private void multiplicate(int i, int j) {
            for (int k = 0; k < b; k++) {
                z[i][j] = z[i][j] + x[i][k] * y[k][j];
            }
        }
    }

    public static void result() {
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < c; j++) {
                System.out.println(z[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}