package org.study.PizzaDelivery.data.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.model.Product;
import org.study.PizzaDelivery.data.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findOne(long id){
       return productRepository.findById(id);
    }

    public Product findDistinctTopByName(String productName){
        return productRepository.findDistinctTopByName(productName);
    }

    @Transactional
    public Product findByNameAndBaseId(String productName, Short baseId){
        System.out.println(productName+ " "+ baseId);
      return   productRepository.findByNameAndBaseId(productName, baseId);
    }

    @Transactional
    public List<Product> findAllByBase(Base base){
       return productRepository.findAllByBase(base);
    }

    public List<Product> findByCategoryName(String name){ return productRepository.findAllByCategoryName(name);}

    public Iterable<Product> findAll(){return productRepository.findAll();}

    public List<Product> findAllById(long id){return productRepository.findAllById(id);}

    public List<Product> findAllByCategoryId(short id){return productRepository.findAllByCategoryId(id);}

    @Transactional
    @Modifying
    public void deleteAllVariablesOfProductByName(String productName){
        if (productRepository.existsByName(productName)){ //TODO вот так можно на null проверять
            productRepository.deleteAllByName(productName);
        }

    }
}
