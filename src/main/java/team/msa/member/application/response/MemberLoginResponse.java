package team.msa.member.application.response;

import lombok.*;


@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponse {

    private int memberId; // 생성된 회원 고유번호
    private String accessToken;
    private String refreshToken;

}
