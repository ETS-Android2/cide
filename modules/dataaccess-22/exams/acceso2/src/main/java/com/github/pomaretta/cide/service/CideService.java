package com.github.pomaretta.cide.service;

import java.util.logging.Level;

import com.github.pomaretta.cide.Main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class CideService {

	private SessionFactory sessionFactory;

	private DepartmentUnit departmentUnit;
	private PersonUnit personUnit;
	private TeacherUnit teacherUnit;

	public CideService() {
		this.sessionFactory = buildSessionFactory();
	}

	private SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure(Main.class.getResource("hibernate.cfg.xml"));
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		ServiceRegistry serviceRegistry = configuration
				.getStandardServiceRegistryBuilder()
				.applySettings(
						configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public DepartmentUnit getDepartmentUnit() {
		if (departmentUnit == null) {
			departmentUnit = new DepartmentUnit(sessionFactory);
		}
		return departmentUnit;
	}

	public PersonUnit getPersonUnit() {
		if (personUnit == null) {
			personUnit = new PersonUnit(sessionFactory);
		}
		return personUnit;
	}

	public TeacherUnit getTeacherUnit() {
		if (teacherUnit == null) {
			teacherUnit = new TeacherUnit(sessionFactory);
		}
		return teacherUnit;
	}

}
