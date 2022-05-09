package com.example.davidharrisgreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Activity2 extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private Button button;
    private TextView email_error_message;
    private TextView password_error_message;
    private boolean email_good;
    private boolean password_good;
    private static final String EMAIL_REGEX = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        editTextUserName = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextPassword2 = findViewById(R.id.editTextTextPassword2);
        email_error_message = (TextView)findViewById(R.id.textView10);
        password_error_message = (TextView)findViewById(R.id.textView11);
        button = findViewById(R.id.button);

        android.widget.ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(view -> openActivity());

        android.widget.Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show());

        editTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                email_changed();
                validate_next_button();
            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password_changed();
                validate_next_button();
            }
        });

        editTextPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password_changed();
                validate_next_button();
            }
        });
    }

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void email_changed() {
        String email_address = (String) editTextUserName.getText().toString();

        if (email_address.matches(EMAIL_REGEX)) {
            email_good = true;

            editTextUserName.setBackgroundResource(R.drawable.thick_good_highlight);

            Drawable check_image = editTextUserName.getContext().getResources().getDrawable(R.drawable.tick, null);
            editTextUserName.setCompoundDrawablesWithIntrinsicBounds(null, null, check_image, null);

            email_error_message.setVisibility(View.INVISIBLE);
        }

        else
        {
            email_good = false;

            editTextUserName.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);

            email_error_message.setVisibility(View.VISIBLE);
        }


    }

    private void password_changed() {
        String password1 = (String) editTextPassword.getText().toString();
        String password2 = (String) editTextPassword2.getText().toString();

        password_good = true;

        if (! password1.equals(password2)) {
            password_good = false;

            password_error_message.setText(" Your passwords do not match");
        }
        else
        {
            if ( (password1.length() < 8) ||
                    (! password1.matches(".*[0-9]+.*")) ||
                    (! password1.matches(".*[a-z]+.*")) ||
                    (! password1.matches(".*[A-Z]+.*")))
            {
                password_good = false;

                password_error_message.setText(" Your password is invalid, see criteria");
            }
        }

        if (password_good) {
            password_error_message.setVisibility(View.INVISIBLE);

            editTextPassword.setBackgroundResource(R.drawable.thick_good_highlight);
            editTextPassword2.setBackgroundResource(R.drawable.thick_good_highlight);

            Drawable check_image = editTextPassword.getContext().getResources().getDrawable(R.drawable.tick, null);
            editTextPassword.setCompoundDrawablesWithIntrinsicBounds(null, null, check_image, null);
            editTextPassword2.setCompoundDrawablesWithIntrinsicBounds(null, null, check_image, null);
        }
        else
        {
            password_error_message.setVisibility(View.VISIBLE);
            editTextPassword.setBackgroundResource(R.drawable.thick_error_highlight);
            editTextPassword2.setBackgroundResource(R.drawable.thick_error_highlight);

            editTextPassword.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            editTextPassword2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }


    private void validate_next_button()
    {

        if (email_good && password_good)
        {
            button.setEnabled(true);
        }
        else
        {
            button.setEnabled(false);
        }
    }
}
