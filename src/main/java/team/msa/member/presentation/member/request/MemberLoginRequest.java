package team.msa.member.presentation.member.request;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import team.msa.member.infrastructure.exception.status.BadRequestException;
import team.msa.member.presentation.shared.request.RequestVerify;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest implements RequestVerify {

    private String memberName; // 회원 이름

    private String memberPassword; // 회원 비밀번호

    @Override
    public void verify() {

        if (StringUtils.isBlank(memberName)) throw new BadRequestException("이름을 입력해 주세요.");
        if (StringUtils.isBlank(memberPassword)) throw new BadRequestException("비밀번호를 입력해 주세요.");
    }
}
