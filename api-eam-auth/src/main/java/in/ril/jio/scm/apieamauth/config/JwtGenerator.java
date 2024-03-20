package in.ril.jio.scm.apieamauth.config;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JwtGenerator {
    Map<String, String> generateToken(String username);

    public String extractUsername(String token);
}
