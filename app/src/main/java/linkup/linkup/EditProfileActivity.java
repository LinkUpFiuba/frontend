package linkup.linkup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import linkup.linkup.model.SingletonUser;
import linkup.linkup.model.User;

public class EditProfileActivity extends BaseActivity {

    boolean changed;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        changed = false;

        getMenuInflater().inflate(R.menu.edit_profile_menu, menu);
        MenuItem item = (MenuItem) menu.findItem(R.id.make_changes_profile);
        View makeChangesButton = (View) item.getActionView();
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                User user = SingletonUser.getUser();

                checkForChange();
                if (changed) {
                    changed=false;
                    updateUser(user,true);
                }

                return true;
            }
        });
        return true;


    }

    private void checkForChange(){
        User user = SingletonUser.getUser();
        TextView proffesionText = (TextView) findViewById(R.id.editable_proffesionText);
        String oldWork=proffesionText.getText().toString();
        if ( !oldWork.equals(user.work)) {
            user.work = proffesionText.getText().toString();
            changed = true;
        }
        TextView centerStudyText = (TextView) findViewById(R.id.editable_centerStudyText);
        String oldEducation=centerStudyText.getText().toString();
        if ( !oldEducation.equals(user.education)) {
            user.education = centerStudyText.getText().toString();
            changed = true;
        }
        TextView about_me_text = (TextView) findViewById(R.id.editable_about_me_text);
        String oldAboutMe=about_me_text.getText().toString();
        if ( !oldAboutMe.equals( user.aboutMe)) {
            user.aboutMe = about_me_text.getText().toString();
            changed = true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEditProfile);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getResources().getString(R.string.nav_item_edit_profile));
        }

        setUserProfile();

    }
    private void setUserProfile(){
        User user = SingletonUser.getUser();
        ImageView imageView1 = (ImageView) findViewById(R.id.profileImage1);
        Picasso.with(this).load(user.photoUrl).fit().centerCrop().into(imageView1);


        TextView proffesionText = (TextView) findViewById(R.id.editable_proffesionText);

        if(!user.work.isEmpty()) {
            proffesionText.setText(user.work);
        }else{
            proffesionText.setHint(getResources().getString(R.string.edit_profile_work_hint));
        }
        TextView centerStudyText = (TextView) findViewById(R.id.editable_centerStudyText);
        if(!user.education.isEmpty()) {
            centerStudyText.setText(user.education);
        }else{
            centerStudyText.setHint(getResources().getString(R.string.edit_profile_studies_hint));
        }

        TextView about_me_text = (TextView) findViewById(R.id.editable_about_me_text);
        if(!user.aboutMe.isEmpty()) {
            about_me_text.setText(user.aboutMe);
        }else{
            about_me_text.setHint(getResources().getString(R.string.edit_profile_about_me_hint));
        }

    }
    private void discardChanges(){
        checkForChange();
        if(changed) {
            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.AppThemeDialog);
            builder.setMessage(getResources().getString(R.string.edit_profile_discartChanges));
            builder.setCancelable(false);
            builder.setNegativeButton(getResources().getString(R.string.edit_profile_discartChanges_negative), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }

            });
            builder.setPositiveButton(getResources().getString(R.string.edit_profile_discartChanges_positive), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    onBackPressed();
                }

            });

            builder.show();
        }
    }
    @Override
    public void onBackPressed() {
        discardChanges();
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
            break;

        }
        return super.onOptionsItemSelected(item);
    }

}
