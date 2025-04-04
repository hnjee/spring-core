package spring.core.beanfind.ApplicationContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

import java.util.Map;

public class SameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration
    //같은 타입의 Bean이 두 개 있는 Config Class 생성
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생")
    //NoUniqueBeanDefinitionException: expected single matching bean but found 2
    void findBeanByTypeDuplicate(){
        //MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정해주면 된다")
        //NoUniqueBeanDefinitionException: expected single matching bean but found 2
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입의 bean 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key: "+key+", value: "+beansOfType.get(key));
        }
        System.out.println(beansOfType);
        //org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
}