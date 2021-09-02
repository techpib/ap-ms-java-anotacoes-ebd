package br.com.techpib.ap.ms_anotacoes_ebd.core.ports;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StatusRepository {

    Status save(Status status);
    Page<Status> findAll(Pageable pageable);
    Optional<Status> findById(Long idStatus);

}
