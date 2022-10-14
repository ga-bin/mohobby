package com.yedam.mohobby.serviceImpl.classes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.yedam.mohobby.mapper.classes.ClassMapper;
import com.yedam.mohobby.service.classes.ClassInfoRequestVO;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassService;
import com.yedam.mohobby.service.classes.ClassesVO;
import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.web.classes.ClassController;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    // 강의개설
    @Override
    public void insertClass(ClassesVO classes) {
        classMapper.insertClass(classes);
    }

    // 목록조회
    @Override
    public List<ClassesVO> listAll(ClassListRequestVO req) {
        return classMapper.listAll(req);
    }

    // 단건조회
    @Override
    public ClassesVO listOne(ClassListRequestVO req) {
        return classMapper.listOne(req);
    }

    // 찜등록
    @Override
    public void addJjim(JjimVO jjim) {
        classMapper.addJjim(jjim);
    }

    // 찜삭제
    @Override
    public void deleteJjim(JjimVO jjim) {
        classMapper.deleteJjim(jjim);
    }

    // html 저장
    @Override
    public void saveClassInfo(ClassInfoRequestVO req) {
        String path = ClassController.class.getResource("/").getPath();
        path = path.substring(0, path.lastIndexOf("mohobby"));
        path = path.substring(0, path.lastIndexOf("mohobby") + "mohobby".length());

        path += "/mohobby/mohobby/src/assets/html/classInfo/";
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
    
    @Override
    public String readClassInfo(int classId) {
        String path = ClassController.class.getResource("/").getPath();
        path = path.substring(0, path.lastIndexOf("mohobby"));
        path = path.substring(0, path.lastIndexOf("mohobby") + "mohobby".length());

        path += "/mohobby/mohobby/src/assets/html/classInfo/";
        path += String.valueOf(classId);
        path += ".html";
        
        File file = new File(path);
        String res = "";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            String str;
            
            while ((str = br.readLine()) != null) {
                res += str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        return res;
    }

    // qr코드 생성
    @Override
    public String createCodeImg(String link) {
        int width = 400;
        int height = 400;
        String base64 = "";
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, width, height);

            try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {

                MatrixToImageWriter.writeToStream(matrix, "PNG", out);

                base64 = new String(Base64.getEncoder().encode(out.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + base64;
    }

}
