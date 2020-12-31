package aaaronny.CopyBlog.controller;

import org.springframework.http.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImg(@RequestParam("url") String path) {

        ResponseEntity<byte[]> response = null;
        
        try {

        URL url = new URL(path);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Firefox");

        try (InputStream inputStream = conn.getInputStream()) {
            int n = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
        byte[] img = output.toByteArray();
        
            response = new ResponseEntity<byte[]>(img, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/image64", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<String> getImg(@RequestParam("url") String path) {

        ResponseEntity<byte[]> response = null;
        
        try {

        URL url = new URL(path);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Firefox");

        try (InputStream inputStream = conn.getInputStream()) {
            int n = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
        byte[] img = output.toByteArray();
        
            response = new ResponseEntity<String>(img, Base64.getEncoder().encodeToString(img));
        } catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

    //produces = MediaType.IMAGE_JPEG_VALUE

}
