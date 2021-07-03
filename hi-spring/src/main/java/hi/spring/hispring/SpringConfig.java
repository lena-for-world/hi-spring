package hi.spring.hispring;

import hi.spring.hispring.repository.MemberRepository;
import hi.spring.hispring.repository.MemoryMemberRepository;
import hi.spring.hispring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // Component 수동등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}