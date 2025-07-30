package hello.core.scan;

import hello.core.*;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
