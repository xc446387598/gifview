package point;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

//��Ϣ������
public class MessageReceiver {
    // tcp ��ַ

    public static final String BROKER_URL = "tcp://localhost:61616";

    // Ŀ�꣬��ActiveMQ����Ա����̨���� http://localhost:8161/admin/queues.jsp

    public static final String DESTINATION = "szh818";
    
    public static void run() throws Exception{
    	Session session=null;
    	Connection connection=null;
    	try {
            // �������ӹ���
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            //ͨ����������һ������
            connection =factory.createConnection();
            //��������
            connection.start();
            //����һ��session�Ự
            session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //����һ����Ϣ����
            Destination destination=session.createQueue(DESTINATION);
            //������Ϣ������
            MessageConsumer consumer=session.createConsumer(destination);
            while(true){
            	//�������ݵ�ʱ�䣨�ȴ���100ms
            	Message message=consumer.receive(1000*100);
            	TextMessage text=(TextMessage) message;
            	if(text!=null){
            		System.out.println("����:"+text.getText());
            	}else{
            		break;
            	}
            }
            //�ύ�Ự
            session.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			if(session!=null){
				session.close();
			}
			if(connection!=null){
				connection.close();
			}
		}
    }
    public static void main(String[] args) throws Exception {

    	MessageReceiver.run();

    }
}
