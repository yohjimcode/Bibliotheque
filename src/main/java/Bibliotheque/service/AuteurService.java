package Bibliotheque.service;

import Bibliotheque.infra.model.AuteurModel;
import Bibliotheque.infra.repo.AuteurJpaRepository;
import Bibliotheque.rest.dto.AuteurDto;
import Bibliotheque.service.mapper.AuteurMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService {

    private final AuteurMapper auteurMapper;
    private final AuteurJpaRepository auteurJpaRepository;

    public AuteurService(AuteurMapper auteurMapper, AuteurJpaRepository auteurJpaRepository) {
        this.auteurMapper = auteurMapper;
        this.auteurJpaRepository = auteurJpaRepository;
    }

    public Optional<AuteurDto> getAuteur(int id) {
        return auteurJpaRepository.findById(id).map(auteurMapper::toDto);
    }

    public List<AuteurDto> getAllLivres() {
        return auteurJpaRepository.findAll().stream().map(auteurMapper::toDto).toList();
    }

    public AuteurDto createAuteur(AuteurDto auteurDto) {
        AuteurModel auteurModel = auteurMapper.toModel(auteurDto);
        AuteurModel saveAuteur = auteurJpaRepository.save(auteurModel);
        return auteurMapper.toDto(saveAuteur);
    }

    public AuteurDto updateAuteur(int id, AuteurDto auteurDto) {
        AuteurModel auteurModel = auteurJpaRepository.findById(id).orElseThrow();
        auteurModel.setNom(auteurDto.getNom());
        auteurModel.setPrenom(auteurDto.getPrenom());
        auteurModel.setBiographie(auteurDto.getBiographie());
        return auteurMapper.toDto(auteurJpaRepository.save(auteurModel));
    }

    public boolean deletedAuteur(int id ){
        if(auteurJpaRepository.existsById(id)){
            auteurJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
