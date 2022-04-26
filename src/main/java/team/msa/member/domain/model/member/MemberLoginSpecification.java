package team.msa.member.domain.model.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import team.msa.member.application.member.MemberSha256;
import team.msa.member.application.response.MemberLoginResponse;
import team.msa.member.infrastructure.exception.status.ExceptionMessage;
import team.msa.member.infrastructure.exception.status.UnauthorizedException;
import team.msa.member.infrastructure.jwt.JwtProvider;
import team.msa.member.presentation.member.request.MemberLoginRequest;

@Component
@RequiredArgsConstructor
public class MemberLoginSpecification {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Value("${jwt.accessExpires}")
    private String accessExpiresString;

    @Value("${jwt.refreshExpires}")
    private String refreshExpiresString;

    public Mono<MemberLoginResponse> memberExistCheckAndLogin(MemberLoginRequest request) {

        Mono<Member> member = memberRepository.findByMemberNameAndMemberPassword(request.getMemberName(), MemberSha256.encrypt(request.getMemberPassword()));
        return member
                .hasElement()
                .flatMap(hasMember -> {
                    if (!hasMember) return Mono.error(new UnauthorizedException(ExceptionMessage.NotFoundLoginMember.getMessage()));

                    return MakeMemberLoginResponse(member);
                });
    }

    private Mono<MemberLoginResponse> MakeMemberLoginResponse(Mono<Member> member) {
        return member.flatMap(m -> {
            String accessToken = jwtProvider.createJwtToken(m, Long.parseLong(accessExpiresString));
            String refreshToken = jwtProvider.createJwtToken(m, Long.parseLong(refreshExpiresString));
            return Mono.just(new MemberLoginResponse(m.getMemberId(), accessToken, refreshToken));
        });
    }
}
