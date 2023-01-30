package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService { //이전에만든 repository(고객으로 수행하는 매서드 등)와 domain(고객객체 등)을 이용해 비즈니스 로직 작성

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) { //DI 의존성 주입(생성자 주입) 위해
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은이름 중복회원 X 로직, 통과하면 아래 save
        validateDuplicateMember(member);

        //그냥 회원가입 로직
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //여기가 꺼내는 매서드
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
