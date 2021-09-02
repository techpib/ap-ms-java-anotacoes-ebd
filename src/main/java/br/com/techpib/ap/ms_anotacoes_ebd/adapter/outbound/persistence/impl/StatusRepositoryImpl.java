package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.impl;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.SpringDataStatusRepository;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import br.com.techpib.ap.ms_anotacoes_ebd.core.ports.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class StatusRepositoryImpl implements StatusRepository {

    @Autowired
    private SpringDataStatusRepository statusRepository;

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Page<Status> findAll(Pageable pageable) {
        return statusRepository.findAll(pageable);
    }

    @Override
    public Optional<Status> findById(Long idStatus) {
        return statusRepository.findById(idStatus);
    }
}
