package team.msa.member.handler;

import team.msa.member.service.MemberService;
import team.msa.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Component
public class MemberHandler {

    @Autowired
    MemberService memberService;

    // 회원가입
    public Mono<ServerResponse> signUpHandler(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(Map.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("user SingUp data required")))
                .map(data -> memberService.SignUp(data))
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 로그인
    public Mono<ServerResponse> loginHandler(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(Map.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("user Login data required")))
                .map(data -> memberService.Login(data))
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 강사 생성 (관리자만)
    public Mono<ServerResponse> setUpLectureHandler(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(Map.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("setUp Lecture data required")))
                .map(data -> memberService.SetUpLecturer(data))
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 회원정보 수정
    public Mono<ServerResponse> editMemberInfoHandler(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(String.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("setUp Lecture data required")))
                .map(data -> memberService.EditMemberInfo(data))
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 회원정보 제공
    public Mono<ServerResponse> getMemberInfoHandler(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(String.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("setUp Lecture data required")))
                .map(data -> memberService.GetMemberInfo(data))
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }


}
