package com.ayseozcan.service;

import com.ayseozcan.repository.IKitapRepository;
import com.ayseozcan.repository.entity.Kitap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitapService {
    private final IKitapRepository kitapRepository;
    public KitapService(IKitapRepository kitapRepository){
        this.kitapRepository = kitapRepository;
    }

    public void saveKitap(Kitap kitap) {
       kitapRepository.save(kitap);
    }

    public List<Kitap> findAll() {
       return kitapRepository.findAll();
    }
    public Kitap findByBaslik(String baslik){
       return kitapRepository.findByBaslik(baslik);
    }
    public List<Kitap> findAllBySayfaSayisi(Integer sayfasayisi){
        if(sayfasayisi <= 0){
            throw new RuntimeException("gecersiz sayfa sayisi");
        }else{
            return kitapRepository.findAllBySayfaSayisi(sayfasayisi);
        }
    }
    public Kitap findOptionalByBaslik(String baslik){
        Optional<Kitap> optionalKitap = kitapRepository.findOptionalByBaslik(baslik);
        if (optionalKitap.isPresent()){
            return optionalKitap.get();
        }else{
            throw new RuntimeException("bu basliga ait bir kitap bulunmamaktadir.");
        }
    }
}
