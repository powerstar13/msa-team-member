package team.msa.member.application.response;

import lombok.*;
import team.msa.member.presentation.shared.response.SuccessResponse;


@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponse extends SuccessResponse {

    private int memberId; // 생성된 회원 고유번호
    private String accessToken;
    private String refreshToken;

}
