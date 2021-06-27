package hi.spring.hispring.repository;

import hi.spring.hispring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        // 테스트 코드에서 새로 멤버를 만들고 리포지토리에 넣을 때마다
        // 저장된다. 테스트 함수는 순서없이 실행되기 때문에
        // 순서에 영향을 받게 작성하면 안 된다.
        // 그래서 매 테스트가 끝날 때마다 리포지토리를 비워주기 위한 코드를
        // afterEach에 넣어주었다.
        repository.clear();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertEquals(member, result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member member2 = new Member();
        member2.setName("emer");
        repository.save(member2);
        Member result = repository.findByName("spring").get();
        Assertions.assertThat(result).isEqualTo(member);
    }
}
