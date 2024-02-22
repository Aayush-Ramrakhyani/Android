package com.example.nat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SingleChoice4 extends DialogFragment {
    int position2=0;
    public interface SingleChoice4Listener
    {
        void onPositiveButtonClicked2(String[] list2,int position2);
        void onNegativeButtonClicked2();
    }
    SingleChoice4Listener m;
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            m = (SingleChoice4Listener) context;
        }catch(Exception e){
            throw new ClassCastException(getActivity().toString()+"SingleChoice4Listener must implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        final String [] list2=getActivity().getResources().getStringArray(R.array.team2);
        builder.setTitle("Select team").setSingleChoiceItems(list2,position2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                position2=i;
            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m.onPositiveButtonClicked2(list2,position2);

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                m.onNegativeButtonClicked2();
            }
        });
        return builder.create();
    }

}
