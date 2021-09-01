package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller;

import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.AnotacaoDTO;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.AnotacaoForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/ap-anotacoes-ebd/anotacao")
public class AnotacaoController {

    private static final Logger log = LogManager.getLogger(AnotacaoController.class);

    //todo: anotacaoService
    //todo: anotacaoMapper
    //todo: anotacaoUtils

    @GetMapping("/{idUsuario}")
    @Cacheable(value = "listaAnotacoes")
    public Page<AnotacaoDTO> findAll(@PathVariable UUID idUsuario, @PageableDefault(sort = "sequencialAnotacao", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){
        //return usuarioMapper.converteParaDTO(usuarioService.findAll(paginacao));
        return null;
    }

    @GetMapping("/{idUsuario}/{sequencialAnotacao}")
    public ResponseEntity<AnotacaoDTO> findByAnotacaoId(@PathVariable UUID idUsuario, @PathVariable Integer sequencialAnotacao){
        return new ResponseEntity<>(new AnotacaoDTO(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaAnotacoes", allEntries = true)
    public ResponseEntity<AnotacaoDTO> save(@RequestBody @Valid AnotacaoForm anotacaoForm) {
        return new ResponseEntity<>(new AnotacaoDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}/{sequencialAnotacao}")
    @Transactional
    @CacheEvict(value = "listaAnotacoes", allEntries = true)
    public ResponseEntity<AnotacaoDTO> update(@PathVariable UUID idUsuario, @PathVariable Integer sequencialAnotacao, @RequestBody @Valid AnotacaoForm anotacaoForm) {
        return new ResponseEntity<>(new AnotacaoDTO(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUsuario}/{sequencialAnotacao}")
    @Transactional
    @CacheEvict(value = "listaAnotacoes", allEntries = true)
    public ResponseEntity delete(@PathVariable UUID idUsuario, @PathVariable Integer sequencialAnotacao) {
        return ResponseEntity.ok().build();
    }

}
