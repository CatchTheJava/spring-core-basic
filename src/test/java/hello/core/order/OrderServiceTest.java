package hello.core.order;

import hello.core.*;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, discountPolicy);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberRepository.save(member);

        Order order = orderService.createOrder(1L, "itemA", 20000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
