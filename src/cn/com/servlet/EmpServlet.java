package cn.com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.com.util.servlet.DispatcherServlet;
import cn.com.util.split.SplitPageUtils;
import cn.com.vo.Emp;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/EmpServlet/*")
// 列表操作：http://localhost/DispatcherProject/EmpServlet/list
// 增加操作：http://localhost/DispatcherProject/EmpServlet/add
// 修改操作：http://localhost/DispatcherProject/EmpServlet/edit
public class EmpServlet extends DispatcherServlet {
	private Emp emp = new Emp() ;
	public Emp getEmp() {
		return emp;
	}
	public String list() 
			throws ServletException, IOException { 
		String urlKey = "EmpServlet.list.servlet" ;
		SplitPageUtils spu = new SplitPageUtils(super.request) ;
		int currentPage = spu.getCurrentPage() ;
		int lineSize = spu.getLineSize() ;
		int empCount = 55 ;	// 这个代表总记录数
		List<Emp> all = new ArrayList<Emp>() ;
		for (int x = (currentPage - 1) * lineSize; x < currentPage * lineSize; x++) {
			Emp emp = new Emp() ;
			emp.setEmpno(1000 + x);
			emp.setEname("雇员姓名 - " + spu.getColumn() + " - " + spu.getKeyWord() + " - " + x);
			all.add(emp) ;
		}
		request.setAttribute("allEmps", all);	// 这个值需要传递给JSP页面
		super.setSplitPage(urlKey, empCount, spu); 
		System.out.println("***** 【Servlet输出】列表雇员信息。" + super.request.getRemoteAddr());
		return "emp.list.page" ;
	}
	public String remove()
			throws ServletException, IOException {
		System.out.println("***** 【Servlet输出】删除雇员信息。");
		return "forward.page" ;
	}
	public String edit()
			throws ServletException, IOException {
		System.out.println("***** 【Servlet输出】编辑雇员信息。");
		return "forward.page" ;
	}
	public String add()
			throws ServletException, IOException {
		System.out.println("***** 【Servlet输出】增加雇员信息。" + this.emp); 
		if (super.isUploadFile()) {	// 现在有文件上传
			String fileName = super.createSingleFileName() ;
			// 而后这个文件名称一定手工设置到VO类对象之中
			super.saveUploadFile(fileName) ;	// 保存文件
		}
		super.setUrlAndMsg("emp.add.page", "vo.add.success.msg");
		return "forward.page" ;
	}
	@Override
	public String getType() {
		return "雇员";
	}
	@Override
	public String getUploadDir() {
		return "/upload/emp/";
	}
	@Override
	public String getDefaultColumn() {
		return "雇员姓名:ename|雇员职位:job|联系电话:phone|邮箱:email";
	}
}
