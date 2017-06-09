package com.qd.modules.ws.utils;

import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;

public class MsgUtil {

	public static String formatTime(long msgTime) throws Exception {

		// 微信消息中时间单位为妙，转化为毫秒
		msgTime = msgTime * 1000L;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date(msgTime));
	}

	public static XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("-", "_")) {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
					if ("CreateTime".equals(name)) {
						cdata = false;
					}
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
						cdata = true;
					}
				}
			};
		}
	});

	public static String Map2JsonString(Map map) throws Exception {
		String str = "";
		if (map != null) {
			JSONObject json = JSONObject.fromObject(map);
			str = json.toString();
		}
		return str;
	}
}