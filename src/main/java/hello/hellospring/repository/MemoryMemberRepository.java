package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    //인터페이스, 추상매서드 구현

    private static Map<Long, Member> store = new HashMap<Long, Member>(); //전체 멤버 넣어줄 Map
    private static long sequence = 0L; //숫자 커가면서 식별해줄 id값
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //옵셔널로 null 일경우 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values()/*밸류스 여기까지 하면 Collection<Member>이 된다. 이 이후에 스트림 갈기는것*/.stream()
                .filter(member -> member.getName().equals(name)) //Collection<Member> 의 member 하나에서 매개변수 name과 같은걸로 필터해서
                .findAny(); //그거 암거나 찾아라
    }

    @Override
    public List<Member> findAll() { //store은 Map 리턴값은 List
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
    
}
//이제 테스트케이스 작성 → test의 repository 패키지안의 MemoryMemberRepositoryTest 클래스에서 테스트해보자!!