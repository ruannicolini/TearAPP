package util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import tela.tearapp.R;

public class MyDialogFragment extends DialogFragment {

    /*
     * A activity que criar uma instância desse dialog fragment deve implementar
     * essa interface para receber os retornos da chamada do evento.
     */
    public interface MyDialogFragmentListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Usa essa instância da interface para entregar eventos de ação
    MyDialogFragmentListener mListener;

    public static MyDialogFragment newInstance(String titulo) {
        MyDialogFragment dialog = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", titulo);
        dialog.setArguments(args);
        return dialog;
    }

    // Sobrescreve o método onAttach para instanciar o MyDialogFragmentListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verifica se a activity implementa a interface de callbacks
        try {
            // Instancia o MyDialogFragmentListener para que possamos enviar
            // eventos para o host
            mListener = (MyDialogFragmentListener) activity;
        } catch (ClassCastException e) {
            // Essa activity não implementa a interface, levanta exceção
            throw new ClassCastException(activity.toString()
                            + " deve implementar MyDialogFragmentListener");

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        Dialog myDialog = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dispara o evento onDialogPositiveClick para a
                        // activity que estiver escultando
                        mListener.onDialogPositiveClick(MyDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dispara o evento onDialogNegativeClick para a
                // activity que estiver escultando
                mListener.onDialogNegativeClick(
                        MyDialogFragment.this);
            }
        }).create();
        return myDialog;
    }


}