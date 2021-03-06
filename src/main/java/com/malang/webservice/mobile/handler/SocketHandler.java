package com.malang.webservice.mobile.handler;

import com.google.gson.Gson;
import com.malang.webservice.mobile.domain.messages.Messages;
import com.malang.webservice.mobile.service.MessagesService;
import com.malang.webservice.mobile.web.dto.MessagesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class SocketHandler extends TextWebSocketHandler {

    private final MessagesService messagesService;

    // HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
    List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        //메시지 발송
//        Map value = new Gson().fromJson(message.getPayload(), Map.class);
//        System.out.println("handleTextMessage called : " + session.getId() + " / message : " + value.get("text").toString());
//        String msg = value.get("text").toString();
        String msg = message.getPayload();
        System.out.println(msg);
        JSONObject obj = jsonToObjectParser(msg);
        System.out.println(obj.toString());

        String rN = (String) obj.get("chatId");
        System.out.println("rN  : " + rN);
        HashMap<String, Object> temp = new HashMap<String, Object>();

        // 여기는 메시지가 수신되자마자 처리되는 부분이므로, Messages Entity의 save 기능을 처리해서 매번 메시지를 저장한다.
        MessagesSaveRequestDto requestDto = MessagesSaveRequestDto.builder()
                .chatId(obj.get("chatId").toString())
                .fromUserId(obj.get("fromUser").toString())
                .text(obj.get("text").toString())
                .build();

        Messages tmpMessage = messagesService.saveMessageReturnEntity(requestDto);

        obj.put("id", tmpMessage.getId());
        obj.put("createdDate", tmpMessage.getCreatedDate().toString());

        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
                String chatId = (String) rls.get(i).get("chatId"); //세션리스트의 저장된 방번호를 가져와서
                if(chatId.equals(rN)) { //같은값의 방이 존재한다면
                    temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
                    break;
                }
            }

            //해당 방의 세션들만 찾아서 메시지를 발송해준다.
            for(String k : temp.keySet()) {
                if(k.equals("chatId")) { //다만 방번호일 경우에는 건너뛴다.
                    continue;
                }

                WebSocketSession wss = (WebSocketSession) temp.get(k);
                if(wss != null) {
                    try {
                        wss.sendMessage(new TextMessage(obj.toJSONString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결
        System.out.println("afterConnectionEstablished called : " + session.getId());
        super.afterConnectionEstablished(session);
        boolean flag = false;
        String url = session.getUri().toString();
        System.out.println("url : " + url);
        String chatId = url.split("/chatting/")[1];
        System.out.println("chatId : " + chatId);
        int idx = rls.size(); //방의 사이즈를 조사한다.
        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
                String rN = (String) rls.get(i).get("chatId");
                if(rN.equals(chatId)) {
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }

        if(flag) { //존재하는 방이라면 세션만 추가한다.
            HashMap<String, Object> map = rls.get(idx);
            map.put(session.getId(), session);
        }else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("chatId", chatId);
            map.put(session.getId(), session);
            rls.add(map);
        }

        //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        //session.sendMessage(new TextMessage(obj.toJSONString()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        System.out.println("afterConnectionClosed called : " + session.getId());
        if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
            for(int i=0; i<rls.size(); i++) {
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}




//package com.malang.webservice.mobile.handler;
//
//import com.google.gson.Gson;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class SocketHandler extends TextWebSocketHandler {
//
//    //HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
//    List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) {
//        //메시지 발송
////        Map value = new Gson().fromJson(message.getPayload(), Map.class);
////        System.out.println("handleTextMessage called : " + session.getId() + " / message : " + value.get("text").toString());
////        String msg = value.get("text").toString();
//        //메시지 발송
//        String msg = message.getPayload();
//        System.out.println(msg);
//        JSONObject obj = jsonToObjectParser(msg);
//        System.out.println(obj.toString());
//
//        String rN = (String) obj.get("roomNumber");
//        System.out.println("rN : " + rN);
//        HashMap<String, Object> temp = new HashMap<String, Object>();
//
//        if(rls.size() > 0) {
//            for(int i=0; i<rls.size(); i++) {
//                String roomNumber = (String) rls.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
//                if(roomNumber.equals(rN)) { //같은값의 방이 존재한다면
//                    temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
//                    break;
//                }
//            }
//
//            //해당 방의 세션들만 찾아서 메시지를 발송해준다.
//            for(String k : temp.keySet()) {
//                if(k.equals("roomNumber")) { //다만 방번호일 경우에는 건너뛴다.
//                    continue;
//                }
//
//                WebSocketSession wss = (WebSocketSession) temp.get(k);
//                if(wss != null) {
//                    try {
//                        wss.sendMessage(new TextMessage(obj.toJSONString()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        //소켓 연결
//        System.out.println("afterConnectionEstablished called : " + session.getId());
//        super.afterConnectionEstablished(session);
//        boolean flag = false;
//        String url = session.getUri().toString();
//        System.out.println("url : " + url);
//        String roomNumber = url.split("/chating/")[1];
//        System.out.println("roomNumber : " + roomNumber);
//        int idx = rls.size(); //방의 사이즈를 조사한다.
//        if(rls.size() > 0) {
//            for(int i=0; i<rls.size(); i++) {
//                String rN = (String) rls.get(i).get("roomNumber");
//                if(rN.equals(roomNumber)) {
//                    flag = true;
//                    idx = i;
//                    break;
//                }
//            }
//        }
//
//        if(flag) { //존재하는 방이라면 세션만 추가한다.
//            HashMap<String, Object> map = rls.get(idx);
//            map.put(session.getId(), session);
//        }else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("roomNumber", roomNumber);
//            map.put(session.getId(), session);
//            rls.add(map);
//        }
//
//        //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
//        JSONObject obj = new JSONObject();
//        obj.put("type", "getId");
//        obj.put("sessionId", session.getId());
//        session.sendMessage(new TextMessage(obj.toJSONString()));
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        //소켓 종료
//        System.out.println("afterConnectionClosed called : " + session.getId());
//        if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
//            for(int i=0; i<rls.size(); i++) {
//                rls.get(i).remove(session.getId());
//            }
//        }
//        super.afterConnectionClosed(session, status);
//    }
//
//    private static JSONObject jsonToObjectParser(String jsonStr) {
//        JSONParser parser = new JSONParser();
//        JSONObject obj = null;
//        try {
//            obj = (JSONObject) parser.parse(jsonStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return obj;
//    }
//
//}
//
//
