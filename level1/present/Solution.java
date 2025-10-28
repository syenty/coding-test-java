package level1.present;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int solution(String[] friends, String[] gifts) {
        // 두 사람 사이에 더 많은 선물을 준 사람
        // 두 사람이 주고 받은 적이 없거나, 개수가 같으면 선물 지수가 높은 사람
        int answer = 0;
        int n = friends.length;

        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++)
            idxMap.put(friends[i], i);

        int[][] fromTo = new int[n][n];
        int[] presentIndex = new int[n];

        for (String gift : gifts) {
            String[] log = gift.split(" ");
            int from = idxMap.get(log[0]);
            int to = idxMap.get(log[1]);

            fromTo[from][to]++;
            presentIndex[from]++;
            presentIndex[to]--;
        }

        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (fromTo[i][j] > fromTo[j][i]) {
                    results[i]++;
                } else if (fromTo[i][j] < fromTo[j][i]) {
                    results[j]++;
                } else {
                    // 같을 때는 선물 지수 비교
                    if (presentIndex[i] > presentIndex[j]) {
                        results[i]++;
                    } else if (presentIndex[i] < presentIndex[j]) {
                        results[j]++;
                    }
                }
            }
        }

        for (int r : results)
            answer = Math.max(answer, r);
        return answer;
    }
    
    public static void main(String[] args) {
        String[] frieds = new String[] { "muzi", "ryan", "frodo", "neo" };
        String[] gifts = new String[] { "muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi",
                "frodo ryan", "neo muzi" };
        
        System.out.println(solution(frieds, gifts));
    }
}
