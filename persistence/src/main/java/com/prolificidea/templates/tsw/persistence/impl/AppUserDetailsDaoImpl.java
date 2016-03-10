package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.AppUserDetails;
import com.prolificidea.templates.tsw.persistence.AppUserDetailsDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserDetailsDaoImpl extends GenericDaoImpl<AppUserDetails> implements AppUserDetailsDao {



}
