package aaaronny.CopyBlog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BlogController {

	@RequestMapping(value = "/copia", method = RequestMethod.GET)
	public ResponseEntity<String> getOldMsg(@RequestParam("url") String url) {

		ResponseEntity<String> response = null;

		RestTemplate restTemplate = new RestTemplate();
		String res = restTemplate.getForObject(url, String.class);
		if (!res.equals("null")) {
			response = new ResponseEntity<String>(res, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return response;
	}

}
