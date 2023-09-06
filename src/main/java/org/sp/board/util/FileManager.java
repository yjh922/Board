package org.sp.board.util;

import java.io.File;
import java.io.IOException;

import org.sp.board.exception.FileException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//스캔에 의해 자동 인스턴스 생성
@Component
public class FileManager {
	
	//확장자 구하기
	public static String getExt(String path) {
		int index=path.lastIndexOf(".");
		return path.substring(index+1, path.length());//exclusive
		
	}
	
	//최종 파일명 만들기
	public static String createFilename(String filename) {
		long time=System.currentTimeMillis();
		
		return time+"."+getExt(filename);
	}
	
	//파일 저장
	public String save(String path, String filename, MultipartFile mf) throws FileException{
		
		System.out.println(filename);
		
		//파일명 만들기
		String newName=FileManager.createFilename(filename);
		
		//확장자 구하기
		
		File file = new File(path+newName);
		
		try {
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			throw new FileException("파일저장 실패", e);
		} catch (IOException e) {
			throw new FileException("파일저장 실패", e);
		}
		
		return newName;
	}
}
















