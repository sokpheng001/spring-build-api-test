package com.example.api.mbanking.api.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;
    //Auth
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;

}
