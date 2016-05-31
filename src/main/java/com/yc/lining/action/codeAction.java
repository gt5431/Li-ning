package com.yc.lining.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

@Controller("codeAction")
public class codeAction implements SessionAware{

	private Map<String, Object> session;
	
	private int t = 0;
	
	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	//产生随即的字体  
	private Font getFont() {  
		Random random = new Random();  
		Font font[] = new Font[5];  
		font[0] = new Font("Ravie", Font.PLAIN, 24);  
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, 24);  
		font[2] = new Font("Forte", Font.PLAIN, 24);  
		font[3] = new Font("Wide Latin", Font.PLAIN, 24);  
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 24);  
		return font[random.nextInt(5)];  
	} 

	public void code() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置响应头 Content-type类型  
		response.setContentType("image/jpeg");  
		// 以下三句是用于设置页面不缓存  
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0);  

		OutputStream os = response.getOutputStream();  
		int width = 90, height = 30;  
		// 建立指定宽、高和BufferedImage对象  
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  

		Graphics g = image.getGraphics(); // 该画笔画在image上  
		Color c = g.getColor(); // 保存当前画笔的颜色，用完画笔后要回复现场  
		g.fillRect(0, 0, width, height);  


		char[] ch = "123456789".toCharArray(); // 随即产生的字符串 不包括 i l(小写L) o（小写O） 1（数字1）0(数字0)  
		int length = ch.length; // 随即字符串的长度  
		String sRand = ""; // 保存随即产生的字符串  
		Random random = new Random();  
		for (int i = 0; i < 4; i++) {  
			// 设置字体  
			g.setFont(getFont());  
			// 随即生成0-9的数字  
			String rand = new Character(ch[random.nextInt(length)]).toString();  
			sRand += rand;  
			// 设置随机颜色  
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));  
			g.drawString(rand, 20 * i + 6, 25);  
		}  
		//产生随即干扰点  
		for (int i = 0; i < 20; i++) {  
			int x1 = random.nextInt(width);  
			int y1 = random.nextInt(height);  
			g.drawOval(x1, y1, 2, 2);  
		}  
		g.setColor(c); // 将画笔的颜色再设置回去  
		g.dispose();  

		//将验证码记录到session  
		session.put("yanzhengma", sRand);  
		// 输出图像到页面  
		ImageIO.write(image, "JPEG", os);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
