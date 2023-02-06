package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //다중상속
    //SpringDataJpa 사용해서 서비스 구현 위해 만든 인터페이스(jpa를 사용하기 편하게 만드는것)

    @Override
    Optional<Member> findByName(String name);
    //SpringDataJpa가 매서드구현없이 지알아서 인터페이스 단계에서 클래스 없이 구현해줌
    
    //우리가 각각의 클래스에 맞게 만들었었던 MemberRepository의 매서드들은 기본적으로 
    //SpringDataJpa에 있음 (파인드바이아이디, 파인드올, 세이브 등등)
    
    //근데 위와 같이 제공되는 매서드 이외는..?
    //findByName 이름을 입력받으면, JPQL select m from Member m where m.name = ? 이런식으로 짜줌.
    //그러니 인터페이스에없어도 매서드 이름만 맞춰주면 가능.. 실로 획기적 (인터페이스 이름으로도 개발이 끝나버림..)
    //findByNameAnd findByNameOr 이런식으로 and or도 사용가능

    /*
    스프링 데이터 JPA 제공 기능
    인터페이스를 통한 기본적인 CRUD (Create, Read, Update, Delete)
    findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공
    페이징 기능 자동 제공
    */
}
