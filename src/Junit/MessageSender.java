package Junit;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class MessageSender implements MessageCreator {

	
	public  void send(){
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		 JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
		 Destination destination = (Destination) ctx.getBean("destination");
		 template.send(destination, this);
		 System.out.println("����JMS��Ϣ�ɹ�");
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		 return session.createTextMessage("sender������Ϣ��");
	}
	public static void main(String[] args) {
		MessageSender s=new MessageSender();
		s.send();
	}
}
