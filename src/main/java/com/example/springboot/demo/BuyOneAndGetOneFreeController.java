package com.example.springboot.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class BuyOneAndGetOneFreeController {
	
	@GetMapping(value ="/buyonegetonefree/applyrule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> applyRule(@RequestBody List<Integer> input) {
		Collections.sort(input, Collections.reverseOrder());
		List<Integer> payList = new ArrayList<>();
		List<Integer> discountList = new ArrayList<>();
		
		for(int i=0; i<input.size();i++) {
			if(payList.size() == discountList.size()) {
				payList.add(input.get(i));
				continue;
			}
			if(payList.get(discountList.size()) > input.get(i)) {
				discountList.add(input.get(i));
			} else {
				payList.add(input.get(i));
			}
		}
		StringBuilder builder  = new StringBuilder();
		builder.append("Discounted Items   " ).append(discountList).
		append("  payable Items  ").append(payList);
		return new ResponseEntity<>(new Gson().toJson(builder.toString()), HttpStatus.OK);
	}

}
