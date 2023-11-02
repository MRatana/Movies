package kh.edu.rupp.ite.movies.help;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShowFragment {
    public static void show(Fragment fragment,FragmentManager fragmentManager,int container){
        // FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace fragment in ui
        fragmentTransaction.replace(container, fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }
}
