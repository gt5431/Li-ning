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
 * �򵥵�java �ʼ�������SimpleSendMail
 * @author jp
 * @version v1.0
 * @since 2015-01-02
 */

public class SimpleSendMail {
	private Session session; //���ʼ����������Ӷ���
	private String username; //����������

	/**
	 * ֻ�ܴ��� �ʼ������� " smtp.xxxx.xxx" ��  "������@xxxx.xxx" ��ͬ���
	 * @param smtpHostName  �ż�������
	 * @param username ����������
	 * @param password ��������
	 */
	public SimpleSendMail(final String username, final String password)
	{
		this(("smtp." + username.split("@")[1]), username, password);
	}

	/**
	 * 
	 * @param smtpHostName  �ż�������
	 * @param username ����������
	 * @param password ��������
	 */
	public SimpleSendMail(final String smtpHostName, final String username, final String password)
	{
		this.username = username;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true"); //���÷����ʼ��Ƿ�Ҫ�˺ź�����
		props.put("mail.smtp.host", smtpHostName); //���÷����ʼ�������

		//���ʼ���������������, Authenticator������֤
		session = Session.getInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * ֻ�ܸ�һ���˻����˷����ʼ��� ������������
	 * @param recipient �������ʼ�
	 * @param subject �ŵ�����
	 * @param content �ŵ�����
	 * @return �Ƿ��ͳɹ�
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public boolean send(String subject, String content, String... recipient)
	{
		return send(subject, content, null, recipient);
	}


	/**
	 *�������ݺ͸���, ����һ����recipientΪString ���ͻ�����ΪString��������
	 * @param recipient �������ʼ�
	 * @param subject �ŵ�����
	 * @param content �ŵ�����
	 * @param attchament �ŵĸ���
	 * @return �Ƿ��ͳɹ�
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public boolean send(String subject, String content, File attachment, String... recipient)
	{
		try {
			MimeMessage msg = new MimeMessage(session); //�ʼ���Ϣ������
			msg.setFrom(new InternetAddress(username)); //���÷�������

			InternetAddress[] recipients = new InternetAddress[recipient.length]; //������͵�ַ
			for (int i = 0; i < recipient.length; i++) {
				recipients[i] = new InternetAddress(recipient[i]);
			}
			msg.setRecipients(RecipientType.TO, recipients); //���ö�����������
			msg.setSubject(subject); //д�ʼ�����

			MimeMultipart multipert = new MimeMultipart(); //Ҫ�������ݰ�(�ʼ����ݺ��ʼ�����)

			//�ʼ�����
			MimeBodyPart contentpart = new MimeBodyPart(); //�ʼ�����
			contentpart.setContent(content, "text/html;charset=utf-8"); //�ʼ���������
			multipert.addBodyPart(contentpart);//�������ݵ����ݰ�

			//�ж��Ƿ��и����͸����Ǵ��ڣ� �о���ӣ� û�в���
			if (attachment != null && attachment.exists())
			{
				MimeBodyPart attachmentpart = new MimeBodyPart(); //�ʼ�����
				DataSource source = new FileDataSource(attachment); //�Ѹ���ת����Դ
				attachmentpart.setDataHandler(new DataHandler(source)); //������Դ�󶨵�����
				//attachmentpart.setFileName(attachment.getName()); //���������ļ���������
				attachmentpart.setFileName(MimeUtility.encodeWord(attachment.getName())); //�����������������
				multipert.addBodyPart(attachmentpart); //���븽������������
			}

			msg.setContent(multipert); //���ʼ����ݺ��ʼ��������뵽��Ϣ������
			msg.saveChanges(); //�����ʼ�
			Transport.send(msg); //�����ʼ�
			return true;
		} catch (Exception e) {
			throw new RuntimeException("[" + username + "]���͸�" + Arrays.toString(recipient) +  "���ʼ�ʧ��...", e);
		} 
	}
}