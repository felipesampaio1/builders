package br.com.builders.clients.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data()
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class RequestClientDto {

    private Long id;
    @NotBlank(message = "The cpf must be informed.")
    private String cpf;
    private String address;
    @Pattern(regexp = "[9]{0,1}[1-9]{1}[0-9]{3}[0-9]{4}", message = "The Phone Number must be valid with only 9 digits.")
    private String phoneNumber;
    @NotBlank(message = "The name must be informed.")
    private String name;
    @Email
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "The birth date must be informed.")
    private LocalDate birthDate;
}
