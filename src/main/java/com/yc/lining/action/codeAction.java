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

	//�����漴������  
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
		// ������Ӧͷ Content-type����  
		response.setContentType("image/jpeg");  
		// ������������������ҳ�治����  
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0);  

		OutputStream os = response.getOutputStream();  
		int width = 90, height = 30;  
		// ����ָ�����ߺ�BufferedImage����  
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  

		Graphics g = image.getGraphics(); // �û��ʻ���image��  
		Color c = g.getColor(); // ���浱ǰ���ʵ���ɫ�����껭�ʺ�Ҫ�ظ��ֳ�  
		g.fillRect(0, 0, width, height);  


		char[] ch = "123456789".toCharArray(); // �漴�������ַ��� ������ i l(СдL) o��СдO�� 1������1��0(����0)  
		int length = ch.length; // �漴�ַ����ĳ���  
		String sRand = ""; // �����漴�������ַ���  
		Random random = new Random();  
		for (int i = 0; i < 4; i++) {  
			// ��������  
			g.setFont(getFont());  
			// �漴����0-9������  
			String rand = new Character(ch[random.nextInt(length)]).toString();  
			sRand += rand;  
			// ���������ɫ  
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));  
			g.drawString(rand, 20 * i + 6, 25);  
		}  
		//�����漴���ŵ�  
		for (int i = 0; i < 20; i++) {  
			int x1 = random.nextInt(width);  
			int y1 = random.nextInt(height);  
			g.drawOval(x1, y1, 2, 2);  
		}  
		g.setColor(c); // �����ʵ���ɫ�����û�ȥ  
		g.dispose();  

		//����֤���¼��session  
		session.put("yanzhengma", sRand);  
		// ���ͼ��ҳ��  
		ImageIO.write(image, "JPEG", os);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
