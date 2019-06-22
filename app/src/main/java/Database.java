/**import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "caycimDB";//database adı

    private static final String USER_TABLE= "Users";
    private static String USER_PHONE = "phone";
    private static String USER_ID = "id";
    private static String USER_EMAIL = "email";
    private static String USER_PASS = "pass";


    private static final String ORDER_TABLE= "Orders";
    private static String ORDER_NAME = "order";
    private static String ORDER_ID = "id";
    private static String ORDER_ADET = "adet";
    private static String ORDER_FIYAT = "fiyat";




    private static final String SHOP_TABLE= "Users";
    private static String SHOP_NAME = "name";
    private static String SHOP_ID = "id";
    private static String SHOP_ADDRESS = "address";




    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE + "("
                + USER_ID + " INTEGER PRIMARY KEY, "
                + USER_PHONE + " TEXT NOT NULL, "
                + USER_EMAIL + " TEXT NOT NULL, "
                + USER_PASS + " TEXT NOT NULL   )");


        db.execSQL("CREATE TABLE " + ORDER_TABLE + "("
                + ORDER_ID + " INTEGER PRIMARY KEY, "
                + ORDER_NAME + "TEXT NOT NULL, "
                + ORDER_FIYAT + "INTEGER, "
                + ORDER_ADET + " INTEGER   )");

        db.execSQL("CREATE TABLE " + SHOP_TABLE + "("
                + SHOP_ID + " INTEGER PRIMARY KEY, "
                + SHOP_NAME + " TEXT NOT NULL, "
                + SHOP_ADDRESS + " TEXT NOT NULL   )");
    }

    public void orderDelete(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ORDER_TABLE, ORDER_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public void orderAdd(String orderName, String orderAdet,String orderPrice) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_NAME, orderName);
        values.put(ORDER_ADET, orderAdet);
        values.put(ORDER_FIYAT, orderPrice);


        db.insert(ORDER_TABLE, null, values);
        db.close();
    }

    public void userAdd(String userPhone, String userMail, String userPass){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_PHONE,userPhone);
        values.put(USER_EMAIL,userMail);
        values.put(USER_PASS,userPass);

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void shopAdd(String shopName, String shopAddress){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SHOP_NAME,shopName);
        values.put(SHOP_ADDRESS,shopAddress);

        db.insert(SHOP_TABLE, null, values);
        db.close();
    }

    public HashMap<String, String> orderDetails(int id){
        //Databeseden id si belli olan row u çekmek için.
        //Bu methodda sadece tek row değerleri alınır.
        //HashMap bir çift boyutlu arraydir.anahtar-değer ikililerini bir arada tutmak için tasarlanmıştır.
        //map.put("x","300"); mesala burda anahtar x değeri 300.

        HashMap<String,String> order = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + ORDER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            order.put(ORDER_NAME, cursor.getString(1));
            order.put(ORDER_ADET, cursor.getInt(2));
            order.put(ORDER_FIYAT, cursor.getInt(3));

        }
        cursor.close();
        db.close();
        return order;
    }

    public HashMap<String, String> userDetails(){

        HashMap<String,String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put(USER_PHONE, cursor.getString(1));
            user.put(USER_EMAIL, cursor.getString(2));
            user.put(USER_PASS, cursor.getString(3));
        }
        cursor.close();
        db.close();

        return user;
    }


    public int getRowCount() {
        // Bu method bu uygulamada kullanılmıyor ama her zaman lazım olabilir.Tablodaki row sayısını geri döner.
        //Login uygulamasında kullanacağız
        String countQuery = "SELECT  * FROM " + ORDER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        return rowCount;
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
 **/