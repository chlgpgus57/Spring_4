package com.iu.s4.util;

import java.io.File;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileSaver {
	
	
	//file Delete
	public boolean fileDelete(String realpath, String filename) throws Exception{
		File file = new File(realpath, filename);
		boolean check=false;
		if(file.exists()) {
			check = file.delete();
		}
		return check;
	}
	
	//1.Spring에서 제공하는 FileCopyUtils 클래스의 copy메서드 사용
	
	public String save(String realpath,MultipartFile multipartFile) throws Exception {
		
		File file = new File(realpath);
		if(!file.exists()) {
			 file.mkdirs();
			}
		
		
		String hawk = multipartFile.getOriginalFilename();
		
		hawk = hawk.substring(hawk.lastIndexOf("."));
		Calendar ca = Calendar.getInstance();
		Long name = ca.getTimeInMillis();
		file = new File(realpath, name.toString()+hawk);
		
		FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		
		return name.toString()+hawk;
	}
	//2.MultipartFile transferTo 메서드 사용
	public String save2(String realpath, MultipartFile multipartFile) throws Exception{
		
		File file = new File(realpath);
		if(!file.exists()) {
			 file.mkdirs();
			}
		
		//저장할 파일명
		String filename =UUID.randomUUID().toString();
		filename= filename+"_"+multipartFile.getOriginalFilename();
		
		file = new File(realpath, filename);
		

		multipartFile.transferTo(file);
		
		return filename;
	}

	
	//3. Io Stream 사용
	public String save3(String realpath, MultipartFile multipartFile) throws Exception{
		
		File file = new File(realpath);
		if(!file.exists()) {
			 file.mkdirs();
			}
		
		//저장할 파일명
		String filename =UUID.randomUUID().toString();
		filename= filename+"_"+multipartFile.getOriginalFilename();
		
		file = new File(realpath, filename);
		
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		
		return filename;
	}

}
