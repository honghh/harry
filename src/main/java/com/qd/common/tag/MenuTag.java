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
public class MenuTag extends TagSupport {
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

	private static String getChildOfTree(Menu parent, int level,
			List<Menu> menuList) {
		StringBuffer menuString = new StringBuffer();
		String href = "";
		if (!parent.hasPermisson())
			return "";
		if (level > 0) {// level 为0是功能菜单

			menuString.append("<li>");

			ServletContext context = SpringContextHolder
					.getBean(ServletContext.class);
			if (parent.getHref() != null && parent.getHref().length() > 0) {

				if (parent.getHref().startsWith("http://")) {// 如果是互联网资源
					href = parent.getHref();
				} else if (parent.getHref().endsWith(".html")
						&& !parent.getHref().endsWith("ckfinder.html")) {// 如果是静态资源并且不是ckfinder.html，直接访问不加adminPath
					href = context.getContextPath() + parent.getHref();
				} else {
					href = context.getContextPath() + Global.getAdminPath()
							+ parent.getHref();
				}
			}
		}

		if ((parent.getHref() == null || parent.getHref().trim().equals(""))
				&& parent.getIsShow().equals("1")) {// 如果是父节点且显示
			if (level > 0) {
				menuString.append("<a href=\"" + href + "\"><i class=\"fa "
						+ parent.getIcon()
						+ "\"></i> <span class=\"nav-label\">"
						+ parent.getName()
						+ "</span><span class=\"fa arrow\"></span></a>");
			}
			if (level == 1) {
				menuString.append("<ul class=\"nav nav-second-level\">");
			} else if (level == 2) {
				menuString.append("<ul class=\"nav nav-third-level\">");
			} else if (level == 3) {
				menuString.append("<ul class=\"nav nav-forth-level\">");
			} else if (level == 4) {
				menuString.append("<ul class=\"nav nav-fifth-level\">");
			}
			for (Menu child : menuList) {
				if (child.getParentId().equals(parent.getId())
						&& child.getIsShow().equals("1")) {
					menuString
							.append(getChildOfTree(child, level + 1, menuList));
				}
			}
			if (level > 0) {
				menuString.append("</ul>");
			}
		} else {
			menuString.append("<a class=\"J_menuItem\"  href=\"" + href
					+ "\" ><i class=\"fa " + parent.getIcon()
					+ "\"></i> <span class=\"nav-label\">" + parent.getName()
					+ "</span></a>");
		}
		if (level > 0) {
			menuString.append("</li>");
		}

		return menuString.toString();
	}

}
