package com.saravit.controller;

import com.saravit.model.AKPostRequest;
import com.saravit.model.PhoneUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by soengsaravit on 7/12/17.
 *
 *
 */

@Controller
public class SMSLoginController {

    private String account_kit_api_version = "v1.1";
    private String  app_id = "535722463218148";
    private String  app_secret = "e5107f7693bfcdc7fbc1c00bc21596bb";
    private String  me_endpoint_base_url = "https://graph.accountkit.com/v1.1/me";
    private String  token_exchange_base_url = "https://graph.accountkit.com/v1.1/access_token";

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String loginPage(ModelMap model){
        model.addAttribute("AKPost", new AKPostRequest());
        return "login";
    }


    @PostMapping("/login_success")
    public String loginSuccessPage(@ModelAttribute AKPostRequest akPostRequest, ModelMap model){

        // Exchange access_token with server
        String uri = token_exchange_base_url + "?grant_type=authorization_code" + "&code=" +
                akPostRequest.getCode()+"&access_token=AA|"+app_id+"|"+app_secret;
        Object obj = restTemplate.getForObject(uri, Object.class);

        Map<String, Object> info = (HashMap<String,Object>) obj;
        String user_id = (String) info.get("id");
        String user_access_token = (String) info.get("access_token");
        int refresh_rate = (int) info.get("token_refresh_interval_sec");

        String me_endpoint_uri = me_endpoint_base_url+"?access_token="+user_access_token;
        Object me = restTemplate.getForObject(me_endpoint_uri,Object.class);

        //Set Phone User Object
        Map<String, Object> map = (HashMap<String,Object>) me;
        Map<String, Object> phone = (HashMap<String,Object>) map.get("phone");
        String number = (String) phone.get("number");

        PhoneUser pu = new PhoneUser();
        pu.setUser_id(user_id);
        pu.setPhone_number(number);
        pu.setAccess_token(user_access_token);s

        model.addAttribute("PHONEUSER",pu);

        return "login_success";
    }
}
