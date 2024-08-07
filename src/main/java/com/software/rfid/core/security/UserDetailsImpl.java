package com.software.rfid.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Daniel Nacher
 * 2023-04-24
 */
//public class UserDetailsImpl implements UserDetails {

//    private final transient Usuario usuario;
//
//    public UserDetailsImpl(Usuario usuario){
//        this.usuario = usuario;
//    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//    }

//    @Override
//    public String getPassword() {
//        return usuario.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return usuario.getNombre();
//    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
