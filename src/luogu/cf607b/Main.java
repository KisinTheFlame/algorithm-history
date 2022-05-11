package luogu.cf607b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = a[i] == a[i + 1] ? 1 : 2;
        }
        for (int len = 3; len <= n; len++) {
            for (int left = 1, right = len; right <= n; left++, right++) {
                dp[left][right] = dp[left + 1][right - 1] + (a[left] == a[right] ? 0 : 2);
                for (int split = left; split < right; split++) {
                    dp[left][right] = Integer.min(dp[left][right], dp[left][split] + dp[split + 1][right]);
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}
