package com.yedam.mohobby.serviceImpl.classes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.yedam.mohobby.mapper.classes.ClassMapper;
import com.yedam.mohobby.service.classes.ClassAttendanceVO;
import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassChapterVO;
import com.yedam.mohobby.service.classes.ClassCurriculumVO;
import com.yedam.mohobby.service.classes.ClassImageVO;
import com.yedam.mohobby.service.classes.ClassInfoRequestVO;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassPayVO;
import com.yedam.mohobby.service.classes.ClassReviewVO;
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
    
    // 후기평가조회
    @Override
    public ClassReviewVO getClassReview(int classId) {
    	return classMapper.getClassReview(classId);
    }
    
    // 강의게시글조회
    @Override
    public List<ClassBoardVO> getClassBoardList(int classId, int boardType) {
    	return classMapper.getClassBoardList(classId, boardType);
    }
    
    // 강의게시글등록
    @Override
    public ClassBoardVO addClassBoard(ClassBoardVO board) {
        classMapper.addClassBoard(board);
        return board;
    }
    
    // 강의게시글수정
    @Override
    public void updateClassBoard(ClassBoardVO board) {
        classMapper.updateClassBoard(board);
    }

    // 강의게시글삭제
    public void deleteClassBoard(int boardId) {
        classMapper.deleteClassBoard(boardId);
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
    
    // 강의챕터조회
    @Override
    public List<ClassChapterVO> getChapterList(int classId, String memberId) {
    	ClassChapterVO vo = new ClassChapterVO();
    	vo.setMemberId(memberId);
    	vo.setClassId(classId);
        return classMapper.getChapterList(vo);
    }
    
    @Override
    //강의 커리큘럼 전체 진행율 조회
  	public HashMap<String, Integer> getCurrProgressAll(int classId, String memberId) {
  		return classMapper.getCurrProgressAll(classId, memberId);
  	}
    
    // 강의 커리큘럼 진행율 조회
    @Override
    public ClassAttendanceVO getCurrProgress(ClassAttendanceVO vo) {
    	return classMapper.getCurrProgress(vo);
    }
    
    // 강의 커리큘럼 단건 조회
    @Override
    public ClassCurriculumVO getCurrInfo(int currId) {
    	return classMapper.getCurrInfo(currId);
    }
    
    // 강의 결제내역 단건조회
    public ClassPayVO getClassPayOne(ClassPayVO vo) {
    	return classMapper.getClassPayOne(vo);
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
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(req.getContent().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // html 읽기
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

    
    // 에디터 이미지 저장
    @Override
    public void uploadClassImage(ClassImageVO vo) {
    	System.out.println(vo);
        File file = null;
        
        String path = ClassController.class.getResource("/").getPath();
        path = path.substring(0, path.lastIndexOf("mohobby"));
        path = path.substring(0, path.lastIndexOf("mohobby") + "mohobby".length());

        path += "/mohobby/mohobby/src/assets/image/class/info/";
        path += String.valueOf(vo.getFoldername());
        //path += "/";
        
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        byte[] targetBytes = null;
        FileOutputStream fos = null;
        
        if(vo.getSrc().contains("base64")) {
        	System.out.println(vo.getSrc().split(",")[1]);
        	String data = vo.getSrc().split(",")[1];
        	targetBytes = data.getBytes();
        	byte[] bytes = Base64.getDecoder().decode(targetBytes);
        	
            try {
            	file = new File(path + "/" + vo.getFilename() + ".jpg");
            	fos = new FileOutputStream(file);
            	fos.write(bytes);
            	fos.close();
            } catch(Exception e) {
            	e.printStackTrace();
            } finally {
            	if (fos != null) {
            		try {
            			fos.close();
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
            	}
            }
        } else {
        	System.out.println("기존파일");
        }
        
        
    }


}
