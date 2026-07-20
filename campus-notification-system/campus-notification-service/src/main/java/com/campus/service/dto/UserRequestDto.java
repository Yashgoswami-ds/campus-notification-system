package com.campus.service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UserRequestDto {
@NotBlank(message = "Name must be required!")
@Size(min = 2, max = 100,message = "Name cannot exceed 100 characters.")
    private  String name;

@Email(message ="Invalid email format." )
@NotBlank(message = "Email is Required !")
    private String Email;

@NotNull(message = "phone number Must be Required 1")
@Size(  min = 10, max = 15, message = "Phone number must be between 10 and 15 digits." )
    private  String phoneNumber;


}
