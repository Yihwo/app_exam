package com.example.a501_09.myportfolio_chungnam.db;

import android.util.Log;
import org.greenrobot.greendao.query.DeleteQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.a501_09.myportfolio_chungnam.util.Util;

/**
 * Created by 501-09 on 2018-04-10.
 */
public class PortfolioQuery {
    public static void logTrip(String tag, ArrayList<Trip> tripList) {
        for (int i = 0; i < tripList.size(); i++) {
            String msg = "trip - " +
                    "id : " + tripList.get(i).getId() + " / " +
                    "title : " + tripList.get(i).getTitle() + " / " +
                    "start_date : " + tripList.get(i).getStart_day() + " / " +
                    "end_date : " + tripList.get(i).getEnd_day() + " / " +
                    "number_of_member : " + tripList.get(i).getNumber_of_member() + " / " +
                    "total_money : " + tripList.get(i).getTotal_money() + " / " +
                    "created_at : " + tripList.get(i).getCreated_at() + " / " +
                    "updated_at : " + tripList.get(i).getUpdate_at() + " / ";
            Log.d(tag, msg);
        }
    }
    public static void logSchedule(String tag, ArrayList<Schedule> schedulesList) {
        for (int i = 0; i < schedulesList.size(); i++) {
            String msg = "schedule - " +
                    "id : " + schedulesList.get(i).getId() + " / " +
                    "place_name : " + schedulesList.get(i).getPlace_name() + " / " +
                    "elapsed_time : " + schedulesList.get(i).getElapse_time() + " / " +
                    "spending_money : " + schedulesList.get(i).getSpend_money() + " / " +
                    "visit_time : " + schedulesList.get(i).getVisit_time() + " / " +
                    "created_at : " + schedulesList.get(i).getCreated_at() + " / " +
                    "updated_at : " + schedulesList.get(i).getUpdate_at() + " / " +
                    "trip_id : " + schedulesList.get(i).getTrip_id() + " / " +
                    "place_id : " + schedulesList.get(i).getPlace_id() + " / ";
            Log.d(tag, msg);
        }
    }
    public static void logPlace(String tag, ArrayList<Place> placeList) {
        for (int i = 0; i < placeList.size(); i++) {
            String msg = "place - " +
                    "id : " + placeList.get(i).getId() + " / " +
                    "name : " + placeList.get(i).getName() + " / " +
                    "desc : " + placeList.get(i).getDesc() + " / " +
                    "img_name : " + placeList.get(i).getImg_name() + " / " +
                    "created_at : " + placeList.get(i).getCreated_at() + " / " +
                    "updated_at : " + placeList.get(i).getUpdate_at() + " / ";
            Log.d(tag, msg);
        }
    }

    public static int updateTrip(DaoSession daoSession, int id,
                                 String title, Date start_date, Date end_date,
                                 int number_of_member, long total_money) {
        Trip temp_trip = daoSession.getTripDao().queryBuilder()
                .where(TripDao.Properties.Id.eq(id)).unique();

        if(temp_trip != null) {
            if(title != null) temp_trip.setTitle(title);
            if(start_date != null) temp_trip.setStart_day(start_date);
            if(end_date != null) temp_trip.setEnd_day(end_date);
            if(number_of_member != -1) temp_trip.setNumber_of_member(number_of_member);
            if(total_money != -1) temp_trip.setTotal_money(total_money);
            temp_trip.setUpdate_at(Util.getNowDateTime());

            daoSession.getTripDao().update(temp_trip);
            return id;
        } else {
            return -1;
        }
    }
    public static int updateSchedule(DaoSession daoSession, int id,
                                     String place_name, Date elapsed_time,
                                     long spending_money, Date visit_time,
                                     long trip_id, long place_id) {
        Schedule temp_schedule = daoSession.getScheduleDao().queryBuilder()
                .where(ScheduleDao.Properties.Id.eq(id)).unique();

        if(temp_schedule != null) {
            if(place_name != null) temp_schedule.setPlace_name(place_name);
            if(elapsed_time != null) temp_schedule.setElapse_time(elapsed_time);
            if(spending_money != -1) temp_schedule.setSpend_money(spending_money);
            if(visit_time != null) temp_schedule.setVisit_time(visit_time);
            if(trip_id != -1) temp_schedule.setTrip_id(trip_id);
            if(place_id != -1) temp_schedule.setPlace_id(place_id);
            temp_schedule.setUpdate_at(Util.getNowDateTime());

            daoSession.getScheduleDao().update(temp_schedule);

            return id;
        } else {
            return -1;
        }
    }

    public static int updatePlace(DaoSession daoSession, int id, String name,
                                  String desc, String img_name) {

        Place temp_place = daoSession.getPlaceDao().queryBuilder()
                .where(PlaceDao.Properties.Id.eq(id)).unique();

        if(temp_place != null) {
            if(name != null) temp_place.setName(name);
            if(desc != null) temp_place.setDesc(desc);
            if(img_name != null) temp_place.setImg_name(img_name);
            temp_place.setUpdate_at(Util.getNowDateTime());
            daoSession.getPlaceDao().update(temp_place);

            return id;
        } else {
            return -1;
        }
    }

