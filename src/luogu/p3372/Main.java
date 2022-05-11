package luogu.p3372;

import java.util.Arrays;
import java.util.Scanner;

class SegmentTree {
    private final long[] array;
    private final Node root;

    public SegmentTree(int size) {
        this.array = new long[size + 1];
        root = new Node(1, size);
    }

    public SegmentTree(int size, long[] array) {
        this.array = Arrays.copyOf(array, array.length);
        root = new Node(1, size);
    }

    public void add(int left, int right, long offset) {
        root.add(left, right, offset);
    }

    public long getSum(int left, int right) {
        return root.getSum(left, right);
    }

    private class Node {
        private final int leftIndex;
        private final int rightIndex;
        private final Node leftNode;
        private final Node rightNode;
        private long sum;
        private long lazy = 0;

        public Node(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            if (leftIndex == rightIndex) {
                leftNode = rightNode = null;
                sum = array[leftIndex];
            } else {
                leftNode = new Node(leftIndex, middleIndex());
                rightNode = new Node(middleIndex() + 1, rightIndex);
                sum = leftNode.sum + rightNode.sum;
            }
        }

        public void add(int left, int right, long offset) {
            if (left < leftIndex || right > rightIndex || left > right) {
                throw new RuntimeException();
            }
            if (left == leftIndex && right == rightIndex) {
                lazy += offset;
                return;
            }
            if (right <= middleIndex()) {
                leftNode.add(left, right, offset);
                sum = leftNode.getSum(leftIndex, middleIndex()) + rightNode.getSum(middleIndex() + 1, rightIndex);
                return;
            }
            if (left > middleIndex()) {
                rightNode.add(left, right, offset);
                sum = leftNode.getSum(leftIndex, middleIndex()) + rightNode.getSum(middleIndex() + 1, rightIndex);
                return;
            }
            leftNode.add(left, middleIndex(), offset);
            rightNode.add(middleIndex() + 1, right, offset);
            sum = leftNode.getSum(leftIndex, middleIndex()) + rightNode.getSum(middleIndex() + 1, rightIndex);
        }

        public long getSum(int left, int right) {
            if (left < leftIndex || right > rightIndex || left > right) {
                throw new RuntimeException();
            }
            if (left == leftIndex && right == rightIndex) {
                return sum + lazy * (rightIndex - leftIndex + 1);
            }

            shiftDownLazy();
            if (right <= middleIndex()) {
                return leftNode.getSum(left, right);
            }
            if (left > middleIndex()) {
                return rightNode.getSum(left, right);
            }
            return leftNode.getSum(left, middleIndex()) + rightNode.getSum(middleIndex() + 1, right);
        }

        private void shiftDownLazy() {
            sum += lazy * (rightIndex - leftIndex + 1);
            leftNode.add(leftIndex, middleIndex(), lazy);
            rightNode.add(middleIndex() + 1, rightIndex, lazy);
            lazy = 0;
        }

        private int middleIndex() {
            return leftIndex + ((rightIndex - leftIndex) >> 2);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextLong();
        }
        SegmentTree segmentTree = new SegmentTree(n, a);
        for (int i = 1; i <= m; i++) {
            int operator = scanner.nextInt();
            if (operator == 1) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                long offset = scanner.nextLong();
                segmentTree.add(left, right, offset);
            } else {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                System.out.println(segmentTree.getSum(left, right));
            }
        }
    }
}
