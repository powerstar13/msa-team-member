package team.msa.member.presentation.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.infrastructure.config.WebFluxRouterConfig;

@SpringBootTest
class MemberHandlerTest {

    private WebTestClient webTestClient;

    @Autowired
    private WebFluxRouterConfig webFluxRouterConfig;
    @Autowired
    private MemberHandler memberHandler;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
            .bindToRouterFunction( // WebFluxConfig에서 작성한 router를 WebTestClient에 바인딩해준다.
                webFluxRouterConfig.memberRouterPostBuilder(memberHandler)
            )
            .build();
    }

    /**
     * 강사 등록
     */
    @Test
    void teacherRegistration() {

        webTestClient
            .post()
            .uri("/member/teacherRegistration")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(MemberBlahBlahResponse.class);
    }

    /**
     * 학생 회원 등록
     */
    @Test
    void studentRegistration() {

        webTestClient
            .post()
            .uri("/member/studentRegistration")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(MemberBlahBlahResponse.class);
    }

    /**
     * 로그인
     */
    @Test
    void login() {

        webTestClient
            .post()
            .uri("/member/login")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(MemberBlahBlahResponse.class);
    }

    /**
     * 회원 정보 조회
     */
    @Test
    void findMemberInfo() {

        webTestClient
            .get()
            .uri("/member/findMemberInfo")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(MemberBlahBlahResponse.class);
    }
}