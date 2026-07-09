class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // Edge case: s1 longer than s2
        if (s1.length() > s2.length()) return false;

        // Frequency map for s1
        HashMap<Character, Integer> count1 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            count1.put(c, count1.getOrDefault(c, 0) + 1);
        }

        // Frequency map for current window in s2
        HashMap<Character, Integer> window = new HashMap<>();

        int l = 0;
        int len1 = s1.length();

        for (int r = 0; r < s2.length(); r++) {
            char rightChar = s2.charAt(r);
            window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);

            // If window size exceeds s1.length(), shrink from left
            if (r - l + 1 > len1) {
                char leftChar = s2.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                if (window.get(leftChar) == 0) {
                    window.remove(leftChar);
                }
                l++;
            }

            // If window size matches s1 length, check if it's a permutation
            if (r - l + 1 == len1) {
                if (window.equals(count1)) {
                    return true;
                }
            }
        }

        return false;
    }
}