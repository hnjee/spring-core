package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // 1. ApplicationContext: 스프링 컨테이너 인터페이스
        //  - AnnotationConfigApplicationContext: 구현 클래스
        //  - AppConfig.class: 미리 설정해놓은 구성 정보
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        // 2. getBean(): 스프링 컨테이너에 등록된 bean 가져오기
        MemberService memberService
                = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}