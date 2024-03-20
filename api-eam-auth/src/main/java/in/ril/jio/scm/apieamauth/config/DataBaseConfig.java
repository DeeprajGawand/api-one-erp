package in.ril.jio.scm.apieamauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class DataBaseConfig {

    @Value("${spring.datasource.username.base64}")
    private String encodedUsername;
    @Value("${spring.datasource.password.base64}")
    private String encodedPassword;
    @Value("${spring.datasource.url}")
    private String sqlURL;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DataSource dataSource() {

        // Decode Username and Password from Base64
        String username = new String(Base64.getDecoder().decode(encodedUsername), StandardCharsets.UTF_8);
        String password = new String(Base64.getDecoder().decode(encodedPassword), StandardCharsets.UTF_8);

        // Create DataSource with decoded username and password
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(sqlURL)
                .username(username)
                .password(password)
                .build();
    }
}
