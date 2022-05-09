package luogu.p1004;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] map = new int[10][10];
        int[][][][] dp = new int[10][10][10][10];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (scanner.hasNext()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int q = scanner.nextInt();
            if (x == 0 && y == 0 && q == 0) {
                break;
            }
            map[x][y] = q;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    for (int l = 1; l <= n; l++) {
                        dp[i][j][k][l] = Integer.max(dp[i][j][k][l], dp[i - 1][j][k - 1][l]);
                        dp[i][j][k][l] = Integer.max(dp[i][j][k][l], dp[i - 1][j][k][l - 1]);
                        dp[i][j][k][l] = Integer.max(dp[i][j][k][l], dp[i][j - 1][k - 1][l]);
                        dp[i][j][k][l] = Integer.max(dp[i][j][k][l], dp[i][j - 1][k][l - 1]);
                        dp[i][j][k][l] += map[i][j] + map[k][l];
                        if (i == k && j == l) {
                            dp[i][j][k][l] -= map[i][j];
                        }
                    }
                }
            }
        }
        System.out.println(dp[n][n][n][n]);
    }
}
