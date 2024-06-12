package com.usuariosventas.springboot.app.config.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority")String permiso){

    }
}
