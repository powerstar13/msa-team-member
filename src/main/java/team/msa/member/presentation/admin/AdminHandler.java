package team.msa.member.presentation.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.msa.member.application.member.MemberApplicationService;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.domain.model.member.MemberType;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class AdminHandler {

    private final MemberApplicationService memberApplicationService;

    /**
     * 강사 등록
     * --> '사이트 운영자'는 강의 컨테츠를 업로드할 '강사' 회원을 생성할 수 있다.
     * @param request : 등록할 강사 정보
     * @return Mono<ServerResponse> : 등록된 강사 정보
     */
    public Mono<ServerResponse> teacherRegistration(ServerRequest request) {

        Mono<MemberRegistrationResponse> response = memberApplicationService.memberRegistration(request, MemberType.TEACHER);

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response, MemberRegistrationResponse.class);
    }
}
