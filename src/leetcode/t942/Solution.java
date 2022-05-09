package leetcode.t942;

public class Solution {
    public int[] diStringMatch(String s) {
        int[] ans = new int[s.length() + 1];
        ans[0] = 0;
        int high = 1, low = 0;
        for (int i = 0; i < s.length(); i++) {
            ans[i + 1] = s.charAt(i) == 'I' ? high++ : --low;
        }
        int offset = -low;
        for (int i = 0; i < ans.length; i++) {
            ans[i] += offset;
        }
        return ans;
    }
}
