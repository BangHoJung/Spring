package board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.mapper.MemberMapper;
import board.vo.MemberVO;

@Service
public class MemberService {

	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public MemberVO loginMember(String id, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		return mapper.loginMember(map);
	}

	public List<MemberVO> memberManageSearch(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.memberManageSearch(map);
	}

	public MemberVO searchMember(String id) {
		return mapper.searchMember(id);
	}

	public void updateMember(String id, String pass, String name, int age) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		map.put("name", name);
		map.put("age", age);
		int count =  mapper.updateMember(map);
		
	}

	public int memberManageUpdate(MemberVO memberVO) {
		return mapper.memberManageUpdate(memberVO);
		
	}

	public int memberManageDelete(String id) {
		return mapper.memberManageDelete(id);
	}

	public void insertLog(String log_date, String code_number, String message) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("code_number", code_number);
		map.put("message", message);
		int count =  mapper.insertLog(map);
		
	}
	
}
