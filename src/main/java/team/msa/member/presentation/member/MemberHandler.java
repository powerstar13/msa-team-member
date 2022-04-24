package team.msa.member.presentation.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.msa.member.application.member.MemberApplicationService;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.presentation.shared.response.ServerResponseFactory;

@Component
@RequiredArgsConstructor
public class MemberHandler {

    private final ServerResponseFactory serverResponseFactory;
    private final MemberApplicationService memberApplicationService;

    /**
     * 강사 등록
     * --> '사이트 운영자'는 강의 컨테츠를 업로드할 '강사' 회원을 생성할 수 있다.
     * @param request : 등록할 강사 정보
     * @return Mono<ServerResponse> : 등록된 강사 정보
     */
    public Mono<ServerResponse> teacherRegistration(ServerRequest request) {

        MemberBlahBlahResponse response = memberApplicationService.teacherRegistration(request);

        return serverResponseFactory.successBodyValue(response);
    }

    /**
     * 학생 회원 가입
     * --> 강의를 수강하고자 하는 사람은 '학생'으로 회원 가입이 가능하다.
     * @param request : 가입할 학생 정보
     * @return Mono<ServerResponse> : 등록된 학생 회원 정보
     */
    public Mono<ServerResponse> studentRegistration(ServerRequest request) {

        MemberBlahBlahResponse response = memberApplicationService.studentRegistration(request);

        return serverResponseFactory.successBodyValue(response);
    }

    /**
     * 로그인
     * @param request : 로그인 정보
     * @return Mono<ServerResponse> : 권한 인증 정보
     */
    public Mono<ServerResponse> login(ServerRequest request) {

        MemberBlahBlahResponse response = memberApplicationService.login(request);

        return serverResponseFactory.successBodyValue(response);
    }

    /**
     * 회원 정보 조회
     * @param request : 조회할 회원 정보
     * @return Mono<ServerResponse> : 조회된 회원 정보
     */
    public Mono<ServerResponse> findMemberInfo(ServerRequest request) {

        MemberBlahBlahResponse response = memberApplicationService.findMemberInfo(request);

        return serverResponseFactory.successBodyValue(response);
    }

}
