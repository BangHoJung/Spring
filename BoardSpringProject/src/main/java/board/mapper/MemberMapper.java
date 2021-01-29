package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.vo.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO loginMember(HashMap<String, Object> map);

	List<MemberVO> memberManageSearch(HashMap<String, Object> map);
	
}
