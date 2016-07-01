package com.sys.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.sys.dao.UserDao;
import com.sys.po.User;

@Service
public class UserBiz extends BaseBiz<UserDao, Integer, User> {
	
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
	
	/**更新用户，但不更新用户密码
	 * */
	@Override
	public void update(User user) {
		User oldUser = dao.findById(user.getId());
		user.setPassword(oldUser.getPassword());	//设置用户的原密码
		dao.update(user);
	}
	
	/**密码验证
	 * @return 验证通过返回true,失败返回false
	 * */
	public String checkPwd(Integer userId, String oldPassword){
		List<?> passwords = dao.find("select password from User where id =:id",new String[]{"id"},new Integer[]{userId});
		String password = (String)passwords.get(0);
		if(password.equals(oldPassword))
			return "{\"valid\":true}";
		else
			return "{\"valid\":false}";
	}
	
	/**修改密码
	 * */
	public void modifyPwd(Integer userId, String password, Integer updateBy){
		dao.executeUpdate("update User set password =:password, updateBy =:updateBy where id =:userId",
				new String[]{"password","updateBy","userId"}, new Object[]{password, updateBy,userId});
	}
	
	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param useName 模糊查询
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<User> findByPageAndParams(int offset, int limit, String userName) {
		BootTablePageDto<User> bt = new BootTablePageDto<User>();
		String[] paramNames = new String[]{"userName"};
		String[] values = new String[]{'%'+userName+'%'};
		Long total = dao.getCount("select count(1) from User where userName like :userName", paramNames, values);
		bt.setTotal(total);
		bt.setRows((List<User>)dao.findByPage("from User where userName like :userName", offset, limit, paramNames, values));
		return bt;
	}
}
