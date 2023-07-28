package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setUsername("kim");

        Long memberId = memberService.join(member);

        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(memberId));
    }

    @Test
    public void 중복회원_예() {
        Member kim1 = new Member();
        kim1.setUsername("kim");
        Member kim2 = new Member();
        kim2.setUsername("kim");

        memberService.join(kim1);
        assertThrows(IllegalStateException.class, () -> memberService.join(kim2));
    }

}