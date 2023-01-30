package hello.hellospring.domain;

public class Member {
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
