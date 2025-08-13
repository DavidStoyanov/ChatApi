package org.myapp.chat_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfiguration {
    // Create JpaConfig to enable auditing @EnableJpaAuditing
    // (for @CreatedDate, @LastModifiedDate, etc.)
}
