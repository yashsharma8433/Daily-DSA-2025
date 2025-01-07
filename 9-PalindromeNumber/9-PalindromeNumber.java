class Solution {
    public boolean isPalindrome(int x) {
        int original = x;
        int rev = 0 ; 

        while (x > 0){
            int ld = x%10 ; //121 then 1 aega 
            rev = (rev*10)+ld; // 1 then 12 then 121
            x = x / 10 ;


        }

return original == rev;
        
    }
}