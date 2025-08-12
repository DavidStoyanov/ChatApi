package org.myapp.chat_api.component;

import org.myapp.chat_api.config.ApplicationBeanConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratorAccessor {
    private static ApplicationBeanConfiguration.ShortUuidGenerator generator;

    @Autowired
    public GeneratorAccessor(ApplicationBeanConfiguration.ShortUuidGenerator injectedGenerator) {
        generator = injectedGenerator;
    }

    public static ApplicationBeanConfiguration.ShortUuidGenerator getGenerator() {
        return generator;
    }
}