package com.example.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.example.a501_09.myportfolio_chungnam.db");

        schema.enableKeepSectionsByDefault();
        setRelation(addTripEntity(schema),addScheduleEntity(schema),addPlaceEntities(schema));

        try{
            new DaoGenerator().generateAll(schema,"../app/src/main/java");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static void setRelation(Entity trip,Entity schedule,Entity place){
        Property place_id = schedule.addLongProperty("place_id").notNull().getProperty();
        schedule.addToOne(place,place_id);
        Property trip_id = schedule.addLongProperty("trip_id").notNull().getProperty();
        ToMany toMany = schedule.addToMany(schedule,trip_id);
        toMany.setName("schedules");

    }

    private static Entity addPlaceEntities(final Schema schema){
        Entity place = schema.addEntity("Place");
        place.addIdProperty().primaryKey().autoincrement();

        place.addDateProperty("created_at").notNull();
        place.addDateProperty("update_at").notNull();

        place.addStringProperty("name");
        place.addStringProperty("desc");
        place.addStringProperty("img_name");
        place.addStringProperty("phone");
        return place;
    }
    private static Entity addTripEntity(Schema schema){
        Entity trip = schema.addEntity("Trip");
        trip.addIdProperty().primaryKey().autoincrement();

        trip.addDateProperty("start_day").notNull();
        trip.addDateProperty("end_day").notNull();

        trip.addDateProperty("created_at").notNull();
        trip.addDateProperty("update_at").notNull();

        trip.addStringProperty("title");
        trip.addIntProperty("number_of_member");
        trip.addLongProperty("total_money");
        return trip;
    }
    private static Entity addScheduleEntity(Schema schema){
        Entity schedule = schema.addEntity("Schedule");
        schedule.addIdProperty().primaryKey().autoincrement();

        schedule.addDateProperty("created_at").notNull();
        schedule.addDateProperty("update_at").notNull();

        schedule.addStringProperty("place_name");
        schedule.addDateProperty("visit_time");
        schedule.addDateProperty("elapse_time");
        schedule.addLongProperty("spend_money");

        return schedule;
    }
}
