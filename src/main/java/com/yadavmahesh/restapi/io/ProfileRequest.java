package com.yadavmahesh.restapi.io;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 5,max = 50,message = "Name should be atleast 5 Characters")
    private String name;

    @NotNull(message = "Email is Required")
    @Email(message = "Provide Valid Email Address")
    private String email;

    @NotNull(message = "Passwored is required")
    @Size(min = 5,message = "Password should be atleast 5 Characters")
    private String password;

}
