package br.com.builders.clients.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestClientDto {


    @NotBlank(message = "The cpf must be informed.")
    private String cpf;
    private String address;
    @Pattern(regexp = "[9]{0,1}[1-9]{1}[0-9]{3}[0-9]{4}")
    private String phoneNumber;
    @NotBlank(message = "The name must be informed.")
    private String name;
    @Email
    private String email;

}
