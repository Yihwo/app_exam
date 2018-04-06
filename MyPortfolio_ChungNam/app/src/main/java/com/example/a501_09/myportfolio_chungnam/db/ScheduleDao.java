package com.example.a501_09.myportfolio_chungnam.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCHEDULE".
*/
public class ScheduleDao extends AbstractDao<Schedule, Long> {

    public static final String TABLENAME = "SCHEDULE";

    /**
     * Properties of entity Schedule.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Created_at = new Property(1, java.util.Date.class, "created_at", false, "CREATED_AT");
        public final static Property Update_at = new Property(2, java.util.Date.class, "update_at", false, "UPDATE_AT");
        public final static Property Place_name = new Property(3, String.class, "place_name", false, "PLACE_NAME");
        public final static Property Visit_time = new Property(4, java.util.Date.class, "visit_time", false, "VISIT_TIME");
        public final static Property Elapse_time = new Property(5, java.util.Date.class, "elapse_time", false, "ELAPSE_TIME");
        public final static Property Spend_money = new Property(6, Long.class, "spend_money", false, "SPEND_MONEY");
        public final static Property Place_id = new Property(7, long.class, "place_id", false, "PLACE_ID");
        public final static Property Trip_id = new Property(8, long.class, "trip_id", false, "TRIP_ID");
    }

    private DaoSession daoSession;

    private Query<Schedule> schedule_SchedulesQuery;

    public ScheduleDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CREATED_AT\" INTEGER NOT NULL ," + // 1: created_at
                "\"UPDATE_AT\" INTEGER NOT NULL ," + // 2: update_at
                "\"PLACE_NAME\" TEXT," + // 3: place_name
                "\"VISIT_TIME\" INTEGER," + // 4: visit_time
                "\"ELAPSE_TIME\" INTEGER," + // 5: elapse_time
                "\"SPEND_MONEY\" INTEGER," + // 6: spend_money
                "\"PLACE_ID\" INTEGER NOT NULL ," + // 7: place_id
                "\"TRIP_ID\" INTEGER NOT NULL );"); // 8: trip_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Schedule entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCreated_at().getTime());
        stmt.bindLong(3, entity.getUpdate_at().getTime());
 
        String place_name = entity.getPlace_name();
        if (place_name != null) {
            stmt.bindString(4, place_name);
        }
 
        java.util.Date visit_time = entity.getVisit_time();
        if (visit_time != null) {
            stmt.bindLong(5, visit_time.getTime());
        }
 
        java.util.Date elapse_time = entity.getElapse_time();
        if (elapse_time != null) {
            stmt.bindLong(6, elapse_time.getTime());
        }
 
        Long spend_money = entity.getSpend_money();
        if (spend_money != null) {
            stmt.bindLong(7, spend_money);
        }
        stmt.bindLong(8, entity.getPlace_id());
        stmt.bindLong(9, entity.getTrip_id());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Schedule entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCreated_at().getTime());
        stmt.bindLong(3, entity.getUpdate_at().getTime());
 
        String place_name = entity.getPlace_name();
        if (place_name != null) {
            stmt.bindString(4, place_name);
        }
 
        java.util.Date visit_time = entity.getVisit_time();
        if (visit_time != null) {
            stmt.bindLong(5, visit_time.getTime());
        }
 
        java.util.Date elapse_time = entity.getElapse_time();
        if (elapse_time != null) {
            stmt.bindLong(6, elapse_time.getTime());
        }
 
        Long spend_money = entity.getSpend_money();
        if (spend_money != null) {
            stmt.bindLong(7, spend_money);
        }
        stmt.bindLong(8, entity.getPlace_id());
        stmt.bindLong(9, entity.getTrip_id());
    }

    @Override
    protected final void attachEntity(Schedule entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Schedule readEntity(Cursor cursor, int offset) {
        Schedule entity = new Schedule( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            new java.util.Date(cursor.getLong(offset + 1)), // created_at
            new java.util.Date(cursor.getLong(offset + 2)), // update_at
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // place_name
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // visit_time
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // elapse_time
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // spend_money
            cursor.getLong(offset + 7), // place_id
            cursor.getLong(offset + 8) // trip_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Schedule entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCreated_at(new java.util.Date(cursor.getLong(offset + 1)));
        entity.setUpdate_at(new java.util.Date(cursor.getLong(offset + 2)));
        entity.setPlace_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setVisit_time(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setElapse_time(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setSpend_money(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setPlace_id(cursor.getLong(offset + 7));
        entity.setTrip_id(cursor.getLong(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Schedule entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Schedule entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Schedule entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "schedules" to-many relationship of Schedule. */
    public List<Schedule> _querySchedule_Schedules(long trip_id) {
        synchronized (this) {
            if (schedule_SchedulesQuery == null) {
                QueryBuilder<Schedule> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Trip_id.eq(null));
                schedule_SchedulesQuery = queryBuilder.build();
            }
        }
        Query<Schedule> query = schedule_SchedulesQuery.forCurrentThread();
        query.setParameter(0, trip_id);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPlaceDao().getAllColumns());
            builder.append(" FROM SCHEDULE T");
            builder.append(" LEFT JOIN PLACE T0 ON T.\"PLACE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Schedule loadCurrentDeep(Cursor cursor, boolean lock) {
        Schedule entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Place place = loadCurrentOther(daoSession.getPlaceDao(), cursor, offset);
         if(place != null) {
            entity.setPlace(place);
        }

        return entity;    
    }

    public Schedule loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Schedule> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Schedule> list = new ArrayList<Schedule>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Schedule> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Schedule> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
