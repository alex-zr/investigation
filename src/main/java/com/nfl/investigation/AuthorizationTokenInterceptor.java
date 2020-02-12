//package com.nfl.investigation;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class AuthorizationTokenInterceptor extends HandlerInterceptorAdapter {
//    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationTokenInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println(request);
///*        // We let OPTIONS calls go through
//        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
//            return true;
//        }
//
//        // extract token from request
//        JWTBody jwtBody = JwtTokenUtil.getTokenBody(request.getHeader(HttpHeaders.AUTHORIZATION));
//
//        LOG.debug("PreHandle request with JWT Body {}", jwtBody);
//        // client id
//        if (jwtBody.getClientId() == null) {
//            throw new InvalidClientException(InvalidClientException.CANNOT_FIND_CREDENTIAL);
//        }
//        request.setAttribute(JWTClientId.key, jwtBody.getClientId());
//
//        // device id
//        if (jwtBody.getDeviceId() != null) {
//            request.setAttribute(JWTDeviceId.key, jwtBody.getDeviceId());
//        }
//
//        // username
//        if (jwtBody.getUsername() != null) {
//            request.setAttribute(JWTUsername.key, jwtBody.getUsername());
//        }
//
//        // uid
//        if (jwtBody.getUid() != null) {
//            request.setAttribute(JWTUid.key, jwtBody.getUid());
//        }
//
//        // exp
//        if (jwtBody.getExp() != null) {
//            request.setAttribute(JWTExp.key, jwtBody.getExp());
//        }
//
//        // iat
//        if (jwtBody.getIat() != null) {
//            request.setAttribute(JWTIat.key, jwtBody.getIat());
//        }
//
//        // jwtBody
//        request.setAttribute(JWT.key, jwtBody);*/
//
//        return true;
//    }
//}
