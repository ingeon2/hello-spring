package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { //의존성 주입(같은 MemoryMemberRepository 사용) + 각 테스트 시작 전마다 실행
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){ //하나하나 아래의 매서드 끝날때마다 저장소(store)를 초기화해줘야지 각각의 매서드가 서로 간섭을 안함.
        //얘 없으면 에러뜬다~
        memberRepository.clearStore();
    }
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