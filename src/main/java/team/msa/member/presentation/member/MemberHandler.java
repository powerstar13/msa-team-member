package team.msa.member.presentation.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.msa.member.application.member.MemberApplicationService;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.domain.model.member.MemberType;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class MemberHandler {

    private final MemberApplicationService memberApplicationService;

    /**
     * 학생 회원 가입
     * --> 강의를 수강하고자 하는 사람은 '학생'으로 회원 가입이 가능하다.
     * @param request : 가입할 학생 정보
     * @return Mono<ServerResponse> : 등록된 학생 회원 정보
     */
    public Mono<ServerResponse> studentRegistration(ServerRequest request) {

        Mono<MemberRegistrationResponse> response = memberApplicationService.memberRegistration(request, MemberType.STUDENT);

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response, MemberRegistrationResponse.class);
    }

    /**
     * 로그인
     * @param request : 로그인 정보
     * @return Mono<ServerResponse> : 권한 인증 정보
     */
    public Mono<ServerResponse> login(ServerRequest request) {

        Mono<MemberBlahBlahResponse> response = memberApplicationService.login(request);

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response, MemberBlahBlahResponse.class);
    }

    /**
     * 회원 정보 조회
     * @param request : 조회할 회원 정보
     * @return Mono<ServerResponse> : 조회된 회원 정보
     */
    public Mono<ServerResponse> findMemberInfo(ServerRequest request) {

        Mono<MemberBlahBlahResponse> response = memberApplicationService.findMemberInfo(request);

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response, MemberBlahBlahResponse.class);
    }

}
