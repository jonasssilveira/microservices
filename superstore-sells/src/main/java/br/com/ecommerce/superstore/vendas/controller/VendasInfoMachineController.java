package br.com.ecommerce.superstore.vendas.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/venda/env")
public class VendasInfoMachineController {
    @Value("${spring.application.name}")
    String projectName;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getMachineInfo() throws UnknownHostException {
        Map machineInfo = new HashMap();
        machineInfo.put("Ip", Inet4Address.getLocalHost().getHostAddress());
        machineInfo.put("Project Name",projectName);
        return machineInfo;
    }

}
