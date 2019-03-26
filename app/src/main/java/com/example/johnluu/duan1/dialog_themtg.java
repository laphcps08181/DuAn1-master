package com.example.johnluu.duan1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dialog_themtg extends AppCompatDialogFragment {
    public EditText etthemtg;
    public Button bthuy,btthem;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themtg,null);
        builder.setView(view);


        etthemtg = view.findViewById(R.id.etadd);
        bthuy = view.findViewById(R.id.bthuy);
        btthem = view.findViewById(R.id.btthem);
    return builder.create();
    }
}
