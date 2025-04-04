package spring.core.beanfind.ApplicationContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Bean 이름, 타입으로 조회")
        //getBean(Bean 이름, 인터페이스 타입)
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Bean 이름, 구체 타입으로 조회")
        //getBean(Bean 이름, 구현 클래스 타입)
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Bean 이름 없이 타입으로만 조회")
        //getBean(타입)
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Bean 이름으로 조회 실패")
    //조회 대상 스프링 빈이 없으면 예외 발생 -> No such Bean
    void findBeanByNameX() {
        //MemberService memberService = ac.getBean("XXX", MemberServiceImpl.class);
        //assertThat(memberService).isInstanceOf(MemberService.class);

        //예외처리
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("XXX", MemberServiceImpl.class));
    }
}
