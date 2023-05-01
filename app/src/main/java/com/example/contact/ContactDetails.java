package com.example.contact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contact.controller.ContactService;
import com.example.contact.entities.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactDetails extends AppCompatActivity {

    //view
    private TextView nameTv,phoneTv,emailTv,villeTv,jobTv;
    private ImageView profileIv;

    TextView contactEdit,contactDelete;
    String firstName,lastName,phone,email,job,ville;
    private Long id;
    String url = "http://192.168.56.1:8082/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        //get data from intent
        Intent intent = getIntent();
        id = intent.getLongExtra("contactId",0);
        System.out.println(id);
        //init view
        nameTv = findViewById(R.id.nameTv);
        phoneTv = findViewById(R.id.phoneTv);
        emailTv = findViewById(R.id.emailTv);
        profileIv = findViewById(R.id.profileIv);
        jobTv = findViewById(R.id.jobeTv);
        villeTv = findViewById(R.id.villeTv);
        contactEdit = findViewById(R.id.contact_Edit);
        contactDelete = findViewById(R.id.contact_delete);
       // handle edit click
        contactEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent to move AddEditActivity to update data
                Intent intent = new Intent(ContactDetails.this,AddEditContact.class);
                //pass the value of current position
                intent.putExtra("ID",id);
                intent.putExtra("FIRSTNAME",firstName);
                intent.putExtra("LASTNAME",lastName);
                intent.putExtra("PHONE",phone);
                intent.putExtra("EMAIL",email);
                intent.putExtra("JOB",job);
                intent.putExtra("VILLE",ville);

                // pass a boolean data to define it is for edit purpose
                intent.putExtra("isEditMode",true);

                //start intent
                ContactDetails.this.startActivity(intent);

            }
        });
        // handle delete click
        // handle delete click
        contactDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we need database helper class reference
                Retrofit retrofit = new Retrofit.Builder( ).baseUrl(url).addConverterFactory(GsonConverterFactory.create( )).build( );
                ContactService api = retrofit.create(ContactService.class);
                api.deleteContact(id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // show a message or perform some action to indicate successful deletion
                            Toast.makeText(getApplicationContext(), "Contact of "+firstName+" is deleted", Toast.LENGTH_LONG).show();
                            // set the result and finish the activity
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("data_updated", true);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        } else {
                            // show an error message or perform some action to handle the error
                            Toast.makeText(getApplicationContext(), "Failed to delete contact", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // show an error message or perform some action to handle the error
                        Toast.makeText(getApplicationContext(), "Failed to delete contact", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        loadDataById();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == Activity.RESULT_OK) {
            if (data != null && data.getBooleanExtra("data_updated", false)) {
                // reload the data
                loadDataById();
            }
        }
    }
    private void loadDataById() {


        // we need database helper class reference
        Retrofit retrofit = new Retrofit.Builder( ).baseUrl(url).addConverterFactory(GsonConverterFactory.create( )).build( );
        ContactService api = retrofit.create(ContactService.class);
        Call<Contact> call= api.getContact(id);
        call.enqueue(new Callback<Contact>( ) {

            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact data = response.body( );
                firstName=data.getFirstName();
                lastName=data.getLastName();
                phone=data.getPhone();
                email=data.getEmail();
                ville = data.getVille();
                job = data.getJob();
                //set data
                nameTv.setText(firstName+" "+lastName);
                phoneTv.setText(phone);
                emailTv.setText(email);
                jobTv.setText(job);
                villeTv.setText(ville);
                //profileIv.setImageResource(R.drawable.ic_baseline_person_24);
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataById(); // refresh data
    }

}