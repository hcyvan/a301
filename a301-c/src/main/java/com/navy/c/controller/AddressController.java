package com.navy.c.controller;

import com.navy.c.pojo.AddressCreateRequest;
import com.navy.common.pojo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AddressController {
    @PostMapping("/address")
    @ResponseBody
    public Result createAddress(@Valid @RequestBody AddressCreateRequest addressCreateRequest) {
        return Result.ok();
    }
}
