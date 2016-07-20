package ru.ya.litun.academyyandex.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Litun on 21.04.2016.
 */
@DatabaseTable(tableName = "covers")
public class Cover {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(dataType = DataType.STRING)
    String small;
    @DatabaseField(dataType = DataType.STRING)
    String big;
}
