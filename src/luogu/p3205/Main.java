package luogu.p3205;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] a = new int[1100];
        long[][][] dp = new long[1100][1100][2];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            dp[i][i][0] = dp[i][i][1] = 1;
        }
        int MOD = 19650827;
        for (int len = 2; len <= n; len++) {
            for (int left = 1; left + len - 1 <= n; left++) {
                int right = left + len - 1;

                // calculate 0
                if (a[left] < a[left + 1]) {
                    dp[left][right][0] += dp[left+1][right][0];
                    dp[left][right][0] %= MOD;
                }
                if (left + 1 != right && a[left] < a[right]) {
                    dp[left][right][0] += dp[left+1][right][1];
                    dp[left][right][0] %= MOD;
                }

                // calculate 1
                if (a[right] > a[right - 1]) {
                    dp[left][right][1] += dp[left][right-1][1];
                    dp[left][right][1] %= MOD;
                }
                if (left + 1 != right && a[right] > a[left]) {
                    dp[left][right][1] += dp[left][right-1][0];
                    dp[left][right][1] %= MOD;
                }
            }
        }
        System.out.println((dp[1][n][0] + dp[1][n][1]) % MOD);
    }
}
