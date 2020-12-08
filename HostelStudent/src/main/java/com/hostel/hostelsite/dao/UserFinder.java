package com.hostel.hostelsite.dao;

import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.dao.interfaceDao.UserDaoInterface;
import com.hostel.hostelsite.dao.interfaceSettings.Settings;
import com.hostel.hostelsite.repo.UserDataRepository;
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
public class UserFinder {
    @Autowired
    EntityManager m;

    @Autowired
    UserDataRepository repositoryData;

    @Autowired
    Settings settings;


    @Autowired
    UserDaoInterface daoInterface;

    public List<User> user(String date, String date2){
        List<User> users = repositoryData.findAllByDateBetween(date, date2);
        return users;
    }


    public List<User> finderUser(Dates dates){

        if(settings.notEmptyDates(dates)){
            return repositoryData.findAllByDateBetween(dates.getDateBorns(), dates.getDateBorns2());
        }else if(settings.notEmptyRoom(dates)){
            return repositoryData.findAllByRoomBetween(dates.getNumberOne(),dates.getNumberTwo());
        }else if(settings.notEmptyString(dates)){
            daoInterface.userBy(dates);
        }
           return daoInterface.userBy(dates);

    }


    public List<User> findUser(Dates dates){
        return newCriteria(lists(maps(dates)),maps(dates));
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
        for(int j = 0; j < predicateList.size(); j++){
            cq.where(predicateList.get(j));
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
        if(dates.getRoom() != 0){
            map.put("room", dates.getRoom());
        }
        if(!dates.getDateBorns().isEmpty()){
            map.put(("date"), dates.getDateBorns());
        }
        return map;
    }
}
