package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.AppUser;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.AppUserDao;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person> implements PersonDao {
}
