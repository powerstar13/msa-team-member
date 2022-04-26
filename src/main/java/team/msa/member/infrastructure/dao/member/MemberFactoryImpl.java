package team.msa.member.infrastructure.dao.member;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import team.msa.member.domain.model.member.Member;
import team.msa.member.domain.model.member.MemberFactory;
import team.msa.member.domain.model.member.MemberType;
import team.msa.member.infrastructure.exception.status.BadRequestException;
import team.msa.member.infrastructure.exception.status.ExceptionMessage;

import java.util.Arrays;
import java.util.List;

@Component
public class MemberFactoryImpl implements MemberFactory {

    /**
     * 회원 정보 세팅
     * @param memberName : 이름
     * @param memberPassword : 비밀번호
     * @param memberType : 회원 유형
     * @return Member : 회원 정보
     */
    @Override
    public Member memberBuilder(String memberName, String memberPassword, MemberType memberType) {

        if (StringUtils.isBlank(memberName)) throw new BadRequestException(ExceptionMessage.IsRequiredMemberName.getMessage());
        if (StringUtils.isBlank(memberPassword)) throw new BadRequestException(ExceptionMessage.IsRequiredMemberPassword.getMessage());
        if (memberType == null) throw new BadRequestException(ExceptionMessage.IsRequiredMemberType.getMessage());

        return Member.builder()
            .memberName(memberName)
            .memberPassword(memberPassword)
            .memberType(memberType)
            .build();
    }

    /**
     * 초기 사이트 운영자 데이터 세팅
     * @return List<Member> : 운영자 목록
     */
    @Override
    public List<Member> adminSetUpListBuilder() {

        return Arrays.asList(
            this.memberBuilder("홍준성", "msa", MemberType.ADMIN),
            this.memberBuilder("유하얀", "msa", MemberType.ADMIN),
            this.memberBuilder("이휘수", "msa", MemberType.ADMIN),
            this.memberBuilder("배소현", "msa", MemberType.ADMIN),
            this.memberBuilder("박철훈", "msa", MemberType.ADMIN),
            this.memberBuilder("윤해서", "msa", MemberType.ADMIN),
            this.memberBuilder("심재현", "msa", MemberType.ADMIN),
            this.memberBuilder("김영수", "msa", MemberType.ADMIN),
            this.memberBuilder("지경희", "msa", MemberType.ADMIN),
            this.memberBuilder("이성화", "msa", MemberType.ADMIN),
            this.memberBuilder("정승훈", "msa", MemberType.ADMIN),
            this.memberBuilder("설동찬", "msa", MemberType.ADMIN)
        );
    }
}
