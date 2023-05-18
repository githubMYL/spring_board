package org.myexam.configs;

import lombok.RequiredArgsConstructor;
import org.myexam.commons.MemberUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AwareAuditorImpl implements AuditorAware<String> {

    private final MemberUtil memberUtil;
    @Override
    public Optional<String> getCurrentAuditor() {

        String userId= memberUtil.isLogin() ? memberUtil.getMember().getUserId() : null;

        return Optional.ofNullable(userId); // Null 값을 허용한다는 것 NullPointException 을 처리하기 위해 사용
    }
}
