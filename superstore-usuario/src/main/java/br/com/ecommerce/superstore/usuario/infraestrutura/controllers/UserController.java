package br.com.ecommerce.superstore.usuario.infraestrutura.controllers;

import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ecommerce.superstore.usuario.infraestrutura.services.UserService;
import java.util.Optional;

@RestController
@RequestMapping("v1/usuario")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.of(Optional.of(userService.createUser(userDTO)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> getAll(@PageableDefault(sort = "nome",
            direction = Sort.Direction.ASC, page = 0, size = 20) Pageable paginacao){
        return userService.getAll(paginacao);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<UserDTO> getById(@PathVariable String id){
        return ResponseEntity.of(Optional.of(userService.getById(id)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }


}
