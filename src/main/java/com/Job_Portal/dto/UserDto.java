package com.Job_Portal.dto;

import com.Job_Portal.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Long id;
    @NotBlank(message ="Name in null or blank")

    private String name;

    @NotBlank(message ="Email in null or blank")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "password in null or blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "uppercase, lowercase,number,special character and 8-15."
    )
    private String password;


    private AccountType accountType;
    private Long profileId;


    public User toEntity(){
        return  new User(this.id, this.name, this.email, this.password,accountType, this.profileId);
    }
}
