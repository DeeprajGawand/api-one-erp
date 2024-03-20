package in.ril.jio.scm.apieamauth.authentication.controllers;

import in.ril.jio.scm.apieamauth.authentication.services.AuthenticateUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URISyntaxException;
import java.util.Objects;

@RestController
@RequestMapping("/api/one-erp")
public class AuthenticateUser {
    private final String DOMAIN_ID = "domainId";
    private final String PASSWORD = "password";
    private final String EMPLOYEE_ID = "employeeId";

    @Autowired
    private AuthenticateUserInterface authenticateUserInterface;


    @PostMapping("/saml/consume")
    public ResponseEntity<Void> samlConsume(@RequestParam("SAMLResponse") String samlConsume,
                                              @RequestParam("RelayState") String relayState) {

        try {
            if (Objects.nonNull(samlConsume) && !samlConsume.equals("")) {
                return authenticateUserInterface.consumeSamlResponse(samlConsume);
            } else {
                throw new IllegalArgumentException("User Not Authorized, Please Enter a Correct ID and Password");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logoutOneERP() {
        try {
            return authenticateUserInterface.logoutUser();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
