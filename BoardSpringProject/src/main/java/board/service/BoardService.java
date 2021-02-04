package board.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import board.dto.BoardDTO;
import board.dto.CommentDTO;
import board.dto.FileDTO;
import board.dto.QnaDTO;
import board.mapper.BoardMapper;
import board.vo.PagingVO;
import exception.BoardException;

@Service @Alias("bser")
public class BoardService {

	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}

	public List<BoardDTO> searchAllBoardDTO(int currPage, String sort) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PagingVO page = new PagingVO(countAllBoard(), currPage);
		map.put("page", page.getCountPageContent());
		map.put("currPage", currPage);
		map.put("sort", sort);
		return mapper.searchAllBoardDTO(map);
	}
	
	public int countAllBoard() {
		return mapper.countAllBoard();
	}

	public HashMap<Integer, Integer> countAllComment() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		//return (HashMap<Integer, Integer>) session.selectList("mapper.CommentMapper.countAllComment");
//		 for(int i=0; i<list.size();i++) {
//			Iterator<Map.Entry<Integer, Integer>> it = list.get(i).entrySet().iterator();
//			while(it.hasNext()) {
//				Entry<Integer, Integer> itv = it.next();
//				map.put(itv.getKey(), itv.getValue());
//			}
//		 }
		List<Map<String, Object>> list = mapper.countAllComment();
		for(int i=0;i<list.size();i++) {
			map.put(Integer.parseInt(list.get(i).get("BNO").toString()),Integer.parseInt(list.get(i).get("CNT").toString()));
		}
		
		 return map;
	}

	public BoardDTO searchBoardDTO(int bno) throws BoardException {
		int count = mapper.addBoardCount(bno);
		if(count == 1) {
			return mapper.searchBoardDTO(bno);
		}
		else throw new BoardException("아이디가 올바르지 않습니다");
	}

	public List<CommentDTO> searchAllCommentDTO(int bno) {
		return mapper.searchAllCommentDTO(bno);
	}

	public List<FileDTO> searchFileList(int bno, String writer) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("writer", writer);
		return mapper.searchFileList(map);
	}

	public int insertBoardComment(CommentDTO commentDTO) {
		return mapper.insertBoardComment(commentDTO);
	}

	public List<CommentDTO> searchSortCommentDTO(int bno, String sort) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("sort", sort);
		return mapper.searchSortCommentDTO(map);
	}

	public int addLikeHateBoardDTO(int bno, String lh) {
		if(lh.equals("like")) {
			return mapper.addLikeBoardDTO(bno);
		}
		else {
			return mapper.addHateBoardDTO(bno);
		}
	}

	public int searchLikeHateCount(int bno, String lh) {
		if(lh.equals("like")) {
			return mapper.searchLikeCount(bno);
		}
		else {
			return mapper.searchHateCount(bno);
		}
	}

	public int addLikeHateCommentDTO(int cno, String lh) {
		if(lh.equals("clike")) {
			return mapper.addLikeCommentDTO(cno);
		}
		else {
			return mapper.addHateCommentDTO(cno);
		}
	}

	public int searchCommentLikeHateCount(int cno, String lh) {
		if(lh.equals("clike")) {
			return mapper.searchCommentLikeCount(cno);
		}
		else {
			return mapper.searchCommentHateCount(cno);
		}
	}

	public int insertBoardDTO(BoardDTO board) throws BoardException {
		
		int count =  mapper.insertBoardDTO(board);
		System.out.println("count : " + count);
		if(count == 0) throw new BoardException("board insert error");
		return count;
	}

	public void insertFileList(ArrayList<FileDTO> fList) {
		for(int i=0;i<fList.size();i++) {
			mapper.insertFileList(fList.get(i));
		}
	}

	public int nextBno() {
		return mapper.nextBno();
	}

	public BoardDTO prevBoardDTO(int bno) {
		return mapper.prevBoardDTO(bno);
	}
	
	public BoardDTO nextBoardDTO(int bno) {
		return mapper.nextBoardDTO(bno);
	}

	
}
