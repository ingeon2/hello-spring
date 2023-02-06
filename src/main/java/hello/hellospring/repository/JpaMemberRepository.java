package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    
    public final EntityManager em; //jpa 관리자
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    //여기 아래 매서드들은 EntityManager 매서드 사용할테니 헷갈릴 수 있음
    
    @Override
    public Member save(Member member) {
        em.persist(member); //이거 하나면 jpa가 inser 쿼리만들어서 다 집어넣어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // 파라미터로 쓰고싶은 변수앞에 :를 붙임(위 name) 그리고 해당 변수에 값을 setParameter로 넣음.
                .getResultList();

        return result.stream()
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //원래 쿼리문은 select *, 여기는 다름
                .getResultList();
    }
}
