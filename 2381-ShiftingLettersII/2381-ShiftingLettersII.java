class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n + 1]; // Difference array initialized to 0

        // Apply the shifts using the difference array
        for (int[] query : shifts) {
            int L = query[0];
            int R = query[1];
            int dir = query[2];
            int x = (dir == 0) ? -1 : 1; // Determine the direction

            diff[L] += x;
            if (R + 1 < n) {
                diff[R + 1] -= x;
            }
        }

        // Step 2: Calculate the cumulative sum
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }

        // Step 3: Apply the shifts to the string
        char[] result = s.toCharArray(); // Convert string to char array for modification
        for (int i = 0; i < n; i++) {
            int shift = diff[i] % 26;
            if (shift < 0) {
                shift += 26; // Handle negative shifts
            }
            result[i] = (char) ((result[i] - 'a' + shift) % 26 + 'a');
        }

        return new String(result); // Convert char array back to string
    }
}
