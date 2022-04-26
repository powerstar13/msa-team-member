package team.msa.member.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import team.msa.member.presentation.member.MemberHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux // WebFlux 설정 활성화
public class WebFluxRouterConfig implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> memberRouterBuilder(MemberHandler memberHandler) {

        return RouterFunctions.route()
            .path("/member", memberBuilder ->
                memberBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                    builder
                        .POST("/admin/teacherRegistration", memberHandler::memberRegistration) // 강사 등록 (관리자만)
                        .POST("/studentRegistration", memberHandler::memberRegistration) // 학생 회원 가입
                        .POST("/login", memberHandler::login) // 로그인
                )
            )
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> memberRouterGETBuilder(MemberHandler memberHandler) {
        return RouterFunctions.route()
            .path("/member", builder -> builder
                .GET("/findMemberInfo/{memberId}", memberHandler::findMemberInfo)
            ).build();
    }

}
