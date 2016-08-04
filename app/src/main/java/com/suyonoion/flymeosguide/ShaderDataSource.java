package com.suyonoion.flymeosguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ShaderDataSource
{
	public static final String TABLE = "shaders";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_SHADER = "shader";
	public static final String COLUMN_THUMB = "thumb";
	public static final String COLUMN_CREATED = "nama";



	private SQLiteDatabase db;
	private OpenHelper helper;
	private Context context;

	public ShaderDataSource( Context c )
	{
		helper = new OpenHelper( c );
		context = c;
	}

	public void open() throws SQLException
	{
		db = helper.getWritableDatabase();
	}

	public void close()
	{
		helper.close();
	}

	public Cursor queryAll()
	{
		return db.rawQuery(
			"SELECT "+
				COLUMN_ID+","+
				COLUMN_SHADER+","+
				COLUMN_THUMB+","+
                    COLUMN_CREATED+
				" FROM "+TABLE+
				" ORDER BY "+COLUMN_ID,
			null );
	}

	public String getShader( long id )
	{
		try
		{
			Cursor c = db.rawQuery(
				"SELECT "+
					COLUMN_SHADER+
					" FROM "+TABLE+
					" WHERE "+COLUMN_ID+"="+id,
				null );

			if( c == null )
				return null;

			c.moveToFirst();

			return c.getString( 0 );
		}
		catch( Exception e )
		{
			return null;
		}
	}

	public Cursor getRandomShader()
	{
		try
		{
			Cursor c = db.rawQuery(
				"SELECT "+
					COLUMN_ID+", "+
					COLUMN_SHADER+
					" FROM "+TABLE+
					" ORDER BY RANDOM() LIMIT 1",
				null );

			if( c == null )
				return null;

			c.moveToFirst();

			return c;
		}
		catch( Exception e )
		{
			return null;
		}
	}



	public static long insert(
		SQLiteDatabase db,
		String shader,
		byte[] thumbnail, String nama )
	{


		ContentValues cv = new ContentValues();
		cv.put( COLUMN_SHADER, shader );
		cv.put( COLUMN_THUMB, thumbnail );
		cv.put( COLUMN_CREATED, nama);



		return db.insert( TABLE, null, cv );
	}

	public long insert( String shader, byte[] thumbnail, String nama )
	{
		return insert( db, shader, thumbnail, nama);
	}

	public long insert()
	{
		try
		{
			return insert(
				db,
				loadRawResource( R.raw.baru ),
				loadBitmapResource( R.drawable.ic_launcher ),
                loadNamaResource(R.raw.tambah)+" | "+loadTanggal()
            );
		}
		catch( Exception e )
		{
			return 0;
		}
	}

    public void update( long id, String shader, byte[] thumbnail )
    {
        ContentValues cv = new ContentValues();
        cv.put( COLUMN_SHADER, shader );
        cv.put( COLUMN_THUMB, loadBitmapResource( R.drawable.ic_launcher ) );
        try {
            cv.put( COLUMN_CREATED, loadNamaResource(R.raw.tambah)+" | "+loadTanggal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.update(
                TABLE,
                cv,
                COLUMN_ID+"="+id,
                null );
    }



    public void remove( long id )
	{
		db.delete(
			TABLE,
			COLUMN_ID+"="+id,
			null );
	}



	private String loadRawResource( int id ) throws Exception
	{
		InputStream in = context.getResources().openRawResource( id );
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		for( int r; (r = in.read( buf )) != -1; )
			out.write( buf, 0, r );

		return out.toString();
	}

	private byte[] loadBitmapResource( int id )
	{
		Bitmap image = BitmapFactory.decodeResource(
                context.getResources(),
                id);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		image.compress( Bitmap.CompressFormat.PNG, 100, out );

		return out.toByteArray();
	}

    private String loadNamaResource( int id ) throws Exception
    {
        InputStream in = context.getResources().openRawResource( id );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        for( int r; (r = in.read( buf )) != -1; )
            out.write( buf, 0, r );

        return out.toString();
    }
    private String loadTanggal()
    {
        return new SimpleDateFormat(
                "yyyy-MM-dd | HH:mm" ).format(
                new Date() );
    }

	private class OpenHelper extends SQLiteOpenHelper
	{
		public OpenHelper( Context c )
		{
			super( c, "suyonoionflymeOS.db", null, 1 );
		}

		@Override
		public void onCreate( SQLiteDatabase db )
		{
			db.execSQL(
				"CREATE TABLE "+TABLE+" ("+
					COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
					COLUMN_SHADER+" TEXT NOT NULL,"+
					COLUMN_THUMB+" BLOB,"+
                    COLUMN_CREATED+" TEXT NOT NULL );" );

			try
			{
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.flymeos_garuda_style_by_ion ),
                        loadBitmapResource(
                                R.drawable.garuda1 ),
                        loadNamaResource(R.raw.namaflymeos_garuda_style_by_ion)+" | "+loadTanggal()
                );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.flymeos_3jari_style_by_ion ),
                        loadBitmapResource(
                                R.drawable.tigajari ),
                        loadNamaResource(R.raw.namaflymeos_3jari_style_by_ion)+" | "+loadTanggal()
                );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.flymeos_anu_style_by_ion ),
                        loadBitmapResource(
                                R.drawable.anu ),
                        loadNamaResource(R.raw.namaflymeos_anu_style_by_ion)+" | "+loadTanggal()
                );

                ShaderDataSource.insert(
					db,
					loadRawResource(
						R.raw.status2bar ),
					loadBitmapResource(
						R.drawable.duabar ),
                        loadNamaResource(R.raw.duabar)+" | "+loadTanggal()
                        );

				ShaderDataSource.insert(
					db,
					loadRawResource(
						R.raw.status3bar ),
					loadBitmapResource(
						R.drawable.tigabar ),
                        loadNamaResource(R.raw.tigabar)+" | "+loadTanggal()
                        );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.status4bar ),
                        loadBitmapResource(
                                R.drawable.empatbar ),
                        loadNamaResource(R.raw.empatbar)+" | "+loadTanggal()
                        );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.more ),
                        loadBitmapResource(
                                R.drawable.lebihbar ),
                        loadNamaResource(R.raw.morebar)+" | "+loadTanggal()
                        );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.more_bonus ),
                        loadBitmapResource(
                                R.drawable.ic_launcher ),
                        loadNamaResource(R.raw.more_bonus_string)+" | "+loadTanggal()
                        );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.guide ),
                        loadBitmapResource(
                                R.drawable.ic_guide ),
                        loadNamaResource(R.raw.guidetxt)+" | "+loadTanggal()
                );
                ShaderDataSource.insert(
                        db,
                        loadRawResource(
                                R.raw.tentang ),
                        loadBitmapResource(
                                R.drawable.ic_launcher ),
                        loadNamaResource(R.raw.infotentang)+" | "+loadTanggal()
                        );
			}
			catch( Exception e )
			{
			}
		}

		@Override
		public void onUpgrade(
			SQLiteDatabase db,
			int oldVersion,
			int newVersion )
		{
			db.execSQL( "DROP TABLE IF EXISTS "+TABLE );
			onCreate( db );
		}
	}
}
