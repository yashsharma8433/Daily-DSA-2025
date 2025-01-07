class Solution {
    public boolean isPalindrome(int x) {
        int original = x;
        int rev = 0;

        // Reverse the number
        while (x > 0) {
            int ld = x % 10; // Get the last digit
            rev = (rev * 10) + ld; // Build the reversed number
            x = x / 10; // Remove the last digit
        }

        // Explicitly use an if statement
        if (original == rev) {
            return true;
        } else {
            return false;
        }
    }
}
