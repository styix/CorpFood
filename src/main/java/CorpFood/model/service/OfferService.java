package CorpFood.model.service;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.entity.Offer;

import java.util.Set;

public interface OfferService {

    Offer findOneById(Long id);

    Set<Offer> findAll();

    void deleteOffer(Long id);

    Offer createOffer(CreateOfferDTO createUserDto);
}