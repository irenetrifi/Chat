package com.example.chatdemo.controller;

import com.example.chatdemo.model.Message;
import com.example.chatdemo.model.MessageJsonResponse;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {
    
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    
    @GetMapping(value={"/chat"})
    public String chat(){
        return "chat";
    }
    
    @PostMapping(value="/sendMsg", produces={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody 
    public MessageJsonResponse sendMsg(@ModelAttribute @Valid Message message, 
            BindingResult result){
        //ricevo a parametro il messagio, il mittente ed il destinatario
        //invio il messaggio
        //restituisco l'esito dell'operazione alla callback AJAX come oggetto JSON
        
        MessageJsonResponse response = new MessageJsonResponse();
        log.info("Content message: " + message.getContentMsg());
        
        if(result.hasErrors()){
            //get error message
            Map<String,String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            response.setValidated(false);
            response.setErrorMessages(errors);
        }
        else {
            //invia messaggio...
            response.setValidated(true);
            response.setMessage(message);
        }
        
        return response;
        
    }
    
}
