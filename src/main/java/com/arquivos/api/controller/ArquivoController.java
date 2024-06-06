package com.arquivos.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arquivos.api.model.Arquivo;
import com.arquivos.api.service.ArquivoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/arquivo")
public class ArquivoController {
    
    @PostMapping
    public ResponseEntity<Arquivo> uploadArquivo(
        @RequestParam("Arquivo") MultipartFile arquivo, @RequestParam("nome") String nome) throws IllegalStateException, IOException{
        
        return ResponseEntity.ok().body(arquivoService.uploadArquivo(arquivo, nome));

    }


    //CONTINUAR LISTAGEM DE ARQUIVO
    @GetMapping
    public ResponseEntity<?> downloadArquivo(@RequestParam("nome") String nome){
        byte[] arquivoService.downloadArquivo(nome);

    }


    @Autowired
    private ArquivoService arquivoService;

}
