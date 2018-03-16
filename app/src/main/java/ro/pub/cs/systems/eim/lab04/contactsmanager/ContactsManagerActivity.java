package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    // buttons
    private Button show;
    private Button save;
    private Button cancel;
    // layout
    private LinearLayout extra;
    // text
    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;
    private EditText addressText;
    private EditText jobText;
    private EditText companyText;
    private EditText websiteText;
    private EditText imText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        // buttons
        show = findViewById(R.id.show);
        show.setOnClickListener(listener);
        save = findViewById(R.id.save);
        save.setOnClickListener(listener);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(listener);
        // layout
        extra = findViewById(R.id.extraInfo);
        // text
        nameText = findViewById(R.id.name);
        phoneText = findViewById(R.id.phone);
        emailText = findViewById(R.id.email);
        addressText = findViewById(R.id.add);
        jobText = findViewById(R.id.job);
        companyText = findViewById(R.id.company);
        websiteText = findViewById(R.id.website);
        imText = findViewById(R.id.im);
    }

    private Listener listener = new Listener();
    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int current = view.getId();

            switch (current) {
                case R.id.show: {
                    // hide
                    if (show.getText().toString().compareTo("Hide Additional Fields") == 0) {
                        show.setText("Show Additional Fields");
                        extra.setVisibility(View.GONE);
                    } else {// show
                        show.setText("Hide Additional Fields");
                        extra.setVisibility(View.VISIBLE);
                    }
                }

                case R.id.save: {
                    String name = nameText.getText().toString();
                    String phone = phoneText.getText().toString();
                    String email = emailText.getText().toString();
                    String address = addressText.getText().toString();
                    String jobTitle = jobText.getText().toString();
                    String company = companyText.getText().toString();
                    String website = websiteText.getText().toString();
                    String im = imText.getText().toString();

                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    if (name != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                    }
                    if (phone != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                    }
                    if (email != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                    }
                    if (address != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                    }
                    if (jobTitle != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                    }
                    if (company != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                    }
                    ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                    if (website != null) {
                        ContentValues websiteRow = new ContentValues();
                        websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                        websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                        contactData.add(websiteRow);
                    }
                    if (im != null) {
                        ContentValues imRow = new ContentValues();
                        imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                        imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                        contactData.add(imRow);
                    }
                    intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                    startActivity(intent);
                }

                case R.id.cancel:{
                    finish();
                }
            }
        }
    }
}
