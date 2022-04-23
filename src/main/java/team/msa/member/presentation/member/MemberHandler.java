package team.msa.member.presentation.member;

import lombok.RequiredArgsConstructor;
import team.msa.member.application.member.MemberApplicationService;
import team.msa.member.domain.model.member.Member;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MemberHandler {

    private final MemberApplicationService memberApplicationService;

    // 회원가입
    public Mono<ServerResponse> signUp(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(Map.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("user SingUp data required")))
                .map(memberApplicationService::SignUp)
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 로그인
    public Mono<ServerResponse> login(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(Map.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("user Login data required")))
                .map(memberApplicationService::Login)
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 강사 생성 (관리자만)
    public Mono<ServerResponse> setUpTeacher(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(Map.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("setUp Lecture data required")))
                .map(memberApplicationService::SetUpLecturer)
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

    // 회원정보 수정
    public Mono<ServerResponse> editMemberInfo(ServerRequest request) {

        Mono<Member> member = request.bodyToMono(String.class)
                .publishOn(Schedulers.parallel())
                .switchIfEmpty(Mono.error(new IllegalStateException("setUp Lecture data required")))
                .map(memberApplicationService::EditMemberInfo)
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
                .map(memberApplicationService::GetMemberInfo)
                ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(member, Member.class);
    }

}
