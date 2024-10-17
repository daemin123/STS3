package com.example.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/upload")
public class UploadController {
	
	String UPLOAD_PATH="c:\\upload";
	
	@GetMapping("/file")
	public void up_file() {
		log.info("GET /upload/file..");
	}
	
	//단일파일 업로드
	@PostMapping("file")
	public void up_file_post(@RequestParam("singleFile") MultipartFile file) throws IllegalStateException, IOException {
		log.info("POST /upload/file.."+file);	
		
		
		//개별폴더 생성(UUID)
		String subDir = UPLOAD_PATH + File.separator + UUID.randomUUID();
		File dir = new File(subDir);
		if(!dir.exists())
			dir.mkdirs();//폴더 생성
		
		
		log.info("FILENAME : " + file.getOriginalFilename());
		log.info("FILESIZE : " + file.getSize() + " byte");
		
		//파일명 추출
		String filename = file.getOriginalFilename();
		//파일객체 생성
		File fileObject = new File(subDir,filename);
		//업로드
		file.transferTo(fileObject);
		
	}
	
	
	@GetMapping("/files")
	public void up_files_2() {
		log.info("GET /upload/files..");
	}
	
	
	@PostMapping("/files")
	public void up_files_2_post(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {
		log.info("POST /upload/files.."+files);
		
		//개별폴더 생성(UUID)
		String subDir = UPLOAD_PATH + File.separator + UUID.randomUUID();
		File dir = new File(subDir);
		if(!dir.exists())
			dir.mkdirs();//폴더 생성
		
		
		for(MultipartFile file : files) {
			log.info("-------------------");
			log.info("FILENAME : " + file.getOriginalFilename());
			log.info("FILESIZE : " + file.getSize() + " byte");
		
			//파일명 추출
			String filename = file.getOriginalFilename();
			//파일객체 생성
			File fileObject = new File(subDir,filename);
			//업로드
			file.transferTo(fileObject);
		}
		
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("GET /upload/list..");
		
		File base_dir = new File(UPLOAD_PATH);
		File[] sub_dir =  base_dir.listFiles();
		for(File dir : sub_dir) {
			log.info("SUB_DIR : " + dir);	
			
			File tmp = new File(dir.getPath());
			for(File file :tmp.listFiles()) {
				log.info("FILE : " + file);
			}
		}
		
		model.addAttribute("root",base_dir);	
	}
	
	

	
	
}
