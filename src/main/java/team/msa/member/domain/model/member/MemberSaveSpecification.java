package team.msa.member.domain.model.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import team.msa.member.application.member.MemberSha256;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.infrastructure.exception.status.AlreadyDataException;
import team.msa.member.infrastructure.exception.status.ExceptionMessage;
import team.msa.member.infrastructure.exception.status.RegistrationFailException;
import team.msa.member.presentation.member.request.MemberRegistrationRequest;

@Component
@RequiredArgsConstructor
public class MemberSaveSpecification {

    private final MemberRepository memberRepository;
    private final MemberFactory memberFactory;

    /**
     * 회원 중복 검사 및 계정 생성
     * @param request : 저장할 회원 정보
     * @param memberType : 저장할 회원 유형
     * @return Mono<MemberRegistrationResponse> : 저장된 회원 정보
     */
    public Mono<MemberRegistrationResponse> memberExistCheckAndRegistration(MemberRegistrationRequest request, MemberType memberType) {

        return memberRepository.findByMemberNameAndMemberType(request.getMemberName(), memberType)
            .hasElement()
            .flatMap(alreadyMember -> {

                if (alreadyMember) return Mono.error(new AlreadyDataException(ExceptionMessage.AlreadyDataMember.getMessage()));

                return this.memberRegistration(request, memberType)
                    .flatMap(savedMember -> Mono.just(
                        MemberRegistrationResponse.builder()
                            .memberId(savedMember.getMemberId())
                            .build()
                    ));
            }
        );
    }

    /**
     * 회원 계정 생성
     * @param request : 저장할 회원 정보
     * @param memberType : 저장할 회원 유형
     * @return Mono<Member> : 저장된 회원 정보
     */
    private Mono<Member> memberRegistration(MemberRegistrationRequest request, MemberType memberType) {

        return memberRepository.save(
            memberFactory.memberBuilder(
                request.getMemberName(),
                MemberSha256.encrypt(request.getMemberPassword()),
                memberType
            )
        ).switchIfEmpty(Mono.error(new RegistrationFailException(ExceptionMessage.SaveFailMember.getMessage())));
    }
}
