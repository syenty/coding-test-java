public class Solution {
    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 기능 3개 - 10초 전(prev), 10초 후(next), 오프닝 스킵(위치에 도달하면 자동)
        // 동영상의 길이를 나타내는 문자열 video_len, 
        // 기능이 수행되기 직전의 재생위치를 나타내는 문자열 pos, 
        // 오프닝 시작 시각을 나타내는 문자열 op_start, 
        // 오프닝이 끝나는 시각을 나타내는 문자열 op_end, 
        // 사용자의 입력을 나타내는 1차원 문자열 배열 commands
        int len = toSec(video_len);
        int cur = toSec(pos);
        int ops = toSec(op_start);
        int ope = toSec(op_end);
            
        cur = skipOp(cur, ops, ope);
        
        for (String command : commands) {
            if ("prev".equals(command)) {
                cur = Math.max(0, cur - 10);
            } else {
                cur = Math.min(len, cur + 10);
            }
            cur = skipOp(cur, ops, ope);
        }

        return toStr(cur);
    }

    private static int toSec(String mmss) {
        String[] times = mmss.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    private static String toStr(int sec) {
        int m = sec / 60, s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }

    private static int skipOp(int cur, int ops, int ope) {
        return (cur >= ops && cur <= ope) ? ope : cur;
    }

    public static void main(String[] args) {
        System.out.println(solution("34:33", "13:00", "00:55", "02:55", new String[] {"next", "prev"}));
    }
}