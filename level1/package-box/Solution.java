class Solution {
    public static int solution(int n, int w, int num) {
        int answer = 0;

        int floor = (num - 1) / w; // num이 있는 층
        int position = (num - 1) % w; // num이 있는 위치
        int column = floor % 2 == 0 ? position : w - 1 - position; // 실제 num이 있는 열

        int lastFloor = (n - 1) / w; // 마지막 층

        for (int i = floor; i <= lastFloor; i++) {
            int idx = i % 2 == 0 ? (i * w) + (column + 1) : (i * w) + (w - column); 
            if (idx <= n) answer++;
        }

        return answer;
    }
    
    public static void main(String[] args) {
        System.out.println(solution(22, 6, 8)); // 3
        System.out.println(solution(13, 3, 6)); // 4
    }
}