package level1.bandage;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int solution(int[] bandage, int health, int[][] attacks) {
        // t초마다 x + y 회복
        // 공격당하거나 시전시간이 끝나면 취소. 다시 사용하면 y = 0
        // bandage는 기술 시전 시간, 1초당 회복량(x), 추가 회복량(y)
        // health는 최대 체력
        // attacks는 공격 시간, 피해량
        // 죽으면 -1
        int answer = health;
        int sequence = 0;

        int last = attacks[attacks.length - 1][0];
        Map<Integer, Integer> attcakMap = new HashMap<>();
        for (int[] attack : attacks) {
            attcakMap.put(attack[0], attack[1]);
        }

        for (int i = 0; i < last; i++) {
            // 공격 먼저 확인 - 공격 당하면 회복 X
            int damage = attcakMap.get(i + 1) != null ? attcakMap.get(i + 1) : 0;
            
            if (damage > 0) {
                answer -= damage;
                sequence = 0;
            } else {
                answer += bandage[1];
                sequence++;
            }
            
            if (sequence == bandage[0]) {
                answer += bandage[2];
                sequence = 0;
            }

            if (answer > health) {
                answer = health;
            }
            if (answer <= 0) {
                return -1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] bandage = new int[] {5,1,5};
        int health = 30;
        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
        System.out.println(solution(bandage, health, attacks));
    }
}
