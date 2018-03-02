package com.example.candice_feng.training.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.widget.Toast;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactAdapter {

    /**
     * An array of sample (dummy) items.
     */
    //public static final List<ContactItem> ITEMS = new ArrayList<ContactItem>();
    Cursor getPhoneNumber;
    ContentResolver resolver;

    Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public List<ContactItem> getData() {
        List<ContactItem> data = new ArrayList<>();

        getPhoneNumber = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (getPhoneNumber != null) {
            Log.e("count", "" + getPhoneNumber.getCount());
            if (getPhoneNumber.getCount() == 0) {
                Toast.makeText(context, "No contacts in your contact list.", Toast.LENGTH_LONG).show();
            }

            while (getPhoneNumber.moveToNext()) {
                String id = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String phoneType = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHONETIC_NAME));

                //String EmailAddr = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA2));
                //String image_thumb = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

                ContactItem contactItem = new ContactItem(id, name);
                //contactItem.setImagepath(image_thumb);
                contactItem.addNumber(phoneNumber, phoneType);
                //contactItem.setCheckedBox(false);
                //contactItem.setEmail(id);
                data.add(contactItem);
            }
        } else {
            Log.e("Cursor close 1", "----");
        }

        getPhoneNumber.close();

        return data;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ContactItem {
        public String id;
        public String name;
        // public ArrayList<ContactEmail> emails;
        public ArrayList<ContactPhone> numbers;

        public ContactItem(String id, String name) {
            this.id = id;
            this.name = name;
            //this.emails = new ArrayList<ContactEmail>();
            this.numbers = new ArrayList<ContactPhone>();
        }

        @Override
        public String toString() {
            String result = name;
            if (numbers.size() > 0) {
                ContactPhone number = numbers.get(0);
                result += " (" + number.number + " - " + number.type + ")";
            }

            return result;
        }


        public void addNumber(String number, String type) {
            numbers.add(new ContactPhone(number, type));
        }
    }

    public static class ContactPhone {
        public String number;
        public String type;

        public ContactPhone(String number, String type) {
            this.number = number;
            this.type = type;
        }
    }

    public static class ContactEmail {
        public String address;
        public String type;

        public ContactEmail(String address, String type) {
            this.address = address;
            this.type = type;
        }
    }
}
