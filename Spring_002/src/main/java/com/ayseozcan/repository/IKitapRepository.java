package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IKitapRepository extends JpaRepository<Kitap,Long> {

    Kitap findByBaslik(String baslik);
    List<Kitap> findAllBySayfaSayisi(Integer sayfasayisi);
    Optional <Kitap> findOptionalByBaslik(String baslik);
}
