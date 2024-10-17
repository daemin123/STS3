package com.example.app.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoServiceImpl {
	
//	@Autowired
//	private MemoDaoImpl memoDaoImpl;
//	
//	
//	public boolean memoRegistration(MemoDto memoDto) {
//		memoDaoImpl.Insert(memoDto);
//		return true;
//	}
	
	@Autowired
	private MemoMapper memoMapper;
	
	
	public boolean memoRegistration(MemoDto memoDto) throws Exception{	
		
	
		memoMapper.Insert(memoDto);
		return true;
	
			
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void addMemoTx(MemoDto memoDto) throws Exception{
		log.info("MemoService addMemoTx() invoke..");
		memoMapper.Insert(memoDto);	//RollBack x 
		log.info(memoDto.toString());
		memoDto.setId(memoDto.getId()-1);
		memoMapper.Insert(memoDto);	//PK 중복 오류발생!
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean modifiedMemo(MemoDto dto) throws Exception {

		memoMapper.Update(dto);
		return true;
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean removeMemo(int id) throws Exception {
		return memoMapper.Delete(id)>0;	
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<MemoDto> getAllMemo(MemoDto memoDto) throws Exception{
		
		return memoMapper.GetAllMemo(memoDto);	
		
	}
	
	
}
