package com.miex.rest;

import com.miex.domain.Product;
import com.miex.domain.dao.impl.ProductDAO;
import com.miex.domain.dto.UnifiedResponse;
import com.miex.exception.ESException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

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
        log.info("id 查询：" + id);
        return new UnifiedResponse<>(productDAO.selectById(id));
    }
}
