package fr.iiie.android.simpsonsquotes.ui.fragment.search;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.business.search.SearchController;
import fr.iiie.android.simpsonsquotes.data.app.App;

public class SearchFragment extends Fragment
{

    private SearchController searchController;

    public SearchFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        searchController = new SearchController();

        return rootView;
    }


    @Override
    public void onResume()
    {
        super.onResume();
        searchController.resume();
        if (!App.getAppBus().isRegistered(this))
        {
            //App.getAppBus().register(this);
        }
    }

    @Override
    public void onPause()
    {
        searchController.pause();
        if (App.getAppBus().isRegistered(this))
        {
            App.getAppBus().unregister(this);
        }
        super.onPause();
    }
}
