package com.mirea.artemov.notesappv2.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mirea.artemov.notesappv2.Models.Notes;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert(onConflict=REPLACE)
    void insert(Notes notes);

    @Query("SELECT * FROM notes ORDER BY ID DESC")
    List<Notes> getAll();

    @Query("UPDATE notes SET title= :title, notes= :notes WHERE ID = :id")
    void update(int id, String title, String notes);

    @Query("UPDATE notes SET title= :title, notes= :notes, position= :position WHERE ID = :id")
    void update2(int id, String title, String notes, String position);

    @Delete
    void delete(Notes notes);


    @Query("UPDATE notes SET pinned = :pin WHERE ID = :id")
    void pin(int id, boolean pin);
}
