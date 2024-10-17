package com.example.app.restcontroller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/memo")
public class MemoRestController {

	@Autowired
	private MemoServiceImpl memoServiceImpl;
	
	@GetMapping(value="/add_get")
	public Boolean add_get(MemoDto memoDto)  {
		
		log.info("GET /memo/.." + memoDto);
		boolean isadded=false;
		try {
			isadded =  memoServiceImpl.memoRegistration(memoDto);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("ISADDED.. : " + isadded);
		return isadded;
	}
	
	@PostMapping(value="/add_post")
	public ResponseEntity<String> add_post(@RequestBody MemoDto memoDto) throws UnsupportedEncodingException  {
		
		log.info("POST /memo/.." + memoDto);
		boolean isadded=false;
		try {
			
			isadded =  memoServiceImpl.memoRegistration(memoDto);
			if(isadded) {
				return new ResponseEntity( "POST ADD 성공".getBytes("UTF-8") ,HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		log.info("ISADDED.. : " + isadded);

		return new ResponseEntity( "POST ADD 실패".getBytes("UTF-8") ,HttpStatus.BAD_GATEWAY);
	}	
	
	@PutMapping(value="/put")
	public ResponseEntity<String> put(@RequestBody MemoDto dto) {
		
		log.info("PUT /memo/put.." + dto);
		boolean isUpdated=false;
		
		try {
			isUpdated = memoServiceImpl.modifiedMemo(dto);
			if(isUpdated)
				return new ResponseEntity("Put Success..",HttpStatus.OK);	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity("Put Fail..",HttpStatus.BAD_GATEWAY);
		
	}
	@PatchMapping(value="/patch")
	public ResponseEntity<String> patch(@RequestBody MemoDto dto) {
		
		log.info("PATCH /memo/patch.." + dto);
		boolean isUpdated=false;

		try {
			isUpdated = memoServiceImpl.modifiedMemo(dto);
			if(isUpdated)
				return new ResponseEntity("PATCH Success..",HttpStatus.OK);	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity("PATCH Fail..",HttpStatus.BAD_GATEWAY);
	}	
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> delete(int id) throws UnsupportedEncodingException{
		log.info("DELETE /memo/delete..."+id);
		
		boolean isDel=false;
		
		try {
			isDel =memoServiceImpl.removeMemo(id);
			if(isDel)
				return new ResponseEntity("DELETE 성공".getBytes("UTF-8"),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity("DELETE 실패".getBytes("UTF-8") , HttpStatus.BAD_GATEWAY);
	}
	
	@GetMapping("/all")
	public ResponseEntity< List<MemoDto> > getAllMemo(MemoDto memoDto) throws Exception {
		log.info("GET /memo/all..."+memoDto);
		List<MemoDto> list =  memoServiceImpl.getAllMemo(memoDto);
		return new ResponseEntity(list, HttpStatus.OK);	
	}
	
	
	
	
}




