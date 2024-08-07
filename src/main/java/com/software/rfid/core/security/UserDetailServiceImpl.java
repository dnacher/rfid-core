package com.software.rfid.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Daniel Nacher
 * 2023-04-24
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
//        Usuario usuario = usuarioService.getUsuarioByNombre(username);
//        if(usuario==null){
//            throw new UsernameNotFoundException("El usuario " + username + " no existe.");
//        }else {
//            return new UserDetailsImpl(usuario);
//        }
    }
}
