package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.enums;

public enum StatusEnum {

    NOVA(1L, "NOVA"),
    ALTERADA(2L, "ALTERADA"),
    EXCLUIDA(3L, "EXCLUIDA"),
    A_EXPURGAR(4L, "A EXPURGAR");

    public Long idStatus;
    public String status;

    StatusEnum(Long idStatus, String status) {
        this.idStatus = idStatus;
        this.status = status;
    }
}
