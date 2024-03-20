package in.ril.jio.scm.apieamauth.authentication.services;

import in.ril.jio.scm.apieamauth.authentication.models.OnRollAttributes;
import in.ril.jio.scm.apieamauth.config.JwtGenerator;
import in.ril.jio.scm.apieamauth.utils.XmlExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Base64;
import java.util.Map;

@Service
public class AuthenticateUserImpl implements AuthenticateUserInterface {

    @Autowired
    private XmlExtractor xmlExtractor;
    @Autowired
    private JwtGenerator jwtGenerator;

    @Override
    public ResponseEntity<Void> consumeSamlResponse(String samlConsume) {
        String decodedSAMLResponse = new String(Base64.getDecoder().decode(samlConsume.replace("\n", "").replace("\r", "")));

        if (decodedSAMLResponse.contains("status:Success")) {

            OnRollAttributes onRollAttributes = xmlExtractor.samlOnRollAttributesExtractor(decodedSAMLResponse);

            Map<String, String> jwtToken = jwtGenerator.generateToken(onRollAttributes.getCN());

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("https://oneoserp-ui-dev.jio.com/homeLandingPage" + "#" + jwtToken.get("token")))
                    .header("token", String.valueOf(jwtToken))
                    .build();
        } else {
            throw new IllegalArgumentException("User Not Authorized, Please Enter a Correct ID and Password");
        }
    }

    @Override
    public ResponseEntity<Void> logoutUser() {

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("https://oneoserp-ui-dev.jio.com/"))
                .build();
    }
}
