package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //인터페이스니까 추상매서드만 구현
    Member save(Member member);
    Optional<Member> findById(Long id); //옵셔널은 리턴값이 null일때 오류없이 감싸서 내보내줄 수 있는 역할
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
