package team.msa.member.router;

import team.msa.member.handler.MemberHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class MemberRouter {

    MemberHandler memberHandler = new MemberHandler();

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(POST("/member/signup"), memberHandler::signUpHandler)                                // 회원가입
                .andRoute(POST("/member/login"), memberHandler::loginHandler)                               // 로그인
                .andRoute(POST("/member/operator/signup/teacher"), memberHandler::setUpLectureHandler)      // 강사 생성 (관리자만)
                .andRoute(POST("/member/edit"), memberHandler::editMemberInfoHandler)                       // 회원정보 수정
                .andRoute(POST("/member/getinfo"), memberHandler::getMemberInfoHandler)                     // 회원정보 제공
                ;
    }
}
