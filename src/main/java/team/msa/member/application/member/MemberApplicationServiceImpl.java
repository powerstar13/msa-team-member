package team.msa.member.application.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.domain.model.member.Member;
import team.msa.member.domain.model.member.MemberFactory;
import team.msa.member.domain.model.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final MemberRepository memberRepository;
    private final MemberFactory memberFactory;

    private void validateDuplicateMember(Member member) {
        //Exception
        Mono<Boolean> duplicatYn = memberRepository.existsById(member.getMemberId());
        duplicatYn.flatMap(isExist -> {
            if(isExist){
                Mono.error(new IllegalStateException("duplicate member!!"));
            }
            return Mono.just(isExist);
        });
    }

    @Override
    public MemberBlahBlahResponse teacherRegistration(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }

    @Override
    public MemberBlahBlahResponse studentRegistration(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }

    @Override
    public MemberBlahBlahResponse login(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }

    @Override
    public MemberBlahBlahResponse findMemberInfo(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }
}
