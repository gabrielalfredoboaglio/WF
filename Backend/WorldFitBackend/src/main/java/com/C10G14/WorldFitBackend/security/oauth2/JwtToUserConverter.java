package com.C10G14.WorldFitBackend.security.oauth2;


import com.C10G14.WorldFitBackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;


@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private User user;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
    }
}
