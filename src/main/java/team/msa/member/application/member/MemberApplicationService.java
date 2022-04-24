package team.msa.member.application.member;

import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.application.response.MemberInfoResponse;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.domain.model.member.MemberType;

public interface MemberApplicationService {

    Mono<MemberRegistrationResponse> memberRegistration(ServerRequest request, MemberType memberType); // 회원 계정 생성

    Mono<MemberBlahBlahResponse> login(ServerRequest request); // 로그인

    Mono<MemberInfoResponse> findMemberInfo(ServerRequest request); // 회원 정보 조회

}