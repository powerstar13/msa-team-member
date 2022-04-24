package team.msa.member.domain.model.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import team.msa.member.application.response.MemberRegistrationResponse;
import team.msa.member.infrastructure.exception.status.AlreadyDataException;
import team.msa.member.infrastructure.exception.status.RegistrationFailException;
import team.msa.member.presentation.admin.request.MemberRegistrationRequest;

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

                if (alreadyMember) return Mono.error(new AlreadyDataException("이미 존재하는 회원입니다."));

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
                request.getMemberPassword(),
                memberType
            )
        ).switchIfEmpty(Mono.error(new RegistrationFailException("회원 가입에 실패했습니다. 관리자에게 문의 바랍니다.")));
    }
}
