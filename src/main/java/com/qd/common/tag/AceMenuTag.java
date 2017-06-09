package com.qd.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.qd.common.config.Global;
import com.qd.common.utils.SpringContextHolder;
import com.qd.modules.sys.entity.Menu;
import com.qd.modules.sys.utils.UserUtils;


/**
 * 
 * 类描述：菜单标签
 * 
 * Harry
 * 
 * @date： 日期：2017-04-06 
 * @version 1.0
 */
public class AceMenuTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	protected Menu menu;// 菜单Map

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			String menu = (String) this.pageContext.getSession().getAttribute(
					"menu");
			if (menu != null) {
				out.print(menu);
			} else {
				menu = end().toString();
				out.print(menu);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		sb.append(getChildOfTree(menu, 0, UserUtils.getMenuList()));

		System.out.println(sb);
		return sb;

	}

	private static String getChildOfTree(Menu parent, int level,  List<Menu> menuList) {
		StringBuffer menuString = new StringBuffer();
		String href = "";
		if (!parent.hasPermisson())return "";
		ServletContext context = SpringContextHolder.getBean(ServletContext.class);
		if (parent.getHref() != null && parent.getHref().length() > 0) {
			if (parent.getHref().startsWith("http://")) {// 如果是互联网资源
				href = parent.getHref();
			} else if ((parent.getHref().endsWith(".html")) && !parent.getHref().endsWith("ckfinder.html")) {// 如果是静态资源并且不是ckfinder.html，直接访问不加adminPath
				href = context.getContextPath() + parent.getHref();
			} else {
				href = context.getContextPath() + Global.getAdminPath()+ parent.getHref();
			}
		}
		//修复Bug,判断根节点
		boolean isLeaf = true;
		for (Menu child : menuList) {
			if (child.getParentId().equals(parent.getId())&&child.getIsShow().equals("1")) {
				isLeaf = false;
				break;
			}
		}
		if(level > 0 && parent.getIsShow().equals("1")){
			menuString.append("<li id=\""+parent.getId()+"\">");
			if(isLeaf){
				menuString.append("<a class=\"J_menuItem\" href=\"" + href + "\">");
				menuString.append("<i class=\"menu-icon fa " + parent.getIcon() + "\"></i>");
				menuString.append("<span class=\"menu-text\">"+parent.getName()+"</span>");
				menuString.append("<b class=\"arrow\"></b>");
				menuString.append("</a>");
			}else{
				menuString.append("<a  href=\"" + href + "\" class=\"dropdown-toggle\">");
				menuString.append("<i class=\"menu-icon fa " + parent.getIcon() + "\"></i>");
				menuString.append("<span class=\"menu-text\">"+parent.getName()+"</span>");
				menuString.append("<b class=\"arrow fa fa-angle-down\"></b>");
				menuString.append("</a>");
				menuString.append("<b class=\"arrow\"></b>");
			}
		}
		
		if(!isLeaf && parent.getIsShow().equals("1")){
			if (level == 0) {
				menuString.append("<ul class=\"nav nav-list\">");
			} else {
				menuString.append("<ul class=\"submenu\">");
			}
			for (Menu child : menuList) {
				if (child.getParentId().equals(parent.getId())&&child.getIsShow().equals("1")) {
					menuString.append(getChildOfTree(child, level + 1, menuList));
				}
			}
			menuString.append("</ul>");
		}
		if (level > 0 && parent.getIsShow().equals("1")) {
			menuString.append("</li>");
		}
		return menuString.toString();
	}

}
