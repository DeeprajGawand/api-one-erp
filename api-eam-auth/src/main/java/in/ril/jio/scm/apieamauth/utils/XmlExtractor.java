package in.ril.jio.scm.apieamauth.utils;

import in.ril.jio.scm.apieamauth.authentication.models.OnRollAttributes;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class XmlExtractor {

    public OnRollAttributes samlOnRollAttributesExtractor(String decodedSAMLResponse) {

        OnRollAttributes onRollAttributes = new OnRollAttributes();
        String[] attributes = decodedSAMLResponse.split("</saml:Attribute>");

        Arrays.asList(attributes).stream().map((item) -> {
            if (item.contains("firstname")) {
                onRollAttributes.setFirstname(item.split("</saml:AttributeValue>")[0]
                        .split("xsi:type=\"xs:string\">")[1]);
            } else if (item.contains("lastname")) {
                onRollAttributes.setLastname(item.split("</saml:AttributeValue>")[0]
                        .split("xsi:type=\"xs:string\">")[1]);
            } else if (item.contains("UserName")) {
                onRollAttributes.setUsername(item.split("</saml:AttributeValue>")[0]
                        .split("xsi:type=\"xs:string\">")[1]);
            } else if (item.contains("email")) {
                onRollAttributes.setEmail(item.split("</saml:AttributeValue>")[0]
                        .split("xsi:type=\"xs:string\">")[1]);
            } else if (item.contains("CN")) {
                onRollAttributes.setCN(item.split("</saml:AttributeValue>")[0]
                        .split("xsi:type=\"xs:string\">")[1]);
            }
            return onRollAttributes;
        }).toList();
        return onRollAttributes;
    }
}
