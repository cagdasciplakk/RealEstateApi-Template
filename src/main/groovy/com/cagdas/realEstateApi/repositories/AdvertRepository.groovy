package com.cagdas.realEstateApi.repositories

import com.cagdas.realEstateApi.entities.Advert
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

interface AdvertRepository extends JpaRepository<Advert,Long>{

}
