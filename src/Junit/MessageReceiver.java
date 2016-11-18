package Junit;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class MessageReceiver {
	@Test
	public void Receive() throws JMSException{
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-*.xml");  
		          JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
		         Destination destination = (Destination) ctx.getBean("destination");  
	        while (true) {
		             TextMessage msg = (TextMessage) template.receive(destination);
	            if (null != msg)
		               System.out.println("收到消息内容为: " + msg.getText());
		            else
		                 break;
		
		         }  
	}

}
