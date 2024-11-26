package com.ctfs.common.email;

import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.mail.imap.IMAPFolder;

import com.ctfs.ui.utils.DriverHelper;


public class _GmailAccess {
//	private static Logger log = Logger.getLogger(_GmailAccess.class);
	private final Logger log = LoggerFactory.getLogger(DriverHelper.class);
	
    private static final Map<String, String> gmailPasswords;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("TESTER8972@GMAIL.COM", "nlzosvnlrabbalvh");
        aMap.put("TESTER8973@GMAIL.COM", "jgedhiifijvfslfs");
        aMap.put("TESTER8969@GMAIL.COM", "svraatprwfzmjuuv");
        aMap.put("TESTER8975@GMAIL.COM", "gpofgzxhykprnnkk");
        aMap.put("TESTER8967@GMAIL.COM", "pqtouehpxpcyqfoo");
        aMap.put("TESTER8968@GMAIL.COM", "tjbibahunkdhmqbj");
        aMap.put("CTFSAUTOMATION1@GMAIL.COM", "evjcuodsqnicgtxw");        
        gmailPasswords = Collections.unmodifiableMap(aMap);
    }
	
	public _GmailAccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		setCufoffTimes(cal.getTime(), null);
		//cal.add(Calendar.MINUTE, -40);
	}
	
	private Store connectToStore(String emailAddress) throws NoSuchProviderException, MessagingException, Exception {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props, null);

		Store store = session.getStore("imaps");
		String baseEmail = getBaseEmail(emailAddress);
		System.out.println("baseEmail=" + baseEmail);

		store.connect("imap.googlemail.com", baseEmail, gmailPasswords.get(baseEmail.toUpperCase())); 
		//store.connect("imap.googlemail.com", baseEmail, "bearie1234");
		
		return store;
	}
	
	private String getBaseEmail(String email) {
		log.info("Entering String getBaseEmail(String email)");
		email = email.toUpperCase();
		String baseEmail = email.replaceAll("\\.", "");
		baseEmail = baseEmail.replace("GMAILCOM", "GMAIL.COM");
		log.info("Returning String getBaseEmail(String email) baseEmail=" + baseEmail);
		return baseEmail;
	}
	
	private Date beforeCutoffTime = null;
	private Date afterCutoffTime = null;
	public void setCufoffTimes(Date beforeCutoffTime, Date afterCutoffTime) {
		if (beforeCutoffTime != null && afterCutoffTime != null) {
			if (beforeCutoffTime.after(afterCutoffTime)) {
				throw new RuntimeException("Invalid cutoff times, before must be before after " + beforeCutoffTime + " " + afterCutoffTime);
			}
		}
		this.beforeCutoffTime = beforeCutoffTime;
		this.afterCutoffTime = afterCutoffTime;
		log.info("Setting the email time filter to " + beforeCutoffTime + " and " + afterCutoffTime);
	}
	
	public GmailMessageBean getLastMessage(String emailAddress) {
		return getLastMessage(emailAddress, null);
	}
	
	public GmailMessageBean getLastMessage(String emailAddress, String [] subjects) {
		if (!isEmailUnderAutomationControl(emailAddress)) {
			throw new RuntimeException("EMAIL call will fail. Address not under automation control emailAddress=" + emailAddress);
		}
		GmailMessageBean msg = null;
		for (int i=0; i<3; i++) {
			try {
				msg = getLastMessage_Local(emailAddress, subjects);
				break;
			} catch (Exception ex) {
				log.info("Caught exception ex=" + ex.getMessage());
			}
		}
		return msg;
	}
	
	@SuppressWarnings("resource")
	public GmailMessageBean getLastMessage_Local(String emailAddress, String [] _subjects) {
		log.info("Entering GmailMessageBean getLastMessage emailAddress=" + emailAddress);
		if (!isEmailUnderAutomationControl(emailAddress)) {
			throw new RuntimeException("EMAIL call will fail. Address not under automation control emailAddress=" + emailAddress);
		}
		GmailMessageBean bean = null;
		Store store = null;
		
		emailAddress = emailAddress.toUpperCase();
		String baddr = getBaseEmail(emailAddress);
		
		try {
			IMAPFolder folder = null;
			
			String subject = null;
			try {
				long t2 = new Date().getTime();
				try {
					store = connectToStore(baddr);
				} catch (Exception ex) {
					ex.printStackTrace();
					log.info("Caught Exception,will retry ex.getMessage=" + ex.getMessage());
					Thread.sleep(15000);
					store = connectToStore(baddr);
				}
				long t3 = new Date().getTime();
				log.info("connectToStore took " + (t3 - t2) + " msec");
				folder = (IMAPFolder) store.getFolder("inbox"); 
	
				if (!folder.isOpen())
					folder.open(Folder.READ_WRITE);
				long t4 = new Date().getTime();
				log.info("open inbox took " + (t4 - t3) + " msec");

				Message[] messages = null;
				messages = folder.getMessages();
				System.out.println("messages.length=" + messages.length);
				
				Message msg = null;
				OUTER:
				for (int i=messages.length - 1; i>=0; i--) {
					msg = messages[i];
					String addr = msg.getAllRecipients()[0].toString().toUpperCase();
					log.info("addr=" + addr + " " + msg.getReceivedDate());
					System.out.println("addr=" + addr + " " + msg.getReceivedDate());
					if (beforeCutoffTime != null && beforeCutoffTime.after(msg.getReceivedDate())) {
						log.info("Unable to find matching email since beforeCutoff time of " + beforeCutoffTime);
						System.out.println("Unable to find matching email since beforeCutoff time of " + beforeCutoffTime);
						msg = null;
						break;
					}
					if (afterCutoffTime != null && msg.getReceivedDate().after(afterCutoffTime)) {
						if (addr.contains(emailAddress)) {
							log.info("Found an email, but after the cutofftime " + afterCutoffTime);
							System.out.println("Found an email, but after the cutofftime " + afterCutoffTime);
						}
						msg = null;
						continue;
					}
					if (!addr.contains(emailAddress)) {
						log.info("Found an email for address");
						continue;
					}
					
					if (_subjects == null) {
						log.info("No subject filter, found matching email");
						break;
					}
					
					for (String _subject : _subjects) {
						log.info("Checking subjects _subject=" + _subject + " actualSubject=" + msg.getSubject());
						if (_subject.equals(msg.getSubject())) {
							log.info("Found email matching subject");
							break OUTER;
						}
					}
					msg = null;
				}
				
				long t5 = new Date().getTime();
				log.info("search took " + (t5 - t4) + " msec");

				long t6 = new Date().getTime();
				System.out.println("*****************************************************************************");
				System.out.println("MESSAGE:");
	
				if (msg != null) {
					subject = msg.getSubject();
					bean = new GmailMessageBean();
		
					System.out.println("Subject: " + subject);
					System.out.println("From: " + msg.getFrom()[0]);
					System.out.println("To: " + msg.getAllRecipients()[0]);
					System.out.println("Date: " + msg.getReceivedDate());
					System.out.println("Size: " + msg.getSize());
					bean.setSubject(subject);
					bean.setFrom(msg.getFrom()[0].toString());
					bean.setTo( msg.getAllRecipients()[0].toString());
					bean.setDate(msg.getReceivedDate());
					bean.setSize(msg.getSize());
		
					if (msg.getContent().toString()
							.contains("javax.mail.internet.MimeMultipart")) {
						System.out.println("MULTI PART INFO");
						Multipart multipart = (Multipart) msg.getContent();
						String content = null;
	
						for (int j = 0; j < multipart.getCount(); j++) {
	
							BodyPart bodyPart = multipart.getBodyPart(j);
	
							String disposition = bodyPart.getDisposition();
	
							if (disposition != null
									&& (disposition.equalsIgnoreCase("ATTACHMENT"))) { // BodyPart.ATTACHMENT doesn't work for gmail

//								System.out.println("Mail have some attachment");
	
								DataHandler handler = bodyPart.getDataHandler();
								System.out.println("file name : " + handler.getName());
							} else {
//								System.out
//										.println("Body: " + bodyPart.getContent());
								content += bodyPart.getContent().toString();
							}
	
						}
						if (content != null) {
							bean.setContent(content);
						}
	
					} else {
						bean.setContent(msg.getContent().toString());
					}
					long t7 = new Date().getTime();
					log.info("read message took " + (t7 - t6) + " msec");
				}
	
			} finally {
				if (folder != null && folder.isOpen()) {
					folder.close(true);
				}
				if (store != null) {
					store.close();
				}
			}
		} catch (Exception ex) {
			log.info("Caught exception msg=" + ex.getMessage());
			throw new RuntimeException(ex);
		}
		
		return bean;
	}
	
