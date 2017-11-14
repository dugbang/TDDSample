## TDD에서 사용할 샘플 코드 정리

**출처**
* [백명석-클린코드](https://www.youtube.com/playlist?list=PLuLb6MC4SOvXCRePHrb4e-EYadjZ9KHyH)
* [백명석님 깃허브](https://github.com/msbaek/)

* 일러두기
    - github에서 클론한 다음 해당 리비전으로 브린치하면 되지만 git 사용법을 몰라서 무식하게 복사하여 저장함...
    - **다짐**; _머리로 아는 것이 아니라 몸으로 익혀야 하는 부분으로 실습을 해보자!!_

### [expenses refactoring](https://github.com/msbaek/expense/)
* Episode 10. OCP(Open but Closed Principle)에서 사용된 예제
    - Function Should Do One Thing
    - SRP(Single Responsibility Principle)
    - Feature Envy
    - OCP(Open but Closed Principle)
* 큰 함수를 여러개은 작은 함수로 나누는 방법 > _초보적인 기술방법..^^_
    - [소스코드 정보](https://github.com/msbaek/expense/commit/114deae805259d0dc6c3b7bdbba97e2f2bdc55f5?diff=unified)

### [fitness example](https://github.com/msbaek/fitness-example)
* 큰 함수 리팩토리 방법
    - [소스코드 정보](https://github.com/msbaek/fitness-example/commit/464fe42929b93fefcb9b99980db76ec1c34a3881#diff-0494caf1dfbc3a4ff5b53428cae27fc3)

### [PrintPrimes Example](https://github.com/msbaek/print-prime)
* 복잡한 함수를 2개의 클래스로 Extract Method Object하는 과정
    - [소스코드 정보](https://github.com/msbaek/print-prime/commit/c9e14d7e0d506c8b3b037cd86b0cb7737ac0a07b#diff-25d902c24283ab8cfbac54dfa101ad31)
* 파일 2개를 추가 하였으며 윈도 환경에서 테스트할 수 있도록 수정.
  - 필요한 경우 **테스트 파일을 직접 만들어 리팩토링을 진행** 할 수 있다.
  - 테스트 파일이 필요한 이유는 공격적인 리팩토링을 하기 위해서다.

### videostore
* [Switch 문장을 Polymorphic Dispatcher로 치환하기](https://github.com/msbaek/videostore)
    - [소스코드 정보](https://github.com/msbaek/videostore/commit/96948b22da842093c291ff876845bfc485710ab4#diff-25d902c24283ab8cfbac54dfa101ad31)
