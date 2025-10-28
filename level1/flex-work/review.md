https://school.programmers.co.kr/learn/courses/30/lessons/388351

## 🧩 제출 시 틀린 부분

- startday를 안 썼음 → 요일 계산이 고정됨 (`j + 5`)
- 09:55 + 10 = 965 → HHMM을 그냥 더함 → 시간 비교 오류
- isWeekend에서 day == 7 비교 → 불필요

## ✅ 수정한 내용

- `(startday + j) % 7` 로 요일 계산
- HHMM → 분 단위 변환 함수 추가 (`toMinute`)
- `isWeekend` 수정 (0=일, 6=토)

## 💡 배운 점 / 개선 아이디어

- 입력 형식이 단순 정수여도 실제 의미(시간, 날짜 등)를 변환해야 함
- weekday 계산 시 startday를 기준으로 순회해야 함
- 다음에는 시간 계산 문제에서 항상 `toMinutes` 함수 먼저 만들기

## 📈 개선 후 결과

- 예제 입력 기준 정답: 3
- 모든 테스트케이스 통과
