//package com.hostel.hostelsite.dao;
//
//import com.hostel.hostelsite.dao.entity.User;
//import com.hostel.hostelsite.dao.interfaceDao.DaoInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//
//@Component
//public class DaoUsers implements DaoInterface {
//    @Autowired
//    EntityManager m;
//
//    @Autowired
//    JdbcTemplate template;

//    public int save(User models) {
//        String sql = "insert into user(name,lastname,room,date_born) values ('" + models.getName() + "','" + models.getLastname() + "', " + models.getRoom() + ", '" + models.getDate_born() + "');";
//        return template.update(sql);
//    }
//
//    public List<User> getUsers() {
//        return template.query("select * from user", (rs, row) -> {
//            User e = new User();
//            e.setId(rs.getLong(1));
//            e.setName(rs.getString(2));
//            e.setLastname(rs.getString(3));
//            e.setRoom(rs.getInt(4));
//            e.setDate_born(rs.getString(5));
//            return e;
//        });
//
//    }
//
//    public int update (User models){
//        String sql="update user set name= '" + models.getName() + "', lastname= '" + models.getLastname() + "', room=  " + models.getRoom() + ", date_born= '" + models.getDate_born() + "' where id = "+models.getId()+"";
//        return template.update(sql);
//    }
//
//    public User getEmpById(int id){
//        String sql="select * from user where id=?";
//        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
//    }
//
//    public int delete(int id){
//        String sql="delete from user where id= " + id + "";
//        return template.update(sql);
//    }
//
//    public List<User> getUserByRoom(int room){
//        String sql = "select * from user where room ="+room+"";
//        return template.query(sql, (rs,row) -> {
//            User e = new User();
//            e.setId(rs.getLong(1));
//            e.setName(rs.getString(2));
//            e.setLastname(rs.getString(3));
//            e.setRoom(rs.getInt(4));
//            e.setDate_born(rs.getString(5));
//            return e;
//        });
//    }
//
//    public List<User> findUserByRoom(String nameColumn, Object object){
//        CriteriaBuilder cb = m.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> user = cq.from(User.class);
//        Predicate userRoomPredicate = cb.equal(user.get(nameColumn),object);
//        cq.where(userRoomPredicate);
//        TypedQuery<User> query=m.createQuery(cq);
//        return query.getResultList();
//    }
//
//
//
//
//}
