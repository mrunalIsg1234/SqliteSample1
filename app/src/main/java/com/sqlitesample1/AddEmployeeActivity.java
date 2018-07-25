package com.sqlitesample1;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.sqlitesample1.database.DatabaseHandler;
import com.sqlitesample1.database.Employee;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEmployeeActivity extends AppCompatActivity {

//    @BindView(R.id.empNameEditText)EditText empNameText;
//    @BindView(R.id.designationEditText)EditText designationText;
//    @BindView(R.id.ageEditText)EditText ageText;
//    @BindView(R.id.joinDateEditText)EditText joinDateText;


    EditText empNameText;
    EditText designationText;
    EditText ageText;
    EditText joinDateText;

    TextInputLayout empNameLayout,designationLayout,ageLayout,joinDateLayout;
    TextView submitButton;
    String focusDate="";

    Employee mEmployee = new Employee();
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
//        ButterKnife.bind(this);
        empNameText=(EditText)findViewById(R.id.empNameEditText);
        designationText=(EditText)findViewById(R.id.designationEditText);
        ageText=(EditText)findViewById(R.id.ageEditText);
        joinDateText=(EditText)findViewById(R.id.joinDateEditText);
        submitButton=(TextView)findViewById(R.id.submitButton);

        empNameLayout=(TextInputLayout)findViewById(R.id.empNameLayout);
        designationLayout=(TextInputLayout)findViewById(R.id.designationLayout);
        ageLayout=(TextInputLayout)findViewById(R.id.ageLayout);
        joinDateLayout=(TextInputLayout)findViewById(R.id.joinDateLayout);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmployee.name= empNameText.getText().toString().trim();
                mEmployee.designation=designationText.getText().toString().trim();
                mEmployee.age=ageText.getText().toString().trim();
                mEmployee.joiningDate=joinDateText.getText().toString().trim();


                if (TextUtils.isEmpty(mEmployee.name)) {
                    empNameText.requestFocus();
                    empNameLayout.setError(getResources().getString(R.string.error_emp_name));
                    return;
                }

                if (TextUtils.isEmpty(mEmployee.designation)) {
                    designationText.requestFocus();
                    designationLayout.setError(getResources().getString(R.string.error_designation));
                    return;
                }


                if (TextUtils.isEmpty(mEmployee.age)) {
                    ageText.requestFocus();
                    ageLayout.setError(getResources().getString(R.string.error_emp_age));
                    return;
                }


                if (TextUtils.isEmpty(mEmployee.joiningDate)) {
                    joinDateText.requestFocus();
                    joinDateLayout.setError(getResources().getString(R.string.error_join_adate));
                    return;
                }


                DatabaseHandler.getInstance(AddEmployeeActivity.this).putPerson(mEmployee);

                startActivity(new Intent(AddEmployeeActivity.this, MainActivity.class));
                finish();
            }
        });

        joinDateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddEmployeeActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        joinDateText.setText(sdf.format(myCalendar.getTime()));
    }

    /*@OnClick(R.id.submitButton)
    public void onSubmitClick(View view){
        mEmployee.name=empNameText.getText().toString().trim();
        mEmployee.designation=designationText.getText().toString().trim();
        mEmployee.age=ageText.getText().toString().trim();
        mEmployee.joiningDate=joinDateText.getText().toString().trim();

        DatabaseHandler.getInstance(AddEmployeeActivity.this).putPerson(mEmployee);
    }*/


//    @OnTextChanged(value = {R.id.empNameEditText, R.id.designationEditText, R.id.ageEditText,R.id.joinDateLayout}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
//    public void handleTextChanged(Editable editable) {
//        if (editable == empNameText.getEditableText()) {
//            empNameText.setError(null);
//            empNameLayout.setErrorEnabled(false);
//        } else if (editable == designationText.getEditableText()) {
//            designationText.setError(null);
//            designationLayout.setErrorEnabled(false);
//        } else if (editable == ageText.getEditableText()) {
//            ageText.setError(null);
//            ageLayout.setErrorEnabled(false);
//        }else if(editable==joinDateText.getEditableText()){
//            joinDateText.setError(null);
//            joinDateLayout.setErrorEnabled(false);
//        }
//    }

}
