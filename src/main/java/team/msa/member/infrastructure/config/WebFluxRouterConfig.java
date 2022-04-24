package team.msa.member.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import team.msa.member.presentation.member.AdminHandler;
import team.msa.member.presentation.member.MemberHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux // WebFlux 설정 활성화
public class WebFluxRouterConfig implements WebFluxConfigurer {

    /**
     * 사이트 운영자 전용 Router
     * @param adminHandler : 관리자
     * @return RouterFunction<ServerResponse> : 빌드된 Router
     */
    @Bean
    public RouterFunction<ServerResponse> adminRouterBuilder(AdminHandler adminHandler) {

        return RouterFunctions.route()
            .path("/admin", adminBuilder ->
                adminBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                    builder
                        .POST("/teacherRegistration", adminHandler::teacherRegistration) // 강사 등록 (관리자만)
                )
            )
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> memberRouterBuilder(MemberHandler memberHandler) {

        return RouterFunctions.route()
            .path("/member", memberBuilder ->
                memberBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                    builder
                        .POST("/studentRegistration", memberHandler::studentRegistration) // 학생 회원 가입
                        .POST("/login", memberHandler::login) // 로그인
                        .GET("/findMemberInfo", memberHandler::findMemberInfo) // 회원 정보 조회
                )
            )
            .build();
    }
}
