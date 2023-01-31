package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //스프링빈이 되도록 하는 에너테이션
public class MemberController {
    private final MemberService memberService;

    @Autowired //오토 와이어드가 DI임. 헷갈리면 pdf (컨트롤러 → 서비스 → 리파지토리) 그림 보기
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //바로아래와 차이 알기(겟메핑)
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //바로 위와 차이 알기(포스트매핑)
    public String create(MemberForm form){ //create → 만든다는 뜻
        Member member = new Member();
        member.setName(form.getName());
        //
        memberService.join(member);
        //
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); //리스트로 멤버들 가져와서
        model.addAttribute("members", members); //모델에 List 속성부여 (모델은 html에서 쓸거)
        return "members/memberList";
    }
}
