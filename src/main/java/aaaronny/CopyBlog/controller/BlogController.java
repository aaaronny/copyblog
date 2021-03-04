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
    public ResponseEntity<byte[]> getImg64(@RequestParam("url") String path) {

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
    @RequestMapping(value = "/image64", method = RequestMethod.GET)
    public String getImg(@RequestParam("url") String path) {

        String response = null;
        
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
        
            response = Base64.getEncoder().encodeToString(img);
        } catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/sptest", method = RequestMethod.GET)
    public String sharepoint() {

        String response = null;
        String url = "https://kmakers.sharepoint.com:443/_api/v2.0/drives/b!ZDi6MsRb-Uy_NyBaY1DFadVE-GemSj1LpS89F9v-vxm-pyxK9HlIRL19Vahd11jl/items/01IE2KTKK563XHAQ7YAJHJ4LW3VN4BARRF?version=Published&access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAva21ha2Vycy5zaGFyZXBvaW50LmNvbUAwMTQwOTY0OC1jNTUxLTQ4YTEtOWQ4MC01ZTNmODJlYmYyYTEiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjE0ODM3NjAwIiwiZXhwIjoiMTYxNDg1OTIwMCIsImVuZHBvaW50dXJsIjoiNDY0b1lJak5wbytlRDJGZllBSU9GSkZTNER5V08wSjdtaitCNWlvQ1VnRT0iLCJlbmRwb2ludHVybExlbmd0aCI6IjExNCIsImlzbG9vcGJhY2siOiJUcnVlIiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6Ik16SmlZVE00TmpRdE5XSmpOQzAwWTJZNUxXSm1NemN0TWpBMVlUWXpOVEJqTlRZNSIsInNpZ25pbl9zdGF0ZSI6IltcImttc2lcIl0iLCJuYW1laWQiOiIwIy5mfG1lbWJlcnNoaXB8YWxlc3NhbmRyby5hcGljZWxsYUBzdGVwc3JsLm9yZyIsIm5paSI6Im1pY3Jvc29mdC5zaGFyZXBvaW50IiwiaXN1c2VyIjoidHJ1ZSIsImNhY2hla2V5IjoiMGguZnxtZW1iZXJzaGlwfDEwMDM3ZmZlYTQ1NTdlNDBAbGl2ZS5jb20iLCJ0dCI6IjAiLCJ1c2VQZXJzaXN0ZW50Q29va2llIjoiMyJ9.bW5mellHblNBaVBhSzhaakNmOSsrMk5Ja0F3Q1o1dHJPcG9BYnNHdGF4QT0";

        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(url, String.class);
            
        return res;
    }

    //produces = MediaType.IMAGE_JPEG_VALUE

}
