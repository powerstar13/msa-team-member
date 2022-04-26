package team.msa.member.presentation.shared.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessResponse {

    private final int rt = 200;
    private final String rtMsg = "SUCCESS";
}
