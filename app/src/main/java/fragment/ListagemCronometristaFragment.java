package fragment;

import android.app.Fragment;
import android.widget.ListView;

import java.util.List;

import domain.Cronometrista;
import tela.tearapp.Cronometria;

/**
 * Created by Ruan on 17/11/2015.
 */
public class ListagemCronometristaFragment extends Fragment {
    private ListView listViewCronometristas;
    private Cronometrista cronometrista;
    //ListagemAlunoAdapter adapter;
    List<Cronometrista> cronometristas;
}
