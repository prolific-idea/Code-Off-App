package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.AppUserDetails;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import com.prolificidea.templates.tsw.persistence.AppUserDetailsDao;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserDetailsDaoImpl extends GenericDaoImpl<AppUserDetails> implements AppUserDetailsDao {



}
