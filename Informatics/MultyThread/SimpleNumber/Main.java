package Informatics.MultyThread.SimpleNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int a = 2;
    public static int number = 1733;
    public static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int threadsQuantity = sc.nextInt();
        List<IsSimple> threads = new ArrayList<>();
        int divQuantity = (int) Math.sqrt(number);
        int t = divQuantity / threadsQuantity;
        int i = 2;

        for (int a = 0; a < threadsQuantity; a++) {
            int j = i + t;

            if (a == threadsQuantity - 1)
                j = (int) Math.sqrt(number);

            IsSimple num = new IsSimple(i, j);
            threads.add(num);
            num.start();
            i = j;
        }

        for (IsSimple num : threads) {
            num.join();
        }
        System.out.println(flag);
    }

    public static class IsSimple extends Thread {
        private int i;
        private int j;

        IsSimple(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            for (int a = i; a <= j && flag; a++) {
                System.out.println(Thread.currentThread().getName() + " number: " + a);
                if (number % a == 0)
                    flag = false;
            }
        }
    }
}
