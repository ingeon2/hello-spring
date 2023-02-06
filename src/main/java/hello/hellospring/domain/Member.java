package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa가 관리하는 엔티티구나!
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //우리가 사용하는 식별자 id 전략으로 지정한다
    // (영한님이 일부러 jpa 인터페이스에 id가 있다는 것까지 내다보고) 가장 처음부터 id라는 인스턴스변수 생선한거임
    //이러한 어노테이션으로 jpa에서 인식해서 다뤄주는것
    private Long id; //long Long 차이 - 쉽게 말해서 null가능(Long이)
    
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
