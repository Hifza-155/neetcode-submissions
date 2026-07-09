class Solution {
    public String minWindow(String s, String t) {
if (t.length() > s.length()) return "";

        HashMap<Character, Integer> countT = new HashMap<>();
        for (char c : t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> window = new HashMap<>();

        int l = 0;
int minLen=Integer.MAX_VALUE;
int minStart=0;
int req=countT.size();
int matches=0;
        for (int r = 0; r < s.length(); r++) {
            char rightChar = s.charAt(r);
            window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);

if(countT.containsKey(rightChar) && window.get(rightChar).equals(countT.get(rightChar))){
    matches++;
}

            while(matches==req) { 
                if(r-l+1<minLen){
                    minLen=r-l+1;
                    minStart=l;
                }

                char leftChar = s.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                if(countT.containsKey(leftChar) && window.get(leftChar) < countT.get(leftChar)) {
                    matches--;
                }
                l++;
            }
        }

return minLen==Integer.MAX_VALUE?"": s.substring(minStart,minStart+minLen);     }
}
