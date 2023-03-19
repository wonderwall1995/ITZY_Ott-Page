package ITzy.OTT.dao;

import java.util.List;

import ITzy.OTT.dto.MemberDto;

public interface MemberDao {
	List<MemberDto> allMember();
	int idCheck(String id);
	int addMember(MemberDto dto);
	MemberDto login(MemberDto dto);
	int BizAadmember(MemberDto dto);
	MemberDto Bizlogin(MemberDto dto);
}
