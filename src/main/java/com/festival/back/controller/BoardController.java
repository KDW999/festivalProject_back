package com.festival.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.service.BoardService;

@RestController
@RequestMapping()
public class BoardController {
    
    @Autowired private BoardService boardService;

    private final String Recommend = "/recommend";
}
