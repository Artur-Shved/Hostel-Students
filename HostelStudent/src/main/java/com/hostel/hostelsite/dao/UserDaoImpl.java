package com.hostel.hostelsite.dao;

import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.dao.interfaceDao.UserDaoInterface;
import com.hostel.hostelsite.dao.interfaceSettings.Settings;
import com.hostel.hostelsite.repo.UserDataRepository;
import com.hostel.hostelsite.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDaoInterface {
    @Autowired
    EntityManager m;

    @Autowired
    UserRepository repository;

    @Autowired
    Settings settings;

    @Autowired
    UserDataRepository dataRepository;



    public User save(User user){
            repository.save(user);
            return user;
    }

    public List<User> allUsers(){
        return repository.findAll();
    }

    public User findById(long id){
        User user = repository.findById(id).orElseThrow();
        return user;
    }

    public User editUser(User user){
        User user1 = findById(user.getId());
        user.setUsername(user1.getUsername());
        user.setPassword(user1.getPassword());
        repository.save(user);
        return user;
    }

    public void deleteUser(long id){
        User user = findById(id);
        repository.delete(user);
    }

    @Override
    public List<User> findUserBy(String columnName, Object a) {
        CriteriaBuilder cb = m.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        Predicate userNamePredicate = cb.equal(user.get(columnName), a);
        cq.where(userNamePredicate);
        TypedQuery<User> query=m.createQuery(cq);
        return query.getResultList();
    }

    public List<User> findUserByNameAndLastName(String name, String lastname){
        CriteriaBuilder cb = m.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        if(lastname.isEmpty()) {
            Predicate userName = cb.equal(user.get("name"), name);
            cq.where(userName);
            TypedQuery<User> qury = m.createQuery(cq);
            return qury.getResultList();
        }else{
            Predicate userName = cb.equal(user.get("name"), name);
            Predicate userLastName = cb.equal(user.get("lastname"), lastname);
            cq.where(userName, userLastName);
            TypedQuery<User> qury = m.createQuery(cq);
            return qury.getResultList();
        }

    }

    public List<User> findUserByNameAndLastName(String name){
        CriteriaBuilder cb = m.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        Predicate userName = cb.equal(user.get("name"), name);
        cq.where(userName);
        TypedQuery<User> qury = m.createQuery(cq);
        return qury.getResultList();

    } public List<User> findUserByLastName(String lastname){
        CriteriaBuilder cb = m.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        Predicate userName = cb.equal(user.get("lastname"), lastname);
        cq.where(userName);
        TypedQuery<User> qury = m.createQuery(cq);
        return qury.getResultList();
    }

    public int num(boolean b, String a){
        int nums = -1;
        if(!b){
            nums = Integer.parseInt(a);
        }
        return nums;
    }

    public List<User> userBy(Dates dates) {
        String[] array = dates.getString().split(" ");
        int num = num(settings.numInTheString(dates.getString()), dates.getString());
        if (array.length == 1) {
            if (num > 0) {
                return dataRepository.findAllByRoom(num);
            } else if (num < 0 && settings.userInDbByName(array[0])) {
                return dataRepository.findAllByName(array[0]);
            } else if (num < 0 && settings.userInDbByLastName(array[0])) {
                return dataRepository.findAllByLastname(array[0]);
            }

        }
            return dataRepository.findAllByNameAndLastname(array[0], array[1]);

    }

    public List<User> newCriteria(List<String> list, Map map){
        CriteriaBuilder cb = m.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        List<Predicate> predicateList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
          Predicate findUser = cb.equal(user.get(list.get(i)), map.get(list.get(i)));
          predicateList.add(findUser);
        }

        for(int i = 0; i < predicateList.size(); i++){
            cq.where(predicateList.get(i));
        }
        TypedQuery<User> typedQuery = m.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<String> lists (Map map){
        List<String> list = new ArrayList<>();
        if(map.containsKey("name")){
            list.add("name");
        }
        if(map.containsKey("lastname")){
            list.add("lastname");
        }
        if(map.containsKey("room")){
            list.add("room");
        }
        if(map.containsKey("date")){
            list.add("date");
        }
        return list;
    }

    public Map maps(Dates dates){
        Map map = new HashMap();
        if(!dates.getName().isEmpty()){
            map.put("name", dates.getName());
        }
        if(!dates.getLastName().isEmpty()){
            map.put("lastname", dates.getLastName());
        }
        if(dates.getRoom() > 0){
            map.put("room", dates.getRoom());
        }
        if(!dates.getDateBorns().isEmpty()){
            map.put(("date"), dates.getDateBorns());
        }
        return map;
    }

}
