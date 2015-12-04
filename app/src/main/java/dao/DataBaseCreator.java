package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Cronometrista;
import domain.Grupo;
import domain.Tecido;

public class DataBaseCreator extends SQLiteOpenHelper {
	CronometragemJDBCDao cronometragemDao;
	CronometristaJDBCDao cronometristaDao;
	GrupoJDBCDao grupoDao;
	TecidoJDBCDao tecidoDao;

	public DataBaseCreator(Context context) { super(context, "db_cronoanalise", null, 1); }

	@Override
	public void onCreate(SQLiteDatabase database) {
		cronometragemDao = new CronometragemJDBCDao();
		cronometristaDao = new CronometristaJDBCDao();
		grupoDao = new GrupoJDBCDao();
		tecidoDao = new TecidoJDBCDao();


		//Cronometrista
		database.execSQL("CREATE TABLE cronometrista (idcronometrista integer primary key, nome text not null);");
		ArrayList<Cronometrista> cronometristas = new ArrayList(cronometristaDao.obterCronometristas());
		for(int i =0; i< cronometristas.size(); i++){
			//database.execSQL("insert into cronometrista(idcronometrista, nome) values ("+ cronometristas.get(i).getIdCronometrista() + ","+ cronometristas.get(i).getNome()+")");
			ContentValues values = new ContentValues();
			values.put("idcronometrista", cronometristas.get(i).getIdCronometrista());
			values.put("nome", cronometristas.get(i).getNome());
			database.insert("cronometrista", null, values);
		}

		//Grupo
		database.execSQL("CREATE TABLE grupo (idgrupo integer not null primary key, descricao text);");
		ArrayList<Grupo> grupos = null;
		try {grupos = new ArrayList(grupoDao.obterGrupos()); } catch (SQLException e) { e.printStackTrace();	}
		for(int i =0; i< grupos.size(); i++){
			ContentValues values = new ContentValues();
			values.put("idgrupo", grupos.get(i).getIdGrupo());
			values.put("descricao", grupos.get(i).getDescricao());
			database.insert("grupo", null, values);
		}

		//Tecido
		database.execSQL("CREATE TABLE tecido (idtecido integer not null primary key, descricao text);");
		ArrayList<Tecido> tecidos = null;
		try {tecidos = new ArrayList(tecidoDao.obterTecidos()); } catch (SQLException e) { e.printStackTrace();	}
		for(int i =0; i< tecidos.size(); i++){
			ContentValues values = new ContentValues();
			values.put("idTecido", tecidos.get(i).getIdTecido());
			values.put("descricao", tecidos.get(i).getDescricao());
			database.insert("tecido", null, values);
		}

		database.execSQL("CREATE TABLE tipo_Recurso (idtipo_recurso integer not null primary key, descricao text);");

		database.execSQL("CREATE TABLE operador (idoperador integer not null primary key, idgrupo integer not null, nome text not null);");

		database.execSQL("CREATE TABLE produto (idproduto integer primary key, " +
				"descricao text," +
				"data numeric," +
				"idGrupo integer not null);");//Grupo de Produto

		database.execSQL("CREATE TABLE operacao (idoperacao integer primary key, " +
				"descricao text," +
				"acao text," +
				"parte text," +
				"fase text," +
				"idAcao integer not null," +
				"idFase integer not null," +
				"idParte integer not null);");

		database.execSQL("CREATE TABLE cronometragem (idcronometragem integer primary key autoincrement, " +
				"ritmo integer not null," +
				"num_pecas integer not null," +
				"tolerancia integer not null," +
				"comprimento_prod real not null," +
				"num_ocorrencia integer not null," +
				"idProduto integer not null," +
				"idCronometrista integer not null," +
				"idTecido integer not null," +
				"idOperador integer not null," +
				"idOperacao integer not null);");

		database.execSQL("CREATE TABLE cronometragem_has_tipo_Recurso (idcronometragem integer not null, idtipo_recurso integer not null);");

		database.execSQL("CREATE TABLE batida (idbatida integer primary key autoincrement, " +
				"minutos integer not null," +
				"segundos integer not null," +
				"centesimos integer not null," +
				"utilizar numeric not null," +
				"idCronometragem integer not null);");

		//database.execSQL("insert into cronometragem(ritmo, num_pecas, tolerancia, comprimento_prod, num_ocorrencia," +
		//		"idProduto, idCronometrista, idTecido, idOperador, idOperacao) values ('Jogos Mortais', 'Terror')");

		//database.execSQL("insert into filme(nome, genero) values ('As Branquelas', 'Com�dia')");
		//database.execSQL("insert into filme(nome, genero) values ('As Branquelas', 'Com�dia')");

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {
		//database.execSQL("DROP TABLE IF EXISTS filme");
		onCreate(database);
	}

}
