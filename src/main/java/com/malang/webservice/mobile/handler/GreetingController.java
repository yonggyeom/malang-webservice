package com.malang.webservice.mobile.handler;

import com.malang.webservice.mobile.web.HelloMessage;
import com.malang.webservice.mobile.web.dto.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100); // delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}

//
//@Component
//public class SocketHandler extends TextWebSocketHandler {
//
//    HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) {
//        //메시지 발송
//        System.out.println("handleTextMessage called : " + session.getId() + " / message : " + message.getPayload());
//        String msg = message.getPayload();
//        for(String key : sessionMap.keySet()) {
//            WebSocketSession wss = sessionMap.get(key);
//            try {
//                wss.sendMessage(new TextMessage(msg));
//            }catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        //소켓 연결
//        System.out.println("afterConnectionEstablished called : " + session.getId());
//        super.afterConnectionEstablished(session);
//        sessionMap.put(session.getId(), session);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        //소켓 종료
//        System.out.println("afterConnectionClosed called : " + session.getId());
//        sessionMap.remove(session.getId());
//        super.afterConnectionClosed(session, status);
//    }
//}


