package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Cronometrista;
import domain.Grupo;
import domain.Operacao;
import domain.Operador;
import domain.Produto;
import domain.Tecido;
import domain.TipoRecurso;

public class DataBaseCreator extends SQLiteOpenHelper {
	CronometragemJDBCDao cronometragemDao;
	CronometristaJDBCDao cronometristaDao;
	GrupoJDBCDao grupoDao;
	TecidoJDBCDao tecidoDao;
	OperadorJDBCDao operadorDao;
	ProdutoJDBCDao produtoDao;
	OperacaoJDBCDao operacaoDao;
	TipoRecursoJDBCDao tipoRecursoJDBCDao;

	public DataBaseCreator(Context context) { super(context, "db_cronoanalise", null, 1); }

	@Override
	public void onCreate(SQLiteDatabase database) {
		cronometragemDao = new CronometragemJDBCDao();
		cronometristaDao = new CronometristaJDBCDao();
		grupoDao = new GrupoJDBCDao();
		tecidoDao = new TecidoJDBCDao();
		operadorDao = new OperadorJDBCDao();
		produtoDao = new ProdutoJDBCDao();
		operacaoDao = new OperacaoJDBCDao();
		tipoRecursoJDBCDao = new TipoRecursoJDBCDao();

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

		//Operador
		database.execSQL("CREATE TABLE operador (idoperador integer not null primary key, idgrupo integer not null, nome text not null);");
		ArrayList<Operador> operadores = null;
		try {operadores = new ArrayList(operadorDao.obterOperadores()); } catch (SQLException e) { e.printStackTrace();	}
		for(int i =0; i< operadores.size(); i++){
			ContentValues values = new ContentValues();
			values.put("idOperador", operadores.get(i).getIdOperador());
			values.put("nome", operadores.get(i).getNome());
			values.put("idGrupo", operadores.get(i).getGrupo().getIdGrupo());
			database.insert("operador", null, values);
		}

		//Produto
		database.execSQL("CREATE TABLE produto (idproduto integer primary key, " +
				"descricao text);");
		ArrayList<Produto> produtos = null;
		produtos = new ArrayList(produtoDao.obterProdutos());
		for(int i =0; i< produtos.size(); i++){
			ContentValues values = new ContentValues();
			values.put("idProduto", produtos.get(i).getIdProduto());
			values.put("descricao", produtos.get(i).getDescricao());
			database.insert("produto", null, values);
		}

		//Operacao
		database.execSQL("CREATE TABLE operacao (idoperacao integer primary key, " +
				"descricao text," +
				"acao text," +
				"parte text," +
				"fase text," +
				"idacao integer not null," +
				"idfase integer not null," +
				"idparte integer not null);");
		ArrayList<Operacao> operacoes = null;
		operacoes = new ArrayList(operacaoDao.obterOperacoes());
		for(int i =0; i< operacoes.size(); i++){
			ContentValues values = new ContentValues();
			values.put("idoperacao", operacoes.get(i).getIdOperacao());
			values.put("idacao", operacoes.get(i).getIdAcao());
			values.put("acao", operacoes.get(i).getAcao());
			values.put("idfase", operacoes.get(i).getIdFase());
			values.put("fase", operacoes.get(i).getFase());
			values.put("idparte", operacoes.get(i).getIdParte());
			values.put("parte", operacoes.get(i).getParte());
			values.put("descricao", operacoes.get(i).getDescricao());
			database.insert("operacao", null, values);
		}

		//Tipo Recurso
		database.execSQL("CREATE TABLE tipo_Recurso (idtipo_recurso integer not null primary key, descricao text);");
		ArrayList<TipoRecurso> recursos = null;
		try {recursos = new ArrayList(tipoRecursoJDBCDao.obterTiposRecurso());} catch (SQLException e) {e.printStackTrace();}
		for(int i =0; i< recursos.size(); i++){
			ContentValues values = new ContentValues();
			values.put("idtipo_recurso", recursos.get(i).getIdTipoRecurso());
			values.put("descricao", recursos.get(i).getDescricao());
			database.insert("tipo_Recurso", null, values);
		}

		//Cronometragem
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
				"idOperacao integer not null," +
				"referencia text);");

		database.execSQL("CREATE TABLE cronometragem_has_tipo_Recurso (idcronometragem integer not null, idtipo_recurso integer not null);");
		database.execSQL("CREATE TABLE batida (idbatida integer primary key autoincrement, " +
				"minutos integer not null," +
				"segundos integer not null," +
				"centesimos integer not null," +
				"utilizar numeric not null," +
				"idcronometragem integer not null);");


	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {
		//database.execSQL("DROP TABLE IF EXISTS filme");
		onCreate(database);
	}

}
