package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller.utils.AnotacaoUtils;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller.utils.UsuarioUtils;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.enums.StatusEnum;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.AnotacaoDTO;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.AnotacaoForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.AnotacaoMapper;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.AnotacaoService;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.StatusService;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/ap-anotacoes-ebd/anotacao")
public class AnotacaoController {

    private static final Logger log = LoggerFactory.getLogger(AnotacaoController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private AnotacaoService anotacaoService;

    @Autowired
    private AnotacaoMapper anotacaoMapper;

    @GetMapping("/{idUsuario}")
    @Cacheable(value = "listaAnotacoes")
    public ResponseEntity<Page<AnotacaoDTO>> findAll(@PathVariable UUID idUsuario, @PageableDefault(sort = "sequencial_Anotacao", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){
        log.info("[GET] - findAll, idUsuario: {}, data: {}", idUsuario, new Date());

        if (!UsuarioUtils.usuarioExiste(usuarioService, idUsuario)){
            log.error("[GET] - findAll, Usuario nao existe na base! idUsuario: {}, data: {}", idUsuario, new Date());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return new ResponseEntity<>(anotacaoMapper.converteParaDTO(anotacaoService.findAnotacaoByIdUsuario(paginacao, idUsuario)), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}/{sequencialAnotacao}")
    public ResponseEntity<AnotacaoDTO> findByAnotacaoId(@PathVariable UUID idUsuario, @PathVariable Integer sequencialAnotacao){
        log.info("[GET] - findByAnotacaoId, idUsuario: {}, sequencialAnotacao: {}, data: {}", idUsuario, sequencialAnotacao, new Date());

        if (!UsuarioUtils.usuarioExiste(usuarioService, idUsuario) || !AnotacaoUtils.anotacaoExiste(anotacaoService, idUsuario, sequencialAnotacao)){
            log.info("[GET] - findByAnotacaoId, Usuario e/ou anotacao nao existe na base!, idUsuario: {}, sequencialAnotacao: {}, data: {}", idUsuario, sequencialAnotacao, new Date());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return new ResponseEntity<>(anotacaoMapper.converteParaDTO(anotacaoService.findAnotacaoByAnotacaoId(new AnotacaoId(idUsuario, sequencialAnotacao)).get()), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaAnotacoes", allEntries = true)
    public ResponseEntity<AnotacaoDTO> save(@RequestBody @Valid AnotacaoForm anotacaoForm) {
        log.info("[POST] - save, anotacaoForm: {}, data: {}", anotacaoForm, new Date());

        if (!UsuarioUtils.usuarioExiste(usuarioService, anotacaoForm.getIdUsuario())){
            log.error("[POST] - save, Usuario nao existe na base! anotacaoForm: {}, data: {}", anotacaoForm, new Date());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Usuario usuario = usuarioService.findUsuarioByIdUsuario(anotacaoForm.getIdUsuario()).get();
        Status status = statusService.findByIdStatus(StatusEnum.NOVA.idStatus).get();
        anotacaoForm.setSequencialAnotacao(usuarioService.findUsuarioByIdUsuario(anotacaoForm.getIdUsuario()).get().getProximoSequencialAnotacao());

        return new ResponseEntity<>(anotacaoMapper.converteParaDTO(anotacaoService.save(anotacaoMapper.converteParaEntity(anotacaoForm, status, usuario))), HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}/{sequencialAnotacao}")
    @Transactional
    @CacheEvict(value = "listaAnotacoes", allEntries = true)
    public ResponseEntity<AnotacaoDTO> update(@PathVariable UUID idUsuario, @PathVariable Integer sequencialAnotacao, @RequestBody @Valid AnotacaoForm anotacaoForm) {
        log.info("[GET] - update, idUsuario: {}, sequencialAnotacao: {}, data: {}", idUsuario, sequencialAnotacao, new Date());

        if (!UsuarioUtils.usuarioExiste(usuarioService, idUsuario) || !AnotacaoUtils.anotacaoExiste(anotacaoService, idUsuario, sequencialAnotacao)){
            log.info("[GET] - update, Usuario e/ou anotacao nao existe na base!, idUsuario: {}, sequencialAnotacao: {}, data: {}", idUsuario, sequencialAnotacao, new Date());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Usuario usuario = usuarioService.findUsuarioByIdUsuario(anotacaoForm.getIdUsuario()).get();
        Status status = statusService.findByIdStatus(StatusEnum.ALTERADA.idStatus).get();
        anotacaoForm.setSequencialAnotacao(sequencialAnotacao);

        return new ResponseEntity<>(anotacaoMapper.converteParaDTO(anotacaoService.update(anotacaoMapper.converteParaEntity(anotacaoForm, status, usuario))), HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}/{sequencialAnotacao}")
    @Transactional
    @CacheEvict(value = "listaAnotacoes", allEntries = true)
    public ResponseEntity delete(@PathVariable UUID idUsuario, @PathVariable Integer sequencialAnotacao) {
        log.info("[GET] - delete, idUsuario: {}, sequencialAnotacao: {}, data: {}", idUsuario, sequencialAnotacao, new Date());

        if (!UsuarioUtils.usuarioExiste(usuarioService, idUsuario) || !AnotacaoUtils.anotacaoExiste(anotacaoService, idUsuario, sequencialAnotacao)){
            log.info("[GET] - update, Usuario e/ou anotacao nao existe na base!, idUsuario: {}, sequencialAnotacao: {}, data: {}", idUsuario, sequencialAnotacao, new Date());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Status status = statusService.findByIdStatus(StatusEnum.EXCLUIDA.idStatus).get();
        Anotacao anotacao = anotacaoService.findAnotacaoByAnotacaoId(new AnotacaoId(idUsuario, sequencialAnotacao)).get();
        anotacao.setStatus(status);

        anotacaoService.delete(anotacao);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
