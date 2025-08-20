package org.myapp.chat_api.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.myapp.chat_api.models.dto.chat.ChatDto;
import org.myapp.chat_api.models.dto.message.CreateMessageDto;
import org.myapp.chat_api.models.dto.message.MessageDto;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.chat.Chat;
import org.myapp.chat_api.models.entity.chat.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);


        modelMapper.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {

                LocalDate parse = LocalDate
                        .parse(mappingContext.getSource(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                return parse;
            }
        });
        
        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
                LocalDateTime parse = LocalDateTime.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return parse;
            }
        });

        modelMapper.addConverter(new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(MappingContext<String, LocalTime> mappingContext) {
                LocalTime parse = LocalTime.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("HH:mm:ss"));
                return parse;
            }
        });

        modelMapper.typeMap(Chat.class, ChatDto.class).addMappings(mapper -> {
            mapper.map(Chat::getConversationId, ChatDto::setId);
            /*mapper.map(src -> {
                if (src.getParticipants() == null) return 0;
                return src.getParticipants().size();
            } ,ChatDto::setParticipantsSize);*/
            mapper.using(ctx -> {
                Set<User> participants = (Set<User>) ctx.getSource();
                return participants == null ? 0 : participants.size();
            }).map(Chat::getParticipants, ChatDto::setParticipantsSize);
        });

        modelMapper.typeMap(ChatDto.class, Chat.class).addMappings(mapper -> {
            mapper.map(ChatDto::getId, Chat::setConversationId);
        });

        /*modelMapper.typeMap(Message.class, MessageDto.class).addMappings(mapper -> {
            mapper.map(Message::, MessageDto::setSenderUsername);
        });*/

        /*modelMapper.addMappings(new PropertyMap<CreateMessageDto, Message>() {
            @Override
            protected void configure() {
                skip(source.getConversationId());
            }
        });*/

        return modelMapper;
    }

    @Bean
    public ShortUuidGenerator shortUuidGenerator() {
        return new ShortUuidGenerator();
    }

    public static class ShortUuidGenerator {
        public String generate() {
            UUID uuid = UUID.randomUUID();
            ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uuid.getMostSignificantBits());
            buffer.putLong(uuid.getLeastSignificantBits());
            String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.array());
            return encoded.substring(0, 11);
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
