package team.msa.member.presentation.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.infrastructure.config.WebFluxRouterConfig;
import team.msa.member.presentation.admin.request.MemberRegistrationRequest;

@SpringBootTest
class AdminHandlerTest {

    private WebTestClient webTestClient;

    @Autowired
    private WebFluxRouterConfig webFluxRouterConfig;
    @Autowired
    private AdminHandler adminHandler;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
            .bindToRouterFunction( // WebFluxConfig에서 작성한 router를 WebTestClient에 바인딩해준다.
                webFluxRouterConfig.adminRouterBuilder(adminHandler)
            )
            .build();
    }

    /**
     * 강사 등록
     */
    @Test
    void teacherRegistration() {

        MemberRegistrationRequest request = MemberRegistrationRequest.builder()
            .memberName("홍강사")
            .memberPassword("1234")
            .build();

        webTestClient
            .post()
            .uri("/admin/teacherRegistration")
            .bodyValue(request)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(MemberRegistrationResponse.class)
            .value(memberRegistrationResponse -> {
                Assertions.assertInstanceOf(Integer.class, memberRegistrationResponse.getMemberId());
            });
    }
}