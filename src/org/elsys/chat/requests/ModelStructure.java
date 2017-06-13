package org.elsys.chat.requests;

import java.sql.Connection;

public abstract class ModelStructure {

    private Connection con;

    public ModelStructure(Connection con) {
        this.con = con;
    }

    public Connection getConnection() {
        return con;
    }


    public abstract int create(String... params);

    public abstract void read(int id);

    public abstract void readAll();

    public abstract int update(int id, String newName);

    public abstract int delete(int id);
}
