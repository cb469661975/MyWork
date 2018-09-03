package com.example.cheng.myapplication.viewmodel.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by chengbiao on 2018/8/31
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM userentity")
    List<UserEntity> getAll();

    @Query("SELECT * FROM userentity WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM userentity WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    UserEntity findByName(String first, String last);

    @Insert
    void insertAll(UserEntity... users);

    @Delete
    void delete(UserEntity user);
}