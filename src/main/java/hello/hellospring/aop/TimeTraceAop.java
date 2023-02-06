package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") //헬로스프링 아래의 얘들에겐 다 적용해줘! 라는 애너테이션
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //다음 방향으로 진행하라는 매서드
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}
/*
* AOP를 언제 사용할까? 우리가 만든 member 비즈니스 모델에서 중요한 로직은
* save, findbyId, findbyName, findAll 등의 로직이다.
* 근데 코딩하면서 어디서 병목현상이 일어나는지 시간을 측정하려고 하면,
* 위의 매서드를 찾아가 각각 트라이 캣치문을 사용하려면
* long start = System.currentTimeMillis();
    try {
        return memberRepository.findAll();
    } finally {
        long finish = System.currentTimeMillis();
        long timeMs = finish - start;
    System.out.println("findMembers " + timeMs + "ms");
}
*
* 위와 같이 모든 매서드마다 로직을 짜줘야 한다.
* 그럼 이러한 핵심 로직이 아닌 곁도는 로직들을 한꺼번에 관리하기 위해서 필요한 부분이 AOP이고,
* 우리는 위와 같은 문제들을 AOP클래스를 구현함으로 해결했다.
*
* 문제
회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
시간을 측정하는 로직은 공통 관심 사항이다.
시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.
*
*
* 해결
회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
핵심 관심 사항을 깔끔하게 유지할 수 있다.
변경이 필요하면 이 로직만 변경하면 된다.
원하는 적용 대상을 선택할 수 있다.
*
* pdf 63 64 보기 (AOP 작동 원리, 프록시 사용)
* */