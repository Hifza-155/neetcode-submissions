
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        // Two sets representing search frontiers from start and end
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // ALWAYS expand the smaller set to minimize operations
            if (beginSet.size() > endSet.size()) {
                Set<String> swap = beginSet;
                beginSet = endSet;
                endSet = swap;
            }

            Set<String> nextLevelSet = new HashSet<>();

            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char oldChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == oldChar) continue;
                        chars[i] = c;
                        String newWord = new String(chars);

                        // If the two search fronts meet, path is complete!
                        if (endSet.contains(newWord)) {
                            return level + 1;
                        }

                        // If newWord is valid, queue it for next level
                        if (wordSet.contains(newWord)) {
                            nextLevelSet.add(newWord);
                            wordSet.remove(newWord); // Mark visited
                        }
                    }
                    chars[i] = oldChar; // Reset character
                }
            }

            beginSet = nextLevelSet;
            level++;
        }

        return 0;
    }
}