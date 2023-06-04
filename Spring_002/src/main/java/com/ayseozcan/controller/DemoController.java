package com.ayseozcan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deger/")
public class DemoController {
//ustte slash koyarsak alttakilere ekleme
    @GetMapping("dondur")
    public String degerDondur(){
      return "hello";
    }

    @GetMapping("listname")
    public List<String> getListName(){
        List<String> names = new ArrayList<>();
        names.add("ayse");
        names.add("naz");
        names.add("cem");
        return names;
    }
    @GetMapping("getname")
    public String getNameList(String name){
        return name;
        // http://localhost:8080/deger/getname?name=ahmet
    }
}
