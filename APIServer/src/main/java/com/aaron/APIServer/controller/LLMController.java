package com.aaron.APIServer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.aaron.APIServer.OpenAIConnection;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class LLMController {

  @PostMapping("/llmresponse")
  public ResponseEntity<Integer> countCharacters(@RequestBody List<Character> board) {
    String llmReponse = OpenAIConnection.getResponse(board.toString());
    return ResponseEntity.ok(Integer.valueOf(llmReponse));
  } 
}
