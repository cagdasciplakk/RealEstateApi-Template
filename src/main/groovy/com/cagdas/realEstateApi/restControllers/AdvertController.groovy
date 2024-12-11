package com.cagdas.realEstateApi.restControllers

import com.cagdas.realEstateApi.entities.Advert;
import com.cagdas.realEstateApi.services.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adverts")
class AdvertController {
    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    // Tüm ilanları getir
    @GetMapping
    public ResponseEntity<List<Advert>> getAllAdverts() {
        List<Advert> adverts = advertService.getAllAdverts();
        return ResponseEntity.ok(adverts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advert> getAdvertById(@PathVariable("id") Long id) {
        return advertService.getAdvertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Yeni ilan ekle
    @PostMapping
    public ResponseEntity<Advert> createAdvert(@RequestBody Advert advert) {
        Advert savedAdvert = advertService.saveAdvert(advert);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdvert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Advert> updateAdvert(@PathVariable("id") Long id, @RequestBody Advert advert) {
        advert.setId(id); // ID'yi sabitle
        Advert updatedAdvert = advertService.saveAdvert(advert);
        return ResponseEntity.ok(updatedAdvert);
    }

    // İlan sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvert(@PathVariable("id") Long id) {
        if (advertService.getAdvertById(id).isPresent()) {
            advertService.deleteAdvertById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