//	public static void main(String [] args) {
//		_GmailAccess gmail = new _GmailAccess();
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.HOUR, -4);
//		Date d1 = cal.getTime();
//		gmail.setCufoffTimes(d1, null);
//		GmailMessageBean bean = gmail.getLastMessage("T.E.ST.E.R.8.9.69@GMAIL.COM", new String [] {"Your Temporary Password","Votre Mot de Passe Temporaire"});
//		//GmailMessageBean bean = gmail.getLastMessage("T.E.S.TE.R.8.9.6.7@GMAIL.COM");
//		System.out.println("bean=" + (bean == null ? "null" : bean.getTo()));
//	}
	
	public static boolean isEmailUnderAutomationControl(String email) {
		if (email == null) {
			return false;
		}
		return controlledEmails.contains(email.replaceAll("\\.", "").toLowerCase());
	}
	
	static ArrayList<String> controlledEmails;
	static {
		controlledEmails = new ArrayList<String>();
		controlledEmails.add("tester8967@gmailcom");
		controlledEmails.add("tester8968@gmailcom");
		controlledEmails.add("tester8969@gmailcom");
		controlledEmails.add("tester8972@gmailcom");
		controlledEmails.add("tester8973@gmailcom");
		controlledEmails.add("tester8975@gmailcom");
		controlledEmails.add("ctfsautomation1@gmailcom");
		controlledEmails.add("noemailctfs@gmailcom");
	}
	
	public static void main(String [] args) {
		_GmailAccess gmailAccess = new _GmailAccess();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 0);
		Date d2 = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date d1 = cal.getTime();
		gmailAccess.setCufoffTimes(d1, d2);
		//System.out.println(gmailAccess.getLastMessage("T.ES.T.ER.8.97.2@GMAIL.COM", new String [] {"Your is activated","Your card is activated"}));
		System.out.println(gmailAccess.getLastMessage("T.ES.T.ER.8.97.2@GMAIL.COM", null));
	}
}

class _AddressSearchTerm extends SearchTerm {

	private static final long serialVersionUID = -7990793566863292586L;
	private String emailAddr;
	public _AddressSearchTerm(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	@Override
	public boolean match(Message message) {
		try {
			if (message.getAllRecipients()[0].toString().toUpperCase().contains(emailAddr.toUpperCase())) {
				return true;
			}
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
			return false;
	}
	
}


