package in.ril.jio.scm.apieamauth.authentication.services;

import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public interface AuthenticateUserInterface {

    ResponseEntity<Void> consumeSamlResponse(String samlConsume) throws URISyntaxException;

    ResponseEntity<Void> logoutUser();
}
