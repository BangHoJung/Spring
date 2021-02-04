package board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.QnaDTO;
import board.mapper.QnaMapper;

@Service
public class QnaService {
	private QnaMapper mapper;

	public QnaService(QnaMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<QnaDTO> searchQnaList(String writer, int nextPage, String grade_name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("writer", writer);
		map.put("nextPage", nextPage);
		map.put("grade_name", grade_name);
		return mapper.searchQnaList(map);
	}

	public void insertQnaDTO(QnaDTO dto) throws Exception {
		int count = mapper.insertQnaDTO(dto);
		if(count == 0) {
			throw new Exception("문의 등록에 실패했습니다");
		}
		
	}

	public QnaDTO searchQnaDTO(int qid, int status) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qid", qid);
		map.put("status", status);
		int count = mapper.updateQnaStatus(map);
		if(count == 0) {
			return null;
		}
		return mapper.searchQnaDTO(qid);
	}

	public int updateQnaResponse(int qid, String answer, int status) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qid", qid);
		map.put("status", status);
		map.put("answer", answer);
		int count = mapper.updateQnaResponse(map);
		if(count != 0) {
			count = mapper.updateQnaStatus(map);
		}
		return count;
	}
}
