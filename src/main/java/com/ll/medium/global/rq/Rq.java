package com.ll.medium.global.rq;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.global.rsData.RsData;
import com.ll.medium.standard.util.Ut.Ut;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final MemberService memberService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public String redirect(String url, String msg) {
        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        sb.append("redirect:");
        sb.append(url);
        if (msg != null) {
            sb.append("?msg=");
            sb.append(msg);
        }
        return sb.toString();
    }
    public String historyBack(String msg) {
        request.setAttribute("failMsg", msg);
        return "global/js";
    }
    public String redirectOrBack(RsData<?> rs, String path) {
        if (rs.isFail()) return historyBack(rs.getMsg());

        return redirect(path, rs.getMsg());
    }

    public Member getMember() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(it -> it instanceof Member)
                .map(it -> (Member) it)
                .orElse(null);
    }

    public boolean isLogin() {
        return getMember() != null;
    }

    public boolean isLogout() {
        return !isLogin();
    }

    public boolean isAdmin() {
        if (isLogout()) return false;

        return getMember()
                .getAuthorities()
                .stream()
                .anyMatch(it -> it.getAuthority().equals("ROLE_ADMIN"));
    }

    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    public String getCurrentQueryStringWithoutParam(String paramName) {
        String queryString = request.getQueryString();

        if (queryString == null) {
            return "";
        }

        queryString = Ut.url.deleteQueryParam(queryString, paramName);

        return queryString;
    }

    public String getEncodedCurrentUrl() {
        return Ut.url.encode(getCurrentUrl());
    }

    private String getCurrentUrl() {
        String url = request.getRequestURI();
        String queryString = request.getQueryString();

        if (queryString != null) {
            url += "?" + queryString;
        }

        return url;
    }

    public String getProfileImgUrl() {
        return memberService.getProfileImgUrl(getMember());
    }
}