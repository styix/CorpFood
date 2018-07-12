package CorpFood.model.service.impl;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.OfferRepository;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.repository.UserResponseRepository;
import CorpFood.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer findOneById(Long id) {
        return offerRepository.getOne(id);
    }

    @Override
    public Set<Offer> findAll() {
        return new HashSet<>(offerRepository.findAll());
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.delete(id);
    }

    @Override
    public Offer createOffer(CreateOfferDTO createOfferDTO) {
        Offer offer = new Offer();
        offer.setRestaurant(createOfferDTO.getRestaurant());
        offer.setURL(createOfferDTO.getURL());
        offer.setDescription(createOfferDTO.getDescription());
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> findActiveOffers() {

        Set<Offer> activeOffers = offerRepository.findAllByCreationTimeAfter(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        return activeOffers.stream()
                .sorted(comparing(Offer::getCreationTime))
                .collect(Collectors.toList());
    }

}
