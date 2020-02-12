//package com.nfl.investigation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class IdTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//    private AuthenticationManager authenticationManager;
//
//    public IdTokenAuthenticationFilter() {
//        super("/**");
//        setAuthenticationSuccessHandler((request, response, authentication) ->
//        {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
//        });
//        setAuthenticationFailureHandler((request, response, authenticationException) -> {
//            response.getOutputStream().print(authenticationException.getMessage());
//        });
//    }
//
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        return true;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//
//        String header = request.getHeader("id_token");
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            //throw new JwtTokenMissingException("No JWT token found in request headers");
//        }
//
//        String authToken = "";//header.substring(7);
//
//        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
//
////        return authRequest;//getAuthenticationManager().authenticate(authRequest);
//        return getAuthenticationManager().authenticate(authRequest);
//    }
//
///*    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//
//        // As this authentication is in HTTP header, after success we need to continue the request normally
//        // and return the response as if the resource was not secured at all
//        chain.doFilter(request, response);
//    }*/
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//                         FilterChain chain) throws IOException, ServletException {
//        super.doFilter(req, res, chain);
//    }
//
///*    @Override
//    protected AuthenticationManager getAuthenticationManager() {
//        return authenticationManager;
//    }*/
//}
