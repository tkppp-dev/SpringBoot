package com.example.aswspringbootjava.web;

import com.example.aswspringbootjava.domain.post.PostService;
import com.example.aswspringbootjava.web.dto.PostSaveRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(requestDto));
        return postService.save(requestDto);
    }
}
