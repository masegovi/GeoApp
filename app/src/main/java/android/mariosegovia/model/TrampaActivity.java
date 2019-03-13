package android.mariosegovia.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class TrampaActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TrampaFragment();
    }
}
