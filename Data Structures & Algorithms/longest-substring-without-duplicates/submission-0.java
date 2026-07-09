class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer>map=new HashMap<>();
        int l=0,result=0;
        for(int r=0;r<s.length();r++){
            if(map.containsKey(s.charAt(r))){
                l=Math.max(map.get(s.charAt(r))+1,l);
            }
            map.put(s.charAt(r),r);
            result = Math.max(result, r - l + 1);

        }
        return result;
    }
}
