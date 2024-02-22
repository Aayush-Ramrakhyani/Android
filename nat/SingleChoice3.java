package com.example.nat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SingleChoice3 extends DialogFragment {
    int position=0;
    public interface SingleChoice3Listener
    {
        void onPositiveButtonClicked(String[] list,int position);
        void onNegativeButtonClicked();
    }
    SingleChoice3Listener m;
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            m = (SingleChoice3Listener) context;
        }catch(Exception e){
            throw new ClassCastException(getActivity().toString()+"SingleChoice3Listener must implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        final String [] list=getActivity().getResources().getStringArray(R.array.team);
        builder.setTitle("Select team").setSingleChoiceItems(list,position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                position=i;
            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m.onPositiveButtonClicked(list,position);

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                m.onNegativeButtonClicked();
            }
        });
        return builder.create();
    }

}
