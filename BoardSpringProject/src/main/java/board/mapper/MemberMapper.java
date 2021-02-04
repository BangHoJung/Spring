package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.QnaDTO;
import board.vo.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO loginMember(HashMap<String, Object> map);

	MemberVO searchMember(String id);

	int updateMember(HashMap<String, Object> map);
	
	List<MemberVO> memberManageSearch(HashMap<String, Object> map);

	int memberManageUpdate(MemberVO memberVO);

	int memberManageDelete(String id);

	int insertLog(HashMap<String, Object> map);

}
