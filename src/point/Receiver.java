package point;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Receiver {
	public static void main(String[] args) {
		ApplicationContext act=new FileSystemXmlApplicationContext("applicationContext-*.xml");
		JmsTemplate jsmTemplate=(JmsTemplate) act.getBean("jsmTemplate");
		while(true){
			Map<String,Object> map=(Map<String, Object>) jsmTemplate.receiveAndConvert();
			System.out.println("收到消息：" + map.get("message"));
		}
	}

}
