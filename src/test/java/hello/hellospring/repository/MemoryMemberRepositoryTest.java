package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ //하나하나 아래의 매서드 끝날때마다 저장소(store)를 초기화해줘야지 각각의 매서드가 서로 간섭을 안함.
                             //얘 없으면 에러뜬다~
        repository.clearStore();
    }

    @Test //위에 주피터 임포트함.
    public void save(){
        Member member = new Member();
        member.setName("spring"); //멤버 객체 생성해서

        repository.save(member); //저장한놈이랑

        Member result = repository.findById(member.getId()).get(); //저장한놈에서 빼온놈이랑
        assertThat(member).isEqualTo(result); //어설션으로 같은지 보기. (메모리 주소같은걸로 보겠지), 위에 Assertions 임포트함.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //멤버객체 두개 만들고

        Member result = repository.findByName("spring1").get(); //findByName매서드 실행 후
        assertThat(result).isEqualTo(member1); //잘 되었는지는 asserThat 통해 검사
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //멤버객체 두개 만들고 넣어주기 (save)

        List<Member> result = repository.findAll();
        //repository.findAll(); 만 작성하고 컨트롤 알트 v 단축키로 변수생성가능

        assertThat(result.size()).isEqualTo(2);
    }


}
