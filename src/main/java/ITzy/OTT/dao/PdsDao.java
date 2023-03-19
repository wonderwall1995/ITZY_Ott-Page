package ITzy.OTT.dao;

import java.util.List;

import ITzy.OTT.dto.BbsParam;
import ITzy.OTT.dto.PdsDto;

public interface PdsDao {
	List<PdsDto> pdslist(BbsParam pds);
	int getAllPds(BbsParam pds);
	int uploadPds(PdsDto dto);
	void downcount(int seq);
	PdsDto getPds(int seq);
	int updatePds(PdsDto dto);
	PdsDto deletePds(int seq);
}