    public static void setInitAllData(DaoSession daoSession,
                                      ArrayList<Place> placeList,
                                      ArrayList<Schedule> schedulesList,
                                      ArrayList<Trip> tripList) {


        List<Place> temp_place_list = daoSession.getPlaceDao().queryBuilder().list();
        List<Schedule> temp_schedule_list = daoSession.getScheduleDao().queryBuilder().list();
        List<Trip> temp_trip_list = daoSession.getTripDao().queryBuilder().list();

        if(placeList.size() == 0 && schedulesList.size() == 0 && tripList.size() == 0) {
            for (int i = 0; i < temp_place_list.size(); i++) {
                placeList.add(temp_place_list.get(i));
            }

            for (int i = 0; i < temp_schedule_list.size(); i++) {
                schedulesList.add(temp_schedule_list.get(i));
            }

            for (int i = 0; i < temp_trip_list.size(); i++) {
                tripList.add(temp_trip_list.get(i));
            }
        }
    }
    public static void insertSchedule(DaoSession daoSession,
                                      ArrayList<Schedule> schedulesList,
                                      String place_name, Date elapsed_time,
                                      long spend_money, Date visit_time,
                                      long trip_id, long place_id) {
        Schedule temp_schedule = new Schedule();

        temp_schedule.setPlace_name(place_name);
        temp_schedule.setElapse_time(elapsed_time);
        temp_schedule.setSpend_money(spend_money);
        temp_schedule.setVisit_time(visit_time);
        temp_schedule.setTrip_id(trip_id);
        temp_schedule.setPlace_id(place_id);
        temp_schedule.setUpdate_at(Util.getNowDateTime());
        temp_schedule.setCreated_at(Util.getNowDateTime());

        schedulesList.add(temp_schedule);
        daoSession.insert(temp_schedule);
    }

    public static void insertTrip(DaoSession daoSession,
                                  ArrayList<Trip> tripList,
                                  String title, Date start_date, Date end_date,
                                  int number_of_member, long total_money) {
        Trip temp_trip = new Trip();

        temp_trip.setTitle(title);
        temp_trip.setStart_day(start_date);
        temp_trip.setEnd_day(end_date);
        temp_trip.setNumber_of_member(number_of_member);
        temp_trip.setTotal_money(total_money);
        temp_trip.setUpdate_at(Util.getNowDateTime());
        temp_trip.setCreated_at(Util.getNowDateTime());

        tripList.add(temp_trip);
        daoSession.insert(temp_trip);
    }

    public static void insertPlace(DaoSession daoSession,
                                   ArrayList<Place> placeList,
                                   String name, String desc, String img_name) {
        Place temp_place = new Place();

        temp_place.setName(name);
        temp_place.setDesc(desc);
        temp_place.setImg_name(img_name);
        temp_place.setUpdate_at(Util.getNowDateTime());
        temp_place.setCreated_at(Util.getNowDateTime());

        placeList.add(temp_place);
        daoSession.insert(temp_place);
    }

    public static void deletePlaceData(DaoSession daoSession) {
        final DeleteQuery<Place> deletePlaceQuery = daoSession.queryBuilder(Place.class)
                .buildDelete();
        deletePlaceQuery.executeDeleteWithoutDetachingEntities();
        daoSession.clear();
    }

    public static void deleteScheduleData(DaoSession daoSession) {
        final DeleteQuery<Schedule> deleteScheduleQuery = daoSession.queryBuilder(Schedule.class)
                .buildDelete();
        deleteScheduleQuery.executeDeleteWithoutDetachingEntities();
        daoSession.clear();
    }

    public static void deleteTripData(DaoSession daoSession) {
        final DeleteQuery<Trip> deleteTripQuery = daoSession.queryBuilder(Trip.class)
                .buildDelete();
        deleteTripQuery.executeDeleteWithoutDetachingEntities();
        daoSession.clear();
    }

    public static void deleteAllTableData(DaoSession daoSession) {
        final DeleteQuery<Place> deletePlaceQuery = daoSession.queryBuilder(Place.class)
                .buildDelete();
        deletePlaceQuery.executeDeleteWithoutDetachingEntities();

        final DeleteQuery<Schedule> deleteScheduleQuery = daoSession.queryBuilder(Schedule.class)
                .buildDelete();
        deleteScheduleQuery.executeDeleteWithoutDetachingEntities();

        final DeleteQuery<Trip> deleteTripQuery = daoSession.queryBuilder(Trip.class)
                .buildDelete();
        deleteTripQuery.executeDeleteWithoutDetachingEntities();
        daoSession.clear();
    }

}