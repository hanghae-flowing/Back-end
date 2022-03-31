package com.pjt.flowing.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor  //get,set 할거임 생성자 안만들거임
public class ToJsonHelper {
    private String objToString; //최종 리턴하여 쓸 변수
    JSONObject obj = new JSONObject();
    public void PutKeyObject(String key,Object object){ //obj.put은 object를 넣고 싶을때
        this.obj.put(key,object);
    }
    public String Result(){
        return objToString=obj.toString();
    }
}
