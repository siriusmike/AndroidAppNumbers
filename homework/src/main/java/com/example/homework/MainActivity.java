package com.example.homework;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.homework.fragments.ChildFragment;
import com.example.homework.fragments.MyInterface;
import com.example.homework.fragments.TopFragment;

public class MainActivity extends AppCompatActivity implements MyInterface {

    private final static String STATE = "STATE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout);
        if (savedInstanceState == null && getFragmentManager().findFragmentById(R.id.container) == null) {
                getSupportFragmentManager().
                        beginTransaction()
                        .add(R.id.container, new TopFragment())
                        .commit();
        }

    }


    @Override
    public void onOpenChildFragment(String string) {
        Fragment fragment = new ChildFragment();

            Bundle bundle = new Bundle();
            bundle.putString(ChildFragment.KEY, string);
            fragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("Fragment close")
                    .commit();

        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE, false);
    }


}

