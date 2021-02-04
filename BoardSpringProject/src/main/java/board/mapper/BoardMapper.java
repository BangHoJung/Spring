package board.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDTO;
import board.dto.CommentDTO;
import board.dto.FileDTO;
import board.dto.QnaDTO;
import board.vo.MemberVO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> searchAllBoardDTO(HashMap<String, Object> map);

	int countAllBoard();

	List<Map<String, Object>> countAllComment();

	BoardDTO searchBoardDTO(int bno);

	List<CommentDTO> searchAllCommentDTO(int bno);

	List<FileDTO> searchFileList(HashMap<String, Object> map);

	int addBoardCount(int bno);

	int insertBoardComment(CommentDTO commentDTO);

	List<CommentDTO> searchSortCommentDTO(HashMap<String, Object> map);

	int addLikeBoardDTO(int bno);

	int addHateBoardDTO(int bno);

	int searchLikeCount(int bno);

	int searchHateCount(int bno);

	int addLikeCommentDTO(int cno);

	int addHateCommentDTO(int cno);

	int searchCommentLikeCount(int cno);

	int searchCommentHateCount(int cno);

	int insertBoardDTO(BoardDTO board);

	int nextBno();

	void insertFileList(FileDTO fileDTO);

	BoardDTO prevBoardDTO(int bno);

	BoardDTO nextBoardDTO(int bno);

}
