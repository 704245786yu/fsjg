package com.sys.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dto.BootTablePageDto;
import com.sys.dao.UserDao;
import com.sys.po.User;

@Service
public class UserBiz extends BaseBiz<UserDao, Integer, User>{

	/**更新用户更新除密码外的所有字段。
	 * */
	@Override
	public void update(User user) {
		User oldUser = dao.findById(user.getId());
		user.setPassword(oldUser.getPassword());//设置原始密码
		dao.update(user);
	}
	
	/**修改密码
	 * 先比较用户输入的原密码是否确实与数据库中的密码相同，若相同则更新，不同则不更新
	 * @return 1：正常 2：原密码输入错误
	 * */
	public Integer modifyPwd(Integer userId, String oldPwd, String password, Integer updateBy){
		User user = dao.findById(userId);
		if(oldPwd.equals(user.getPassword())){
			user.setPassword(password);
			user.setUpdateBy(updateBy);
			dao.update(user);
			return 1;
		}else{
			return 2;
		}
	}

	/**用户登陆检测
	 * @return 查询不到返回null。
	 * */
	@SuppressWarnings("unchecked")
	public User loginCheck(String userName, String password){
		List<User> users = (List<User>)dao.find("from User where userName =:userName and password =:password",
				new String[]{"userName", "password"},new String[]{userName, password});
		if(users.size() != 1)
			return null;
		else
			return users.get(0);
	}
	
	/**查询用户信息
	 * 限制：只能查看本组织和子组织的用户。
	 * */
//	public BootTablePageDto<User> findByOrgWithPage(int offset, int pageSize, int organizationId){
//		BootTablePageDto<User> bt = new BootTablePageDto<User>();
//		List<Integer> orgIds = organizationDao.getDescendantId(organizationId);
//		orgIds.add(0, organizationId);
//		Long total = dao.getCountByOrg(orgIds);
//		bt.setTotal(total);
//		bt.setRows(dao.findByOrgWithPage(offset, pageSize, orgIds));
//		return bt;
//	}
}
