package es.vicmonmena.utils.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Clase que permite ejecutar las operaciones CRUD contra la BD.
 * <br/>
 * <br/>
 * <b>Creado el: 08/02/2012
 * @author Vicente Montaño Mena
 * <br/>
 * Twitter: @vicmonmena
 * <br/>
 * GTalk: vicmonmena@gmail.com
 * */
public class GenericDAO extends SQLiteOpenHelper {

    private static final String TAG = "GenericDAO";
    private static SQLiteDatabase db;
    private static String dName;
    private static String tName;
    private static String sql;
    
    /**
     * Columna obligatoria que deben contener todas las tablas de la BD
     * */
    public static final String KEY_ID = "_id";

    private static GenericDAO instance;
        
    private GenericDAO(Context ctx, String dbName, String sql, String tableName, int ver){
        super(ctx, dbName, null, ver);
        Log.i(TAG, "Creating or opening database: " + dbName);
        GenericDAO.sql = sql;
        dName = dbName;
        tName = tableName;
    }
    
    /**
     * Obtiene una instancia de la clase GenericDAO
     * 
     * @param ctx - contexto de la aplicación. proviene dela Activity donde invoquemos este método.
     * @paran dbName - nombre de la BD.
     * @param sql - sentencia SQL para crear o abrir la BD.
     * @param tableName - nombre de la tabla en la BD con la que vamos a trabajar.
     * @param ver - versión de la BD.
     * 
     * @return GenericDAO
     * */
    public static GenericDAO getInstance(Context ctx, String dbName, String sql, String tableName, int ver){
        if(instance == null){
            instance = new GenericDAO(ctx, dbName, sql, tableName, ver);
            try{
                Log.i(TAG, "Creating or opening the database [ " + dbName + " ].");
                db = instance.getWritableDatabase();
            }catch(SQLiteException se){
                Log.e(TAG, "Cound not create and/or open the database [ " + dbName + " ] that will be used for reading and writing.", se);
            }
        }
        return instance;
    }

    public void close(){
        if(instance != null){
            Log.i(TAG, "Closing the database [ " + dName + " ].");
            db.close();
            instance = null;
        }
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "Trying to create database table if it isn't existed [ " + sql + " ].");
        try{
            db.execSQL(sql);
        }catch(SQLException se){
            Log.e(TAG, "Cound not create the database table according to the SQL statement [ " + sql + " ].", se);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
       Log.i(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
       try{
           db.execSQL("DROP TABLE IF EXISTS " + tName);
       }catch(SQLException se){
            Log.e(TAG, "Cound not drop the database table [ " + tName + " ].", se);
        }
       onCreate(db);
    }

    /**
     * Inserta un registro en la BD
     * 
     * @param table - tabla en la que insertamos el registro
     * @param values - Objeto que contiene una lista de pares [Columna , Valor]
     * <br/>
     * <br/>
     * <b>Por ejemplo: </b>
     * <br/>
     * ContentValues initialValues = new ContentValues();<br/>
     * initialValues.put("code", code);<br/>
     * initialValues.put("name", name);<br/>
     *  
     * @return long identificador del registro insertado, o -1 en caso de producirse un error 
     * */
    public long insert(String table, ContentValues values){
        return db.insert(table, null, values);
    }
    
    /**
     * Obtiene una lista de registros de la BD
     * 
     * @param table - nombre de la tabla de la que obtener los registros (obligatorio)
     * @param colums - columnas que obtener en la SQL (obligatorio)
     * @param groupBy - nombre de la columna por la que ordenar los resultados (opcional)
     * @param having - cláusula Having (opcional)
     * @param orderBy - nombre de la columna de ordenación de los resultados (opcional)
     * 
     * @return Cursor posicionado en el primer elemento de los encontrados,
     *  o null en caso de no encontrar ningun resultado
     * */
    public Cursor get(String table, String[] columns, String groupBy, String having, String orderBy){
        return db.query(table, columns, null, null, null, null, null);
    }
    
    /**
     * Obtiene un registro que coincida con el identificador pasado por parámetro
     * 
     * @param table - nombre de la tabla de la que queremos obtener el registro
     * @param columns - columnas del registro que queremos obtener
     * @param id - identificador del registro en la tabla
     * @return Cursor posicionado en el único registro encontrado,
     *  o null en caso de no encontrar registros.
     * */
    public Cursor getById(String table, String[] columns, long id){
        Cursor cursor =db.query(true, table, columns, KEY_ID + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    
    /**
     * Elimina todos los registros de la tabla pasada por parámetro
     * 
     * @param table - nombre de la tabla de la que eliminar los registros
     * 
     * @param número de filas afectadas
     * */
    public int delete(String table) {
        return db.delete(table, "1", null);
    }
    
    /**
     * Elimina de la tabla pasada por parámetro 
     * el registro con id pasado por parámetro
     * 
     * @param table - nombre de la tabla de la que se desea eliminar el registro
     * @param id - identificador del registro que se desea eliminar
     * 
     * @param número de filas afectadas
     * */
    public int delete(String table, long id) {
        return db.delete(table, KEY_ID + "=" + id, null);
    }
    
    /**
     * Actualiza en la tabla pasada por parámetro,
     *  el registro con id pasado por parámetro
     *  
     *  @param table - nombre de la tabla de la que se desea actualizar el registro
     *  @param id - identificador del registro que se desea actualizar
     *  @param values - Objeto que contiene los pares [columna, valor] que se van a actualizar del registro
     *  
     *  @return número de filas afectadas
     * */
    public int update(String table, long id, ContentValues values) {
        return db.update(table, values, KEY_ID + "=" + id, null);
    }
}
