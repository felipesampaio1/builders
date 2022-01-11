package br.com.builders.clients.service.mapper;

import br.com.builders.clients.domain.Client;
import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDto, Client>{

    Client toEntity(RequestClientDto requestClientDto);
}
