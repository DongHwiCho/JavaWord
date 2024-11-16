# Commit Message Guidelines

## Commit Message Structure

커밋 메시지는 아래와 같은 구조로 작성합니다:

```
<타입>: <제목>

<본문 (선택 사항)>

<푸터 (선택 사항)>
```

### 1. **타입 (Type)**  
커밋의 목적을 명확히 하기 위해 다음 타입 중 하나를 선택합니다:

- **feat**: 새로운 기능 추가
- **fix**: 버그 수정
- **docs**: 문서 수정 (README, 문서화 관련 변경)
- **style**: 코드 포맷팅, 세미콜론 누락 등 코드 변경이 없는 수정
- **refactor**: 코드 리팩토링 (기능 변경 없음)
- **test**: 테스트 코드 추가 또는 수정
- **chore**: 빌드 프로세스, 패키지 매니저 설정 등 기타 작업
- **perf**: 성능 개선
- **revert**: 이전 커밋 되돌리기

---

### 2. **제목 (Title)**  
- 50자 이내로 간결하게 작성합니다.
- 대문자로 시작하고, 마침표를 생략합니다.
- 명령형 동사를 사용합니다.  
  예: "Add", "Fix", "Update"

---

### 3. **본문 (Body)** _(선택 사항)_  
- 변경의 이유와 맥락을 72자 단위로 줄바꿈하여 자세히 설명합니다.
- "왜"와 "무엇"을 중심으로 작성하세요.

---

### 4. **푸터 (Footer)** _(선택 사항)_  
- 중요한 참고 정보나 관련 이슈 번호를 포함합니다.
- 예: `Closes #123` 또는 `Related to #456`

---

## Examples

### 간단한 커밋
```
feat: Add user authentication module
```

### 상세한 커밋
```
fix: Resolve null pointer issue in login function

The error occurred due to an uninitialized variable when the
login form was submitted without entering any data. This fix
ensures proper null-check validation.

Closes #42
```

### 리팩토링
```
refactor: Simplify database query logic

Replaced nested queries with a single JOIN to improve readability
and performance. No functionality was changed.
```

### 문서 수정
```
docs: Update README with installation instructions
```

### 작업 되돌리기
```
revert: Revert "Add caching mechanism"

This reverts commit 1a2b3c4d5e.
```

---