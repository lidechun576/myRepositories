package cn.sm1234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sm1234.domain.User;

@Controller
@RequestMapping(value="/users")
public class UserController 
{
	/**
	 * 进入首页方法
	 * @date 20180506
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index()
	{
		return "index";
	}
	
	
	//用户展示
	@RequestMapping("list")
	public String list(Model model)
	{
		List<User> list = new ArrayList<User>();
		list.add(new User(1,"小张",18));
		list.add(new User(2,"小序",20));
		list.add(new User(3,"小城",22));
		//把数据存入model
		model.addAttribute("list",list);
		//跳转页面list.jsp
		return "list";
	}
	//thymeleaf变量输出
	//用户展示
		@RequestMapping("/demo2")
		public String demo2(Model model)
		{
			model.addAttribute("name", "张三");
			//跳转页面在src.main.resources.templates下的demo2页面
			return "demo2";
		}
		//条件判断
		@RequestMapping("/demo3")
		public String demo3(Model model)
		{
			model.addAttribute("gender", "女");
			model.addAttribute("grade", 3);
			//跳转页面在src.main.resources.templates下的demo2页面
			return "demo2";
		}
		//迭代遍历
		@RequestMapping("/demo4")
		public String demo4(Model model)
		{
			List<User> list = new ArrayList<User>();
			list.add(new User(1,"战三",18));
			list.add(new User(2,"李四",20));
			list.add(new User(3,"王麻子",22));
			model.addAttribute("list",list);
			return "demo2";
		}
		//域对象
		@RequestMapping("/demo5")
		public String demo5(HttpServletRequest request,Model model)
		{
			/**
			 * application全局变量,session是会话变量，比如说：
			 * (1)session：声明一个session变量。打开一个网站，session就存在了，更换页，只要是这个网站的页，session始终存在，当关闭这个网站的时候，session就结束了，这叫会话变量。。。
			 * (2)application:声明一个application变量。不关你前台有多少客户打开该网站的页，有多少客户关闭该网站的页，只要服务器端不关闭该网站，application始终存在。
			 */
			//request
			request.setAttribute("request", "request data");
			//session
			request.getSession().setAttribute("session1", "session data");
			//application
			request.getSession().getServletContext().setAttribute("application1", "application data");
			return "demo2";
		}
}
