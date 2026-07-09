class Solution {
    public int findMin(int[] nums) {
       int left=0;
       int rght=nums.length-1;
       while(left<rght){
        int mid=left+(rght-left)/2;
        if(nums[mid]<nums[rght]){
            rght=mid;
        }else{
            left=mid+1;
        }
       }
        return nums[left];
    }
}
