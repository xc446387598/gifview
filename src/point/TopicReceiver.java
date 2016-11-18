package point;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicReceiver {
    // tcp ��ַ

    public static final String BROKER_URL = "tcp://localhost:61616";

    // Ŀ�꣬��ActiveMQ����Ա����̨���� http://localhost:8161/admin/queues.jsp

    public static final String TARGET = "szh719";

    

    

    public static void run() throws Exception {

        

        TopicConnection connection = null;

        TopicSession session = null;

        try {

            // �������ӹ���

            TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);

            // ͨ����������һ������

            connection = factory.createTopicConnection();

            // ��������

            connection.start();

            // ����һ��session�Ự

            session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            // ����һ����Ϣ����

            Topic topic = session.createTopic(TARGET);

            // ������Ϣ������

            TopicSubscriber subscriber = session.createSubscriber(topic);

            

            subscriber.setMessageListener(new MessageListener() { 

                public void onMessage(Message msg) { 

                    if (msg != null) {

                        MapMessage map = (MapMessage) msg;

                        try {

                            System.out.println(map.getLong("time") + "����#" + map.getString("text"));

                        } catch (JMSException e) {

                            e.printStackTrace();

                        }

                    }

                } 

            }); 

            // ����100ms�ٹر�

            Thread.sleep(1000 * 100); 

            

            // �ύ�Ự

            session.commit();

            

        } catch (Exception e) {

            throw e;

        } finally {

            // �ر��ͷ���Դ

            if (session != null) {

                session.close();

            }

            if (connection != null) {

                connection.close();

            }

        }

    }

    

    public static void main(String[] args) throws Exception {

        TopicReceiver.run();

    }
}
