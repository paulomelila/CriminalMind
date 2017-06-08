package com.derrick.park.criminalmind;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String ID = "ID";

    public static Intent newIntent(Context context, UUID crimeID) {
        Intent intent = new Intent (context, CrimeActivity.class);
        intent.putExtra(ID, crimeID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

}
