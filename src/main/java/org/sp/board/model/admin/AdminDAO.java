package org.sp.board.model.admin;

import org.sp.board.domain.Admin;

public interface AdminDAO {

		public Admin login(Admin admin);
		public void insert(Admin admin);
		public int idCheck(String id);
		public Admin findId(Admin admin);
		public Admin findPass(Admin admin);
}
