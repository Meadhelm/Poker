package com.nogo.poker.user.web;

import org.hibernate.validator.constraints.NotEmpty;

import com.nogo.poker.user.domain.IDomainObjectAware;
import com.nogo.poker.user.domain.User;

public class UserDto implements IDomainObjectAware<User>
{
    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
    private String createdDate;
    private String modifiedDate;

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(final String createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getModifiedDate()
    {
        return modifiedDate;
    }

    public void setModifiedDate(final String modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public User toDomainObject()
    {
        return new User.Builder().withName(name).withEmail(email).withCreatedDate(createdDate)
                .withModifiedDate(modifiedDate).build();
    }

    public UserDto()
    {

    }

    public UserDto(final User user)
    {
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate() == null ? null : user.getCreatedDate().toString();
        this.modifiedDate = user.getModifiedDate() == null ? null : user.getModifiedDate().toString();
    }
}
