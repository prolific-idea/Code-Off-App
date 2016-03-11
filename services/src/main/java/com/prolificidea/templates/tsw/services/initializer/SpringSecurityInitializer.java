package com.prolificidea.templates.tsw.services.initializer;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by matthew.jordaan on 2016/03/10.
 */
@Order(2)
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
