package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /*
    private EntityManager em;
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    여기는 jpa에서 springdatajpa 넘어가면서 필요없어진것 springdatajpa는 위처럼 구현
    */

    /*
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    여기는 아래에 jpa 사용하면서 데이터소스필요없어서 주석처리. jpa는 위의 EntityManager 필요.
    */

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return timeTraceAop();
    }
    얘는 AOP만들어놓은것 애너테이션 @Aspect 빼고 다 지우려면 사용.
    */

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();  교체해준것 내 컴퓨터 메모리에서 데이터베이스(jdbc)로 (pdf 46페이지)
//        return new JdbcMemberRepository(dataSource); 교체해준것 그냥 jdbc에서 jdbctemplate로
//        return new JdbcTemplateMemberRepository(dataSource); 얘는 jpa 사용할거라서 밀려났어
//        return new JpaMemberRepository(em); 얘는 SpringDataJpaMemberRepository 사용하느라 마지막에 밀려났고
//    }
}
