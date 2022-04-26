package team.msa.member.application.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.application.response.MemberInfoResponse;
import team.msa.member.application.response.MemberLoginResponse;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.domain.model.member.*;
import team.msa.member.infrastructure.exception.status.BadRequestException;
import team.msa.member.infrastructure.exception.status.UnauthorizedException;
import team.msa.member.infrastructure.jwt.JwtProvider;
import team.msa.member.presentation.member.request.MemberLoginRequest;
import team.msa.member.presentation.member.request.MemberRegistrationRequest;

@Service
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final MemberRepository memberRepository;
    private final MemberSaveSpecification memberSaveSpecification;
    private final MemberSearchSpecification memberSearchSpecification;

    private final MemberLoginSpecification memberLoginSpecification;



    /**
     * 회원 계정 생성
     * @param serverRequest : 전달된 Request
     * @param memberType : 회원 유형
     * @return Mono<MemberRegistrationResponse> : 저장된 회원 정보
     */
    @Override
    public Mono<MemberRegistrationResponse> memberRegistration(ServerRequest serverRequest, MemberType memberType) {

        return serverRequest.bodyToMono(MemberRegistrationRequest.class).flatMap(
            request -> {
                request.verify(); // Request 유효성 검사

                return memberSaveSpecification.memberExistCheckAndRegistration(request, memberType); // 회원 계정 생성
            }
        );
    }

    @Override
    public Mono<MemberLoginResponse> login(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(MemberLoginRequest.class).flatMap(
                request -> {
                    request.verify(); // Request 유효성 검사
                    return memberLoginSpecification.memberExistCheckAndLogin(request);
                }
        );
    }


    @Override
    public Mono<MemberInfoResponse> findMemberInfo(ServerRequest request) {

        Integer memberId = Integer.parseInt(request.pathVariable("memberId"));
        return memberSearchSpecification.getMemberInfo(memberId);
    }
}
