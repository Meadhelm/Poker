package com.nogo.poker.user.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserService
{
    @Autowired
    private Validator validator;

    public String createUser(final User newUser)
    {
        final String violations = validator.validate(newUser);
        if (StringUtils.isNotBlank(violations))
        {
            throw new IllegalArgumentException(violations);
        }
        return "1";
    }
}
