package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional //비포이치 애프터이치를 트랜잭셔널 이 대신역할해줌
class MemberServiceIntegrationTest { //얘는 MemberServiceTest 일 때는 자바 안에서 테스트했다면, 
                                     //여기서는 직접 database 건드리면서 스프링 키고 테스트 해주는 것이 차이점

    @Autowired  MemberService memberService;
    @Autowired MemberRepository memberRepository;

    
    @Test
    void 회원가입() {
        //given 뭐가 주어지고
        Member member = new Member();
        member.setName("hello");

        //when 뭐할때 검증되고
        Long saveId = memberService.join(member);

        //then 결과는?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //위는 멤2 쪼인시켯슬때 예외(IllegalStateException)가 터져나와야함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}