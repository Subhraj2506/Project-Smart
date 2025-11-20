package com.egov.security; import jakarta.servlet.*; import jakarta.servlet.http.HttpServletRequest; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.security.core.userdetails.UserDetailsService; import org.springframework.util.StringUtils; import java.io.IOException;
public class JwtFilter extends GenericFilter {
    private final JwtUtil jwtUtil; private final UserDetailsService uds;
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService uds){ this.jwtUtil=jwtUtil; this.uds=uds; }
    @Override public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req; String bearer = request.getHeader("Authorization"); String token=null;
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) token = bearer.substring(7);
        if(token!=null && jwtUtil.validateToken(token)){ String username=jwtUtil.getUsernameFromToken(token); UserDetails ud = uds.loadUserByUsername(username); UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities()); SecurityContextHolder.getContext().setAuthentication(auth); }
        chain.doFilter(req,res);
    }
}
