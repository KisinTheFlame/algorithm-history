package luogu.p1271;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n + 1];
        for (int i = 0; i < m; i++) {
            a[scanner.nextInt()]++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < a[i]; j++) {
                builder.append(i).append(" ");
            }
        }
        System.out.println(builder);
    }
}
