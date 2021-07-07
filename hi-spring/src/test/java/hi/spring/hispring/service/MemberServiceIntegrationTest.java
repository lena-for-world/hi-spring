package hi.spring.hispring.service;

import hi.spring.hispring.domain.Member;
import hi.spring.hispring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // db에 넣어졌던 데이터가, 재실행 시 rollback되어 db에서 사라진 상태가 되어 중복오류없이 테스트가능
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello!!!!");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        org.junit.jupiter.api.Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("hot");
        Member member2 = new Member();
        member2.setName("hot");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}