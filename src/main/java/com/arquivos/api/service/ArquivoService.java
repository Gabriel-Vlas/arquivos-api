package com.arquivos.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arquivos.api.model.Arquivo;
import com.arquivos.api.repository.ArquivoRepository;

@Service
public class ArquivoService {

    public Arquivo uploadArquivo(MultipartFile file, String nome) throws IllegalStateException, IOException{
        String path = /* Criar pasta e usar o camminho */"/arquivos/" + file.getOriginalFilename();

        Arquivo arquivo = new Arquivo();

        arquivo.setNome(nome);
        arquivo.setDataHoraEnvio(LocalDateTime.now());
        arquivo.setPath(path);
        arquivo.setTipo(file.getContentType());

        File novoFile = new File(path);

        file.transferTo(novoFile);

        return arquivoRepository.save(arquivo);
    }

    public byte[] downloadArquivo(String nome) throws IOException{
        Optional<Arquivo> arquivo = arquivoRepository.findByNome(nome);    
        
        if (arquivo.isPresent()) {
            String path = arquivo.get().getPath();
            
            File file = new File(path);

            return Files.readAllBytes(file.toPath());
        }
        
        return null;

    }

    @Autowired
    private ArquivoRepository arquivoRepository;

}
