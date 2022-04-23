package team.msa.member.infrastructure.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import team.msa.member.domain.model.member.MemberFactory;
import team.msa.member.domain.model.member.MemberRepository;

import java.time.Duration;

/**
 * Spring Data R2DBC를 사용하는 방법에는 Spring Data에서 지원하는 Repository를 이용하는 방법과 R2dbcEntityTemplate을 이용하는 방법이 있다.
 * 이번 예제에서는 Repository를 이용할 것이기 때문에 @EnableR2dbcRepositories 어노테이션을 추가한다.
 */
@Slf4j
@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    private final MemberFactory memberFactory;

    /**
     * ConnectionFactory는 application.yml에서 설정값으로 생성하기 때문에 생략한다.
     * @return ConnectionFactory
     */
    @Override
    public ConnectionFactory connectionFactory() { return null; }

    /**
     * 초기 사이트 운영자 데이터 세팅
     * @param memberRepository : member 레포티토리
     * @return CommandLineRunner : 명령 실행
     */
    @Bean
    public CommandLineRunner setUp(MemberRepository memberRepository) {

        return args -> {
            log.info("===== Member Data setUp START =====");
            // Member 정보 저장
            memberRepository.saveAll(memberFactory.adminSetUpListBuilder())
                .blockLast(Duration.ofSeconds(10)); // 10초가 만료될 때까지 차단한다.

            log.info("===== Member Data setUp Completed INFO =====");
            memberRepository.findAll()
                .doOnNext(person -> log.info(person.toString()))
                .blockLast(Duration.ofSeconds(10));

            log.info("===== Member Data setUp END =====");
        };
    }
}
