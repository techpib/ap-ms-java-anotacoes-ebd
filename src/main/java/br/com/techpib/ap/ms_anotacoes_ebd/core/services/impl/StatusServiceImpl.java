package br.com.techpib.ap.ms_anotacoes_ebd.core.services.impl;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import br.com.techpib.ap.ms_anotacoes_ebd.core.ports.StatusRepository;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Optional<Status> findByIdStatus(Long idStatus) {
        return statusRepository.findById(idStatus);
    }
}
