package org.sp.board.model.admin;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.sp.board.domain.Admin;
import org.sp.board.mybatis.MybatisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisAdminDAO implements AdminDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Admin login(Admin admin) {
		
		Admin dto=sqlSessionTemplate.selectOne("Admin.login", admin);
		
		
		return dto;
	}
	
	public void insert(Admin admin) {
		
		int result=sqlSessionTemplate.insert("Admin.insert", admin);
	}
	
	//아이디 중복체크
	public int idCheck(String id) {
		int result=sqlSessionTemplate.selectOne("Admin.idCheck", id);
		return result;
	}
	
	//아이디 찾기
	@Override
	public Admin findId(Admin admin) {
		return sqlSessionTemplate.selectOne("Admin.findId", admin);
		
	}
	
	//비밀번호 찾기
	@Override
	public Admin findPass(Admin admin) {
		return sqlSessionTemplate.selectOne("Admin.findPass", admin);
	}
}
