package team.msa.member.infrastructure.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import team.msa.member.domain.model.member.Member;
import team.msa.member.domain.model.member.MemberType;
import team.msa.member.infrastructure.jwt.JwtProvider;

@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Value("${jwt.refreshExpires}")
    private String refreshExpires;

    @Test
    void generateToken() {
        Member member = Member.builder()
                .memberId(1)
                .memberName("memberName")
                .memberPassword("memberPassword")
                .memberType(MemberType.ADMIN)
                .build();
        long accessExpires = Long.parseLong(refreshExpires);

        String token = jwtProvider.createJwtToken(member, accessExpires);
        System.out.println(token);

    }


}
