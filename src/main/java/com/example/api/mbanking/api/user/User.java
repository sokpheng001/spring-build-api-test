package com.example.api.mbanking.api.user;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String onSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;
}
