package com.usuariosventas.springboot.app.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuariosventas.springboot.app.models.usuarios.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.usuariosventas.springboot.app.config.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Usuario usuario = null;
        Integer rut = null;
        String password = null;
        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            rut = usuario.getRut();
            password = usuario.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(rut,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println(authResult.getPrincipal());
        User usuario = (User) authResult.getPrincipal();
        String rut = String.valueOf(usuario.getUsername());
        Collection<? extends GrantedAuthority> permisos = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add("authorities",new ObjectMapper().writeValueAsString(permisos))
                .add("rut", rut)
                .build();

        String token = Jwts.builder()
                .subject(rut)
                .claims(claims)
                .signWith(SECRET_KEY)
                .expiration(new Date(System.currentTimeMillis() + 3600000)) //Tiempo expiracion token  3600000 = 1 hora
                .issuedAt(new Date())
                .compact();

        response.addHeader(HEADER_AUTHORIZATION,PREFIX_TOKEN + token);

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("rut",rut);
        body.put("message", String.format("Hola %s has iniciado sesion", rut));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticacion rut o password");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }
}
