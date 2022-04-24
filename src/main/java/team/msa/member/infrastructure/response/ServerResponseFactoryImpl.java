package team.msa.member.infrastructure.response;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.msa.member.presentation.shared.response.ServerResponseFactory;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ServerResponseFactoryImpl implements ServerResponseFactory {

    /**
     * Success response without body
     * @return Mono<ServerResponse> : body 없이 성공만 반환
     */
    @Override
    public Mono<ServerResponse> successOnly() {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .build();
    }

    /**
     * Success response with body (shortcut)
     * @param response : 반환할 값
     * @return Mono<ServerResponse> : body 정보 반환
     */
    @Override
    public Mono<ServerResponse> successBodyValue(Object response) {

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(response);
    }
}
