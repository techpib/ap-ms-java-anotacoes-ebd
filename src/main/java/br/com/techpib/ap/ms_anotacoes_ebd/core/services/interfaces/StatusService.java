package br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;

import java.util.Optional;

public interface StatusService {

    Optional<Status> findByIdStatus(Long idStatus);

}
