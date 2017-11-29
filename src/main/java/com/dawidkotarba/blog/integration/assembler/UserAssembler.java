package com.dawidkotarba.blog.integration.assembler;

import com.dawidkotarba.blog.integration.dto.UserOutDto;
import com.dawidkotarba.blog.model.entities.UserEntity;

import javax.inject.Named;
import java.util.function.Function;

/**
 * Created by Zakochani on 11.05.2016.
 */

@Named
public class UserAssembler {

    public Function<UserEntity, UserOutDto> convert() {
        return userEntity -> {
            UserOutDto outDto = new UserOutDto();
            outDto.setUserId(userEntity.getId());
            outDto.setUsername(userEntity.getUsername());
            outDto.setEnabled(userEntity.isEnabled());
            outDto.setRole(userEntity.getRole());
            return outDto;
        };

    }
}