package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {
	
	@Autowired
	private RBoardDao rDao;
	
	
	// 리스트
	public Map<String, Object> getList(int crtPage){
		
		System.out.println("RBoardService > getList()");
		
		
		// 페이지당 글 갯수
		int listCnt = 10;
		
		// 현재 페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		// 시작 글 번호
		int startRNum = (crtPage - 1) * listCnt+1;
		
		// 끝 번호
		int endRNum = (startRNum + listCnt) - 1;
		
		List<RBoardVo> boardList = rDao.rBoardList(startRNum, endRNum);
		
		///////////////////////////////////////////////
		/////////// 		페이지 계산			///////////
		///////////////////////////////////////////////
		
		// 전체 글 갯수
		int totalCnt = rDao.selectTotalCnt();
		
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		// 마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount;
		
		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) + 1;
		
		// 다음 화살표 유무
		boolean next = false;
		
		if( (listCnt*endPageBtnNo) < totalCnt) {
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCnt / (double)listCnt);
		}
		
		// 이전 화살 표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
	}
	
	// 글 읽기
	public RBoardVo readPost(int no) {
		System.out.println("RBoardService > readPost()");
		return rDao.rBoardRead(no);
	}
	
	// 계층 불러오기
	public RBoardVo getStair(int no) {
		System.out.println("RBoardService > getStair");
		return rDao.getStair(no);
	}
	
	// 게시글 포스팅
	public int postBoard(RBoardVo rVo) {
		System.out.println("RBoardService > postBoard()");
		
		// 임시
//		for(int i = 0; i < 127; i++) {
//			rVo.setTitle(i + "번째 게시글(제목)입니다.");
//			rVo.setContent(i + "번째 게시글(내용)입니다.");
//			rDao.insertBoard(rVo);
//		}
		
		return rDao.insertBoard(rVo);
	}
	
	// 답글 포스팅
	public int postReply(RBoardVo rVo) {
		System.out.println("RBoardService > postBoard()");
		String space = "&nbsp;&nbsp;&nbsp;&nbsp;";
		rDao.orderUpdate(rVo);
		
		int count;
		
		rVo.setContent(rVo.getContent().replace("\n", "<br"));
		
		for(int i = 0; i < rVo.getDepth(); i++ ) {
			space += space;
		}
		
		rVo.setTitle(space + "ㄴ" +rVo.getTitle());
		
		count = rDao.replyBoard(rVo);
		return count;
	}
	
	// 게시글 삭제
	public int deleteBoard(int no) {
		System.out.println("RBoardService > deleteBoard()");
		return rDao.deleteBoard(no);
	}

}
