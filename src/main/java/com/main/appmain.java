package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.mainban.user;

public class appmain {

	List<String> empnames = new ArrayList();
	List<Object> startwith = new ArrayList();
	List<Object> capital = new ArrayList();
	static user up;
	static Session ses;
	static SessionFactory sesfacobj;

	private void acquire() {
		Configuration cong = new Configuration().configure("hibernate.xml").addAnnotatedClass(user.class);
		ServiceRegistry sreg = new StandardServiceRegistryBuilder().applySettings(cong.getProperties()).build();
		SessionFactory sfac = cong.buildSessionFactory(sreg);
		Session hsess = sfac.openSession();
		Query qu = hsess.createQuery("from  user");
		List<user> li = qu.list();
		for (user u : li) {
			System.out.println("name " + u.getName());
			empnames.add(u.getName());
			System.out.println("ID " + u.getId());
		}
		startwith = empnames.stream().filter(S -> S.startsWith("L")).collect(Collectors.toList());
		List<Object> sortedname = empnames.stream().sorted().collect(Collectors.toList());
		for (Object ob : sortedname) {
			System.out.println("Sortednames=" + ob);
		}
		for (Object obj : startwith) {
			System.out.println("names with L" + obj);
		}
		capital=empnames.stream().map(String::toUpperCase).collect(Collectors.toList());
		for(Object ob:capital) {
			System.out.println("Capital names "+ob);
		}
	}

	private void insert() {
		Configuration cong = new Configuration().configure("hibernate.xml").addAnnotatedClass(user.class);
		ServiceRegistry sreg = new StandardServiceRegistryBuilder().applySettings(cong.getProperties()).build();
		SessionFactory sfac = cong.buildSessionFactory(sreg);
		Session hsess = sfac.openSession();
		Transaction tran = hsess.beginTransaction();
		user s = new user();
		s.setId(10);
		s.setName("LionKing " + 10);
		hsess.save(s);
		tran.commit();
		hsess.close();
	}

	public static SessionFactory sfac() {
		Configuration conf = new Configuration().addAnnotatedClass(user.class);
		conf.configure("hibernate.xml");
		ServiceRegistry sreg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		sesfacobj = conf.buildSessionFactory(sreg);
		return sesfacobj;
	}

	public static void main(String[] args) {

		appmain am = new appmain();
		am.acquire();
		// insert();
		// System.out.println("-------------------------------HIbernate Maven
		// Example------------------------------------");
		// try {
		// ses=sfac().openSession();
		// ses.beginTransaction();
		// for(int i=1;i<=25;i++) {
//				System.out.println("number "+i);
		// up=new user();
		// up.setId(i);
		// up.setName("Lionking"+i);
//				ses.save(up);
		// }
//			System.out.println("-----------------records save sucessfully--------------------------");
		// ses.getTransaction().begin();
		// }catch(HibernateException hes) {
		// System.out.println(hes);
		// }
	}

}
