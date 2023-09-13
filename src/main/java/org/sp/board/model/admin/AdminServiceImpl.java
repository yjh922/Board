package org.sp.board.model.admin;

import org.sp.board.domain.Admin;
import org.sp.board.exception.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDAO adminDAO;
	
	public Admin login(Admin admin) throws AdminException{
		
		Admin dto=adminDAO.login(admin);
		if(dto==null) {
			throw new AdminException("로그인 정보가 올바르지 않습니다.");
		}
		return dto;
	}
	
	public void insert(Admin admin) throws AdminException{
		adminDAO.insert(admin);
	}
	
	public int idCheck(String id) {
		int result=adminDAO.idCheck(id);
		return result;
	}
	
	public Admin findId(Admin admin) {
		 return adminDAO.findId(admin);
		
	}
	
	@Override
	public Admin findPass(Admin admin) {
		
		return adminDAO.findPass(admin);
	}
}
