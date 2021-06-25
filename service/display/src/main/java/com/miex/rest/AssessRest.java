package com.miex.rest;

import com.miex.domain.Assess;
import com.miex.domain.dao.impl.AssessDAO;
import com.miex.domain.dto.UnifiedResponse;
import com.miex.exception.ESException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assess")
public class AssessRest {

    @Autowired
    AssessDAO assessDAO;

    @PostMapping("add")
    public UnifiedResponse<String> add(@RequestBody Assess assess) throws ESException {
        return new UnifiedResponse<>(assessDAO.insert(assess));
    }
}
