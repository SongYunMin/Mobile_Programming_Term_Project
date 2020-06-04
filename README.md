# Mobile Programming Term Project

---

## Requirements

**Program Action Environment** : Android or iOS

**Use Compiler** : Compiler suitable for the environment in which the program operates

**Use Language** : Compiler-driven language

## Functions

- 사칙연산, 나머지 연산(Modular), 비트 연산 (AND, OR ,NOT, XOR) 가능해야 함
- 다수의 연산자와 피 연산자로 구성된 계산식을 처리할 수 있어야 하며, 해당 계산식을 처리할 때 연산자 우선순위에 따라 게산을 수행해야 한다. **(괄호 포함)**
(e.g: 1+2*3-4*(5+6)/7 = 0.714285)
- 계산기 프로그램 구현 시 반드시 **Linked-List를 사용해야 한다**
- 계산기 프로그램에 입력하는 계산식은 키보드로부터 입력받을 수 있어야 할 뿐 아니
라, **파일로부터도 입력**받을 수 있어야 한다
    - 입력받은 계산식은 **Queue에 저장해야 한다 (Optional)**
- 계산기 프로그램에서 입력받은 계산식의 결과출력은 화면으로 출력할 수 있어야 할 뿐
아니라, **파일로도 출력**할 수 있어야 한다. **(Optional)**
- 사용자가 이전에 입력한 계산식을 볼 수 있는 History 기능을 제공해야 한다. 이 기능
을 제공하기 위한 명령어 (e.g: history) 또는 메뉴를 제공해야 하며, History의 최대
개수는 최대 50개로 한다. (Optional)
    - History는 파일에 저장할 수 있어야 한다.
    - History에 있는 계산식은 사용자가 임의로 삭제하거나, 임의의 자리에 삽입할 수 있어야 한다. 이 기능의 구현 시 Linked-List를 사용해야 한다.
- 계산기 프로그램의 정수부분 연산은 **최대 50자리까지** 처리할 수 있어야 한다.
- 계산기 프로그램의 소수부 연산은 **해당 언어에서 제공하는 배정밀도에 따른다.**
- 계산기 프로그램에서 입력된 계산식의 연산 시 **Stack을 사용해야 한다. (Optional)**
- **계산 Quiz 기능을 제공해야 한다.** 계산 Quiz 기능의 명세는 다음과 같다. (Optional)
    - 사전에 입력된 계산식이 들어있는 텍스트 파일을 읽어서, 랜덤하게 화면에 출제한 후
    사용자가 입력한 결과값의 정답 여부와 문제 풀이 시간의 총합을 나타낼 수 있어야
    하며, 총점이 표현되어야 한다. 문제 풀이 시, 사전에 입력된 계산식 문제가 중복되
    어 출제되어서는 안된다. 즉, 문제 풀이 기간 동안 반드시 한번만 출제 되어야 한다.
    - 이기능의 구현 시 **Linked-List를 사용해야 한다.**
