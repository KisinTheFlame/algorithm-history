package luogu.p1005;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final BigInteger ONE = new BigInteger("1");
        final BigInteger TWO = new BigInteger("2");
        final BigInteger ZERO = new BigInteger("0");

        BigInteger[] base = new BigInteger[100];
        base[0] = ONE;
        for (int i = 1; i < base.length; i++) {
            base[i] = base[i-1].multiply(TWO);
        }

        Scanner scanner = new Scanner(System.in);
        int[] a = new int[100];
        BigInteger[][] dp = new BigInteger[100][100];
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        BigInteger ans = ZERO;
        for (int time = 0; time < n; time++) {

            for (int i = 0; i < m; i++) {
                a[i] = scanner.nextInt();
            }
            for (int i = 0; i < m; i++) {
                dp[i][i] = new BigInteger(String.valueOf(a[i]));
            }
            for (int len = 2; len <= m; len++) {
                for (int left = 0, right = len - 1; right < m; left++, right++) {
                    BigInteger value1 = dp[left+1][right].multiply(TWO).add(new BigInteger(String.valueOf(a[left])));
                    BigInteger value2 = dp[left][right-1].multiply(TWO).add(new BigInteger(String.valueOf(a[right])));
                    dp[left][right] = value1.max(value2);
                }
            }
            ans = ans.add(dp[0][m-1]);
        }
        System.out.println(ans.multiply(TWO));
    }
}
