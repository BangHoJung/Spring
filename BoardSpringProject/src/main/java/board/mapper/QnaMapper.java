package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.QnaDTO;

@Mapper
public interface QnaMapper {

	List<QnaDTO> searchQnaList(HashMap<String, Object> map);

	int insertQnaDTO(QnaDTO dto);

	QnaDTO searchQnaDTO(int qid);

	int updateQnaStatus(HashMap<String, Object> map);

	int updateQnaResponse(HashMap<String, Object> map);
}
