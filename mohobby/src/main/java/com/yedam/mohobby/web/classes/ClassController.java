package com.yedam.mohobby.web.classes;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.yedam.mohobby.service.classes.ClassInfoRequestVO;
import com.yedam.mohobby.service.classes.ClassService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ClassController {
	@Autowired
	ClassService classService;
	
	//html 파일 생성
	@PostMapping("/saveClassInfo")
	public void saveClassInfo(@RequestBody ClassInfoRequestVO req) {
		System.out.println(req.getContent());
		System.out.println(req.getFilename());
		String path = ClassController.class.getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("mohobby"));
		path = path.substring(0, path.lastIndexOf("mohobby")+"mohobby".length());
		
		System.out.println(path);
		
		path += "/mohobby/src/main/webapp/resources/html/classInfo/";
		path += req.getFilename();
		path += ".html";
		System.out.println(path);
		
		File file = new File(path);
		
		try {
			FileOutputStream fos = new FileOutputStream(file, false);
			fos.write(req.getContent().getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//QR코드 생성
	@GetMapping(
	        value = "/AttdQR",
	        produces = MediaType.IMAGE_PNG_VALUE
	        )
	public @ResponseBody String createCodeImg(
	        @RequestParam String link
	        ) throws IOException, WriterException {
	    
	    //String img = "https://cdn.sisamagazine.co.kr/news/photo/202208/457615_463348_1644.jpg";
	    
	    int width = 400;
	    int height = 400;
	    BitMatrix matrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, width, height);
	    
	    try(ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	        
	        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
//	        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, new MatrixToImageConfig(ClassController.Colors.WHITE.getArgb(), ClassController.Colors.ORANGE.getArgb()));
//	        BufferedImage overly = ImageIO.read(new URL(img));
//
//	        int deltaHeight = qrImage.getHeight() - overly.getHeight();
//	        int deltaWidth = qrImage.getWidth() - overly.getWidth();
//	        
//	        BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = (Graphics2D) combined.getGraphics();
//            
//            g.drawImage(qrImage, 0, 0, null);
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//            // QR코드 이미지의 정중앙 위치에 덮음.
//            g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
//            
//            ImageIO.write(combined, "png", out);
	        
	       String base64 = new String(Base64.getEncoder().encode(out.toByteArray()));
	       return "data:image/png;base64," + base64;
	       
	    }
	}
	
	 public enum Colors {
         BLUE(0xFF40BAD0),
         RED(0xFFE91C43),
         PURPLE(0xFF8A4F9E),
         ORANGE(0xFFF4B13D),
         WHITE(0xFFFFFFFF),
         BLACK(0xFF000000);
         private final int argb;
         Colors(final int argb){
             this.argb = argb;
         }
         public int getArgb(){
             return argb;
         }
     }

}
