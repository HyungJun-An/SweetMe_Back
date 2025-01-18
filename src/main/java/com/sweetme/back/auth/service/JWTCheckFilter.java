package com.sweetme.back.auth.service;

import com.google.gson.Gson;
import com.sweetme.back.auth.domain.User;
import com.sweetme.back.auth.dto.UserDTO;
import com.sweetme.back.common.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.server.Cookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import static com.sweetme.back.auth.domain.User.*;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // Preflight 요청은 체크하지 않음
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURI();

        log.info("check uri.............." + path);

        // /auth 경로 처리
        if (path.startsWith("/auth/")) {
            if (path.equals("/auth/login")) {
                return true;
            }
            if (path.equals("/auth/refresh")) {
                return true;
            }

            return false;
        }

        // /profile 경로 처리
        if (path.startsWith("/profile/")) {
            if (path.equals("/profile/me")) {
                return false;
            }
            return true;
        }

        // /studies 경로 처리
        if (path.startsWith("/studies/")) {
            if (request.getMethod().equals("GET")) {
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("-----------------JWTCheckFilter-------------------");

        String authHeaderStr = request.getHeader("Authorization");

        log.info("authHeaderStr: " + authHeaderStr);

        try {
            //Bearer "accessToken"
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims: " + claims);

            // claims 로부터 UserDTO 생성
            UserDTO userDTO = UserDTO.fromClaims(claims);

            log.info("----------------------------------");
            log.info(userDTO);
            log.info(userDTO.getAuthorities());

            // JWT 토큰에서 추출한 사용자 정보로 인증 객체를 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDTO, userDTO.getPassword(), Collections.singleton(new SimpleGrantedAuthority(userDTO.getRole().name())));

            // SecurityContext 에 인증 정보 저장 => 컨트롤러나 서비스 계층에서 권한 검증시 사용
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        } catch (Exception e) {

            log.error("JWT Check Error............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }
}
