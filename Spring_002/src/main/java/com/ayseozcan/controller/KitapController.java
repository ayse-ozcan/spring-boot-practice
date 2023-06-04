package com.ayseozcan.controller;

import com.ayseozcan.repository.entity.Kitap;
import com.ayseozcan.service.KitapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kitap")
public class KitapController {
    private final KitapService kitapService;
    public KitapController(KitapService kitapService){
        this.kitapService = kitapService;
    }
    @GetMapping("/savekitap")
    public void saveKitap(String yazarIsmi, String baslik, Integer sayfaSayisi){
        Kitap kitap = Kitap.builder().yazarIsmi(yazarIsmi).baslik(baslik).sayfaSayisi(sayfaSayisi).build();
        kitapService.saveKitap(kitap);
        // http://localhost:9090/kitap/savekitap?yazarIsmi=Orhan%20Pamuk&&baslik=Kar&&sayfaSayisi=100
    }
    @GetMapping("/findall")
    public List<Kitap> findAll(){
        return kitapService.findAll();
    }
    @GetMapping("/findbybaslik")
    public Kitap findByBaslik(String baslik){
       return kitapService.findByBaslik(baslik);
    }
    @GetMapping("/findallbysayfasayisi")
    public List<Kitap> findAllBySayfaSayisi(Integer sayfasayisi){
        return kitapService.findAllBySayfaSayisi(sayfasayisi);
        // http://localhost:9090/kitap/findallbysayfasayisi?sayfasayisi=100
        // sayfasayisi parametreye gonderdigimiz isim entity dekiyle alakasi yok!!!
    }
    @GetMapping("/findoptionalbybaslik")
    public Kitap findOptionalByBaslik(String baslik){
        return kitapService.findOptionalByBaslik(baslik);
    }
}
