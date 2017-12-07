package fi.solinor.mathathon.resultservice;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mathathon2017.util.ImageBase;

@SpringBootApplication
public class ResultserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultserviceApplication.class, args);
	}
	
	@RestController
	@RequestMapping("/")
	class ResultController {
	    
	    private Map<String, ImageBase> images = new HashMap<>();
	    
	    private Map<String, Long> results = new HashMap<>();
	    
        private BufferedImage solinorLogo = ImageUtils.getImage("solinor_avatar.png");

	    @GetMapping(value="results/")
	    Map<String, Long> getResults() {
	        return results;
	    }
	    
	    @PutMapping(value="submit/{name}", consumes="application/json")
	    String uploadResults(@PathVariable String name, @RequestBody ImageBase imageBase) {
	        System.err.println(imageBase);
	        if(imageBase.getTriangles().size() != 30) {
	            System.out.println("Triangle count was " + imageBase.getTriangles().size());
	            return "Triangle count not 30";
	        }
	        Long diff = ImageUtils.compare(imageBase, solinorLogo); 
	        if(results.get(name) == null || results.get(name) > diff) {
	            results.put(name, diff);
	            images.put(name, imageBase);
	        }
	        return "ok";
	    }
	    
	    @GetMapping(value="test")
	    String test() {
	        return solinorLogo.toString();
	    }
	    @GetMapping(value="picture/{name}")
	    public void showImage(@PathVariable String name, HttpServletResponse response) throws Exception {

	      ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
	      if(!images.containsKey(name)) {
		        response.sendError(HttpServletResponse.SC_NOT_FOUND);
		        return;
	      }
	      try {
	        BufferedImage image = ImageUtils.drawImage(images.get(name), solinorLogo.getWidth(), solinorLogo.getHeight());
	        ImageIO.write(image, "png", jpegOutputStream);
	      } catch (IllegalArgumentException e) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        return;
	      }

	      byte[] imgByte = jpegOutputStream.toByteArray();

	      response.setHeader("Cache-Control", "no-store");
	      response.setHeader("Pragma", "no-cache");
	      response.setDateHeader("Expires", 0);
	      response.setContentType("image/png");
	      ServletOutputStream responseOutputStream = response.getOutputStream();
	      responseOutputStream.write(imgByte);
	      responseOutputStream.flush();
	      responseOutputStream.close();
	    }
	}
}
