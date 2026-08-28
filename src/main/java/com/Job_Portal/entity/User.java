package com.Job_Portal.entity;

import com.Job_Portal.dto.AccountType;

import com.Job_Portal.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private Long id;



    private String name;


    @Indexed(unique = true)
    private String email;


    private String password;


    private AccountType accountType;
    private Long profileId;

    public UserDto toDto(){
        return  new UserDto(this.id, this.name, this.email, this.password,accountType, this.profileId);
    }
}
