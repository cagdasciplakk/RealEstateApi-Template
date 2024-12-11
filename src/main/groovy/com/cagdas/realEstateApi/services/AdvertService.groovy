package com.cagdas.realEstateApi.services

import com.cagdas.realEstateApi.entities.Advert
import com.cagdas.realEstateApi.repositories.AdvertRepository
import org.springframework.stereotype.Service

@Service
class AdvertService {


    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    // Tüm ilanları getir
    public List<Advert> getAllAdverts() {
        return advertRepository.findAll();
    }

    // ID'ye göre ilan bul
    public Optional<Advert> getAdvertById(Long id) {
        return advertRepository.findById(id);
    }

    // Yeni ilan ekle veya güncelle
    public Advert saveAdvert(Advert advert) {
        return advertRepository.save(advert);
    }

    // ID'ye göre ilan sil
    public void deleteAdvertById(Long id) {
        advertRepository.deleteById(id);
    }
}
