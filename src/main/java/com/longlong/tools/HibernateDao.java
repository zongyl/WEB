package com.longlong.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.longlong.model.UZlecuList;

/**
 * hibernate dao
 * @author long
 *
 */
public class HibernateDao {
	
	static Logger log =  Logger.getLogger(HibernateDao.class);
	static Session session = null;
	static Configuration configuration = new AnnotationConfiguration().configure(new File("src/main/java/hibernate.cfg.xml"));
    static Transaction transaction = null;
    Transaction tran = null;
    
    /**
     * init
     */
	public static void init(){
		session = configuration.buildSessionFactory().openSession();
	}
	
	/**
	 * save object
	 * @param obj Object
	 * @return
	 */
	public Long save(Object obj){
		tran = session.beginTransaction();
		Long ret = (Long) session.save(obj);
		tran.commit();
		session.clear();
		return ret;
	}
	
	public Object getById(Long Id){
		return session.get(UZlecuList.class, Id);
	}
	
	public List list(String hql, int pagesize, int start){
		Query query = session.createQuery(hql);
		query.setMaxResults(pagesize);
		query.setFirstResult(start);
		return query.list();
	}
	
	public void update(Object obj){
		tran = session.beginTransaction();
		session.saveOrUpdate(obj);
		tran.commit();
		session.clear();
	}
	
	public static void close(){
		session.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init();
		transaction = session.beginTransaction();
		/**
		 * 慎用   会清掉数据库的  ............555555
		 */
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, true);
		
		/** save */
//		Units unit = null;
//		for(int i=0;i<10;i++){
//		unit = new Units();
//		unit.setUnitName("unit"+i);
//		session.save(unit);
//		}
		
//		Users user = new Users();
//		user.setUsername("zongyl");
//		try{
//			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1988-03-29"));
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		user.setRemake("remake");
//		Units unit = new Units();
//		unit.setId(5l);
//		user.setUnit(unit);
//		
//		session.save(user);
		/** update */
		//session.update(user);
		
		//session.delete(user);
		
//		Object obj = session.load(Users.class, 2l);
//		log.info(obj);
		///session.createQuery("").list();
		
		transaction.commit();
		
	}

}
