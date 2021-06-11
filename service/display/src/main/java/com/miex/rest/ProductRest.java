package com.miex.rest;

import com.miex.domain.Product;
import com.miex.domain.dao.impl.ProductDAO;
import com.miex.domain.dto.UnifiedResponse;
import com.miex.exception.ESException;
import com.miex.util.anno.Id;
import com.miex.util.anno.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductRest {

    @Autowired
    ProductDAO productDAO;

    @PostMapping("add")
    public UnifiedResponse add(@RequestBody Product product) throws ESException {
        productDAO.insert(product);
        return new UnifiedResponse();
    }

    @PostMapping("select-by-id")
    public UnifiedResponse<Product> selectById(@RequestParam("id") String id) throws ESException {
        return new UnifiedResponse<>(productDAO.selectById(id));
    }

    @PostMapping("select-one")
    public UnifiedResponse<Product> selectOne(@RequestBody Product product) throws ESException {
        return new UnifiedResponse<>(productDAO.selectOne(product));
    }

    @PostMapping("select-list")
    public UnifiedResponse<List<Product>> selectList(@RequestBody Product product) throws  ESException {
         productDAO.selectOne(product);
        return null;
    }
}
