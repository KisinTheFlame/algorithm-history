package luogu.p1352;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] r = new int[n + 1];
        int[][] dp = new int[n + 1][2];
        boolean[] visited = new boolean[n + 1];
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            r[i] = scanner.nextInt();
            set.add(i);
        }
        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int childId = scanner.nextInt();
            int fatherId = scanner.nextInt();
            children.get(fatherId).add(childId);
            set.remove(childId);
        }
        int root = set.stream().findFirst().orElseThrow(RuntimeException::new);
        LinkedList<Integer> calculateStack = new LinkedList<>();
        calculateStack.add(root);
        while (!calculateStack.isEmpty()) {
            int top = calculateStack.getLast();
            if (!visited[top]) {
                visited[top] = true;
                calculateStack.addAll(children.get(top));
            } else {
                calculateStack.removeLast();
                dp[top][0] = children.get(top).stream().mapToInt(id -> Integer.max(dp[id][0], dp[id][1])).sum();
                dp[top][1] = children.get(top).stream().mapToInt(id -> dp[id][0]).sum() + r[top];
            }
        }
        System.out.println(Integer.max(dp[root][0], dp[root][1]));
    }
}
