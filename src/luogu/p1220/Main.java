package luogu.p1220;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
         * dp[i][j][k] - cost
         * i - left lamp
         * j - right lamp
         * k - at the left if 0, right if 1
         */
        int[][][] dp = new int[60][60][2];
        int[] power = new int[60];
        int[] position = new int[60];
        int n = scanner.nextInt();
        int start = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            position[i] = scanner.nextInt();
            power[i] = scanner.nextInt();
        }
    }
}
