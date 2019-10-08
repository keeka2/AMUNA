package com.example.amuna;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.Calendar;

public class BirthPickerDialog extends DialogFragment {
    OnMyDialogResult mDialogResult;
    private static final int MAX_YEAR = 2000;
    private static final int MIN_YEAR = 1970;

    private DatePickerDialog.OnDateSetListener listener;
    public Calendar cal = Calendar.getInstance();

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    Button btnConfirm;
    Button btnCancel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.birth_picker_dialog, null);

        btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        final NumberPicker dayPicker = dialog.findViewById(R.id.picker_day);
        final NumberPicker monthPicker = dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker =  dialog.findViewById(R.id.picker_year);

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BirthPickerDialog.this.getDialog().cancel();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), dayPicker.getValue());
                BirthPickerDialog.this.getDialog().cancel();
                if( mDialogResult != null ){
                    String year,month,day;
                    String fullbirth;
                    year = Integer.toString(yearPicker.getValue());
                    month = Integer.toString(monthPicker.getValue());
                    day = Integer.toString(dayPicker.getValue());
                    if(month.length() == 1){
                        month = "0" + month;
                    }
                    if(day.length() == 1){
                        day = "0" + day;
                    }
                    fullbirth = year + month + day;
                    mDialogResult.finish(fullbirth);
                }
            }
        });

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        yearPicker.setValue(cal.get(Calendar.DAY_OF_MONTH));

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(MIN_YEAR);
        yearPicker.setMaxValue(MAX_YEAR);
        yearPicker.setValue(year);

        builder.setView(dialog)
        // Add action buttons
        /*
        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), 0);
            }
        })
        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MyYearMonthPickerDialog.this.getDialog().cancel();
            }
        })
        */
        ;

        return builder.create();
    }

    public void setDialogResult(OnMyDialogResult dialogResult){

        mDialogResult = dialogResult;

    }

    public interface OnMyDialogResult{

        void finish(String result);

    }
}
