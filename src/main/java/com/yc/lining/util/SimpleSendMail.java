package com.yc.lining.util;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
 * 简单的java 邮件发送类SimpleSendMail
 * @author jp
 * @version v1.0
 * @since 2015-01-02
 */

public class SimpleSendMail {
	private Session session; //与邮件服务器连接对象
	private String username; //发送人邮箱

	/**
	 * 只能处理 邮件服务器 " smtp.xxxx.xxx" 与  "邮箱名@xxxx.xxx" 相同情况
	 * @param smtpHostName  信件服务器
	 * @param username 发送人邮箱
	 * @param password 邮箱密码
	 */
	public SimpleSendMail(final String username, final String password)
	{
		this(("smtp." + username.split("@")[1]), username, password);
	}

	/**
	 * 
	 * @param smtpHostName  信件服务器
	 * @param username 发送人邮箱
	 * @param password 邮箱密码
	 */
	public SimpleSendMail(final String smtpHostName, final String username, final String password)
	{
		this.username = username;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true"); //设置发送邮件是否要账号和密码
		props.put("mail.smtp.host", smtpHostName); //设置发送邮件服务器

		//与邮件服务器建立连接, Authenticator连接验证
		session = Session.getInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * 只能给一个人或多个人发送邮件， 但不包含附件
	 * @param recipient 收信人邮件
	 * @param subject 信的主题
	 * @param content 信的内容
	 * @return 是否发送成功
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public boolean send(String subject, String content, String... recipient)
	{
		return send(subject, content, null, recipient);
	}


	/**
	 *发送内容和附件, 发送一个人recipient为String 类型或多个人为String数组类型
	 * @param recipient 收信人邮件
	 * @param subject 信的主题
	 * @param content 信的内容
	 * @param attchament 信的附件
	 * @return 是否发送成功
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public boolean send(String subject, String content, File attachment, String... recipient)
	{
		try {
			MimeMessage msg = new MimeMessage(session); //邮件信息处理类
			msg.setFrom(new InternetAddress(username)); //设置发信邮箱

			InternetAddress[] recipients = new InternetAddress[recipient.length]; //多个发送地址
			for (int i = 0; i < recipient.length; i++) {
				recipients[i] = new InternetAddress(recipient[i]);
			}
			msg.setRecipients(RecipientType.TO, recipients); //设置多收信人邮箱
			msg.setSubject(subject); //写邮件主题

			MimeMultipart multipert = new MimeMultipart(); //要发送数据包(邮件内容和邮件附件)

			//邮件内容
			MimeBodyPart contentpart = new MimeBodyPart(); //邮件内容
			contentpart.setContent(content, "text/html;charset=utf-8"); //邮件内容数据
			multipert.addBodyPart(contentpart);//加入内容到数据包

			//判断是否有附件和附件是存在， 有就添加， 没有不加
			if (attachment != null && attachment.exists())
			{
				MimeBodyPart attachmentpart = new MimeBodyPart(); //邮件附件
				DataSource source = new FileDataSource(attachment); //把附件转数据源
				attachmentpart.setDataHandler(new DataHandler(source)); //把数据源绑定到附件
				//attachmentpart.setFileName(attachment.getName()); //发送中文文件名，乱码
				attachmentpart.setFileName(MimeUtility.encodeWord(attachment.getName())); //解决附件中文名乱码
				multipert.addBodyPart(attachmentpart); //加入附件到发送主体
			}

			msg.setContent(multipert); //把邮件内容和邮件附件加入到信息处理类
			msg.saveChanges(); //保存邮件
			Transport.send(msg); //发出邮件
			return true;
		} catch (Exception e) {
			throw new RuntimeException("[" + username + "]发送给" + Arrays.toString(recipient) +  "的邮件失败...", e);
		} 
	}
}