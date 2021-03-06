package br.com.builders.clients.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ClientDto {

    private Long id;
    private String cpf;
    private String address;
    private String phoneNumber;
    private String name;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private Integer age;

    public static ClientDto calculateAge(ClientDto clientDto) {
        clientDto.setAge(Period.between(clientDto.getBirthDate(), LocalDate.now()).getYears());
        return clientDto;
    }
}
