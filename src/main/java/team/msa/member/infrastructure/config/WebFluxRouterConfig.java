package team.msa.member.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import team.msa.member.presentation.member.MemberHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
@EnableWebFlux // WebFlux 설정 활성화
public class WebFluxRouterConfig implements WebFluxConfigurer {

    private final MemberHandler memberHandler;

    @Bean
    public RouterFunction<ServerResponse> routerBuilder() {

        return RouterFunctions.route()
            .path("/member", memberBuilder ->
                memberBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                    builder
                        .POST("/signup", memberHandler::signUp) // 회원가입
                        .POST("/login", memberHandler::login) // 로그인
                        .POST("/operator/signup/teacher", memberHandler::setUpTeacher) // 강사 생성 (관리자만)
                        .POST("/edit", memberHandler::editMemberInfo) // 회원정보 수정
                        .POST("/getinfo", memberHandler::getMemberInfoHandler) // 회원정보 제공
                )
            )
            .build();
    }
}
