package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseCreator extends SQLiteOpenHelper {

	public DataBaseCreator(Context context) {
		super(context, "db_Cronoanalise", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL("CREATE TABLE filme (id integer primary key autoincrement, nome text not null, genero text not null);");
		database.execSQL("insert into filme(nome, genero) values ('Jogos Mortais', 'Terror')");
		database.execSQL("insert into filme(nome, genero) values ('As Branquelas', 'Comédia')");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {
		database.execSQL("DROP TABLE IF EXISTS filme");
		onCreate(database);
	}

}
