https://school.programmers.co.kr/learn/courses/30/lessons/250137

# 💬 코드 리뷰 — 프로그래머스 “붕대 감기” 문제

## ✅ 전반적인 평가

로직 자체는 정확하고 잘 구현되어 있음.

- 공격 시 회복 X
- 연속 회복 카운트 및 시전 시간 관리
- 체력 상한 및 사망 처리  
  → 문제 조건을 모두 충족함.

다만 **가독성**, **불필요한 자료구조 제거**, **변수명 개선** 측면에서 약간의 리팩터링 여지가 있음.

---

## 🧩 세부 피드백

### 1. **루프 인덱스 개선**

```java
for (int t = 1; t <= last; t++)
```

- `i + 1` 대신 `t`로 명확하게 시간 축을 표현.
- 공격/회복 순서가 직관적으로 보임.

---

### 2. **Map 대신 포인터(two-pointer) 사용**

- `attacks`는 보통 정렬되어 있고, 한 시점에 한 번만 공격이 발생함.
- 따라서 `Map` 대신 **인덱스 포인터**로 순회하면 성능과 가독성 모두 향상됨.
- `HashMap` 사용 시 **오토박싱 비용**과 **오타 위험** (`attcakMap`)도 줄일 수 있음.

---

### 3. **가독성 높은 변수명**

| 기존 변수  | 개선 제안   | 의미            |
| ---------- | ----------- | --------------- |
| `answer`   | `hp`        | 현재 체력       |
| `sequence` | `streak`    | 연속 회복 초 수 |
| `health`   | `maxHealth` | 최대 체력       |

- 변수명만 봐도 역할이 분명해짐.

---

### 4. **`getOrDefault` 사용 (Map 유지 시)**

```java
int damage = attackMap.getOrDefault(t, 0);
```

- null 체크와 삼항연산자보다 간결하고 안전함.

---

### 5. **체력 캡핑 위치 명확화**

- 회복 시점마다 `Math.min(maxHealth, hp + ...)` 적용.
- 추가 회복(`y`) 후에도 체력 상한 초과 방지.

---

### 6. **시전 완료 후 초기화 타이밍**

- `streak == bandage[0]`일 때 보너스 회복 후 `streak = 0`으로 리셋하는 것은 문제 설명과 정확히 일치함.

---

### 7. **불필요한 자료구조 제거로 성능 개선**

- `HashMap` 대신 배열 또는 포인터 접근으로 불필요한 객체 생성 방지.
- `O(last)` 복잡도는 유지되면서 공간 복잡도 감소.

---

## 🧱 리팩터링 예시

```java
public static int solution(int[] bandage, int maxHealth, int[][] attacks) {
    final int T = bandage[0]; // 시전 시간
    final int X = bandage[1]; // 초당 회복량
    final int Y = bandage[2]; // 추가 회복량

    int hp = maxHealth;
    int streak = 0;
    int lastTime = attacks[attacks.length - 1][0];
    int i = 0;

    for (int t = 1; t <= lastTime; t++) {
        // 공격 시
        if (i < attacks.length && attacks[i][0] == t) {
            hp -= attacks[i][1];
            i++;
            streak = 0;
            if (hp <= 0) return -1;
            continue;
        }

        // 회복 시
        hp = Math.min(maxHealth, hp + X);
        streak++;

        // 시전 완료 시 추가 회복
        if (streak == T) {
            hp = Math.min(maxHealth, hp + Y);
            streak = 0;
        }
    }

    return hp;
}
```

---

## 🌟 개선 요약

| 항목          | 개선 내용                              |
| ------------- | -------------------------------------- |
| 루프 구조     | 1 기반 시간 루프로 변경                |
| 자료구조      | HashMap → 포인터 방식                  |
| 변수명        | 명확하고 직관적으로 변경               |
| 체력 관리     | 회복마다 `Math.min`으로 캡핑           |
| 불필요한 연산 | null 체크, 박싱/언박싱 제거            |
| 가독성        | 전체적으로 흐름이 한눈에 보이게 단순화 |

---

원본 코드의 로직은 정확하지만,  
이 리팩터링 버전은 **가독성, 성능, 유지보수성**이 모두 향상된 버전이야 💪
