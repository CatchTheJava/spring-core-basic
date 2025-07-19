package hello.core.xml;

import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.context.support.*;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
