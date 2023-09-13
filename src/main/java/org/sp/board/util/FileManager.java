package org.sp.board.util;

import java.io.File;
import java.io.IOException;

import org.sp.board.exception.FileException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	//확장자 구하기
	public static String getExt(String path) {
		int index=path.lastIndexOf(".");
		
		return path.substring(index+1, path.length()); //exclusive
		
	}

	//파일명 생성
	public static String createFilename(String filename) {
		long time=System.currentTimeMillis();
		
		return time+"."+getExt(filename);
	}
	
	//파일 저장
	public String save(String path, String filename, MultipartFile mf) throws FileException{
		
		//파일명 만들기
		String newName=FileManager.createFilename(filename);
		
		/*
		 * if (path.equals("파일명")) { throw new FileException("이미지 저장 실패"); }
		 */
		
		
		File file=new File(path+newName);
		try {
			
			
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			throw new FileException("이미지 저장 실패", e);
			
		} catch (IOException e) {
			throw new FileException("이미지 저장 실패", e);
			
		} 
		
		return newName;
	}
	
	//파일 삭제
	public void remove(String path) throws FileException{
		
		File file = new File(path);
		boolean result=file.delete();//파일 삭제
		if(result==false) {
			throw new FileException("파일 삭제 실패입니다.");
		}
	}
	
}











