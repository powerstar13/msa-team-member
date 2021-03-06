package team.msa.member.domain.model.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import team.msa.member.application.response.MemberInfoResponse;

@Component
@RequiredArgsConstructor
public class MemberSearchSpecification {

    private final MemberRepository memberRepository;

    public Mono<MemberInfoResponse> getMemberInfo(Integer memberId) {

        return memberRepository.findById(memberId)
                .map(m -> new MemberInfoResponse( m.getMemberId(), m.getMemberType()));
    }
}
