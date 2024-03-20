package in.ril.jio.scm.apieamauth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        try {
            filter.setFilter(new JwtFilter());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        // provide endpoints which needs to be restricted.
        // All Endpoints would be restricted if unspecified
//        filter.addUrlPatterns("/api/mrs/master/search");
        filter.addUrlPatterns("/api/mrs/abc");
        return filter;
    }
}
