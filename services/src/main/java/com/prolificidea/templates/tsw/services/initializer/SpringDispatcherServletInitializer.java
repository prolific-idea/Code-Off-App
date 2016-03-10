//package com.prolificidea.templates.tsw.services.initializer;
//
//import org.springframework.core.annotation.Order;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
///**
// * Created by matthew.jordaan on 2016/03/10.
// */
//@Order(1)
//public class SpringDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{SecurityConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] {SecurityConfig.class};
//    }
//}
