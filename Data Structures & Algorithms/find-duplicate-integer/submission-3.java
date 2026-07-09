class Solution {
    public int findDuplicate(int[] nums) {

for (int num:nums){
    int indx=Math.abs(num)-1;
    if(nums[indx]<0){
        return Math.abs(num);
    }
    nums[indx]*= -1;
}
return -1;
    }
}

// }