package luogu.p1135;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static class Node {
        public int layer;
        public int step;

        public Node(int layer, int step) {
            this.layer = layer;
            this.step = step;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[] k = new int[210];
        for (int i = 1; i <= n; i++) {
            k[i] = scanner.nextInt();
        }
        boolean[] visited = new boolean[210];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(a, 0));
        visited[a] = true;
        while (!queue.isEmpty()) {
            Node current = queue.peek();
            queue.poll();
            if (current.layer == b) {
                System.out.println(current.step);
                return;
            }
            int down = current.layer - k[current.layer];
            int up = current.layer + k[current.layer];
            if (down > 0 && !visited[down]) {
                queue.add(new Node(down, current.step + 1));
                visited[down] = true;
            }
            if (up <= n && !visited[up]) {
                queue.add(new Node(up, current.step + 1));
                visited[up] = true;
            }
        }
        System.out.println(-1);
    }
}
