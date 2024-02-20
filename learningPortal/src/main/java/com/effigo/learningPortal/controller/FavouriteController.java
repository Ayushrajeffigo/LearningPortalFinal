package com.effigo.learningPortal.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.learningPortal.dto.FavouriteDto;
import com.effigo.learningPortal.service.FavouriteService;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

	private final FavouriteService favouriteService;
	private static final String MESSAGE_KEY = "message";

	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@PostMapping("/make")
	public ResponseEntity<?> makeFavourite(@Valid @RequestBody FavouriteDto favouriteDto) {
		boolean favourite = favouriteService.makeFavourite(favouriteDto);
		HashMap<String, String> response = new HashMap<>();

		if (favourite) {
			response.put(MESSAGE_KEY, "Course added to favourites.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put(MESSAGE_KEY, "Validation error");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/remove")
	public ResponseEntity<?> removeFavourite(@Valid @RequestBody FavouriteDto favouriteDto) {
		boolean deleteFavourite = favouriteService.removeFavourite(favouriteDto);
		HashMap<String, String> response = new HashMap<>();

		if (deleteFavourite) {
			response.put(MESSAGE_KEY, "Course removed from favourites");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put(MESSAGE_KEY, "Something went wrong");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}