//package com.software.rfid.core.enums.Logger;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//
//public class LogManagerClass {
//
//    public Logger logger;
//
//    public LogManagerClass(Class<?> className) {
//        logger = LogManager.getLogger(className);
//    }
//
//    public void error(String error){
//        logger.error(error);
//    }
//
////    public void error(Usuario nombreUsuario, String error, Exception ex){
////        logger.error(getnombreUsuario() + error, ex);
////    }
//
//    public void info(String info){
//        logger.info(getnombreUsuario() + info);
//    }
//
//    public void warn(String warn){
//        logger.warn(warn);
//    }
//
////    public void warn(Usuario nombreUsuario,String warn, Exception ex){
////        logger.warn(getnombreUsuario() + warn, ex);
////    }
//
//    private String getnombreUsuario(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String usuario = null;
//        if(authentication!=null){
//             usuario = (String) authentication.getPrincipal();
//        }
//        if(usuario!=null){
//            return "[" + usuario + "] :: ";
//        }else{
//            return "[ SIN USUARIO LOGUEADO ] :: ";
//        }
//    }
//
//}
