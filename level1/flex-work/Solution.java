class Solution {
    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int score = 0;

            int scheduledMin = toMinute(schedules[i]) + 10;
            
            for (int j = 0; j < 7; j++) {
                int today = (startday + j) % 7;
                if (isWeekend(today))
                    continue;
                int logMin = toMinute(timelogs[i][j]);
                if (logMin <= scheduledMin)
                    score++;
            }
            
            if (score == 5)
                answer++;
        }

        return answer;
    }

    private static boolean isWeekend(int day) {
        return day == 0 || day == 6;
    }

    private static int toMinute(int hhmm) {
        int h = hhmm / 100;
        int m = hhmm % 100;
        return h * 60 + m;
    }
    
    public static void main(String[] args) {
        int[] schedules = new int[] { 700, 800, 1100 };
        int[][] timelogs = new int[][] {
            {710, 2359, 1050, 700, 650, 631, 659}, 
            {800, 801, 805, 800, 759, 810, 809}, 
            {1105, 1001, 1002, 600, 1059, 1001, 1100}
        };
        int startday = 5;

        System.out.println(solution(schedules, timelogs, startday));
    }
}