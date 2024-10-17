package com.example.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.dto.BoardDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

	//Board Post Page Mapping
	@GetMapping("/post")
	public void board_post() {
		log.info("GET /board/post");
	}
	
	//
	@PostMapping("/post")
	public void board_post_2(BoardDto dto) {
		log.info("POST /board/post.." +dto );
	}
	
	
	@GetMapping("/imagePost")
	public void imagePost() {
		log.info("GET /board/imagePost");
	}
	
	@PostMapping("/imagePost")
	public void imagePost_2(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {
		log.info("POST /board/imagePost" + files.length);
		
		String UPLOAD_PATH="c:\\upload";
		
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
}





