package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.Base;
import org.study.PizzaDelivery.repository.BaseRepository;

import java.util.List;


@Service
public class BaseService {

    private static final Logger logger = LogManager.getLogger(BaseService.class);

    private final BaseRepository baseRepository;


    @Autowired
    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    public List<Base> findAll() {
        logger.info("Call method: findAll()");

        return (List<Base>) baseRepository.findAll();
    }

    @Transactional
    public Base findCheapest() {
        logger.info("Call method: findAll()");
        Base base = baseRepository.findTopOrderByPriceAsc();
        if (base != null) {
            logger.info(base);
        } else {
            logger.error("Base is not exist.");
        }

        return base;
    }
}
