package com.miex.rest;

import com.miex.domain.Product;
import com.miex.domain.dao.impl.ProductDAO;
import com.miex.domain.dto.UnifiedResponse;
import com.miex.exception.ESException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductRest {

    @Autowired
    ProductDAO productDAO;

    @PostMapping("add")
    public UnifiedResponse<String> add(@RequestBody Product product) throws ESException {
        return new UnifiedResponse<>(productDAO.insert(product));
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
    public UnifiedResponse<List<Product>> selectList(@RequestBody Product product) throws IOException {
        return null;
    }

    @PostMapping("delete-by-id")
    public UnifiedResponse<String> deleteById(@RequestParam("id") @NotEmpty(message = "id不能为空") String id) throws ESException {
        return new UnifiedResponse<>(productDAO.deleteById(id));
    }

    @PostMapping("update-by-id")
    public UnifiedResponse<String> updateById(@RequestBody Product product) throws ESException {
        return new UnifiedResponse<>(productDAO.updateById(product));
    }
}
