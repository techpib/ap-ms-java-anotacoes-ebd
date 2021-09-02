package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataStatusRepository extends JpaRepository<Status, Integer> {
}
