package team.msa.member.presentation.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.infrastructure.config.WebFluxRouterConfig;

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

        webTestClient
            .post()
            .uri("/admin/teacherRegistration")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(MemberBlahBlahResponse.class);
    }
}