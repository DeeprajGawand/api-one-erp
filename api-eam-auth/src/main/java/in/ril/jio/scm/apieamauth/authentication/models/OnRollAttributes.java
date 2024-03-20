package in.ril.jio.scm.apieamauth.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnRollAttributes {

    public String firstname;

    public String lastname;

    public String username;

    public String email;

    public String CN;

}
