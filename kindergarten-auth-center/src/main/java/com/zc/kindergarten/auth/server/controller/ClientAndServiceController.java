package com.zc.kindergarten.auth.server.controller;

import com.zc.kindergarten.auth.server.entity.AuthClient;
import com.zc.kindergarten.auth.server.service.ClientAndServiceService;
import com.zc.kindergarten.common.msg.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ace
 * @create 2017/12/26.
 */
@RestController
@RequestMapping("service")
public class ClientAndServiceController {

    @Autowired
    private ClientAndServiceService clientAndServiceService;

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity modifyUsers(@PathVariable int id, String clients) {
        clientAndServiceService.modifyClientServices(id, clients);
        return new ResponseEntity();
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AuthClient> getClients(@PathVariable int id) {
        return new ResponseEntity(clientAndServiceService.getClients(id));
    }
}
