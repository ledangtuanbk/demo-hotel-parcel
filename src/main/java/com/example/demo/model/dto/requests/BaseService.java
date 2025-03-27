package com.example.demo.model.dto.requests;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class BaseService {

    // get page request with sorting
    protected PageRequest getPageRequest(int page, int size, String sort) {
        // some logic
        Sort.Direction direction = Sort.Direction.fromOptionalString(sort).orElse(Sort.Direction.DESC);
        // can sort by id or created
        Sort sortBy = Sort.by(direction, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sortBy);
        return pageRequest;
    }
}
