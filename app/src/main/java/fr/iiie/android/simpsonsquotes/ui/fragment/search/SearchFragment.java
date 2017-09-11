package fr.iiie.android.simpsonsquotes.ui.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.bus.SnackEvent;
import fr.iiie.android.simpsonsquotes.bus.SwitchFragmentEvent;
import fr.iiie.android.simpsonsquotes.business.search.SearchController;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.SearchDataReadyEvent;
import fr.iiie.android.simpsonsquotes.ui.fragment.random.RandomFragment;

public class SearchFragment extends Fragment
{
    @BindView(R.id.fragment_search_editText)
    EditText searchEditText;

    @BindView(R.id.fragment_search_progressBar)
    ProgressBar searchProgressBar;

    @BindView(R.id.fragment_search_recyclerView)
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    SearchDataAdapter searchDataAdapter;

    @OnClick(R.id.fragment_search_randomButton)
    public void onRandomButtonClick()
    {
        App.getAppBus().post(new SwitchFragmentEvent(new RandomFragment(), SwitchFragmentEvent.Direction.ALPHA, true, true, false));
    }

    private SearchController searchController;

    @OnEditorAction(R.id.fragment_search_editText)
    protected boolean onSearchEnter(int actionId)
    {
        if (actionId == KeyEvent.ACTION_DOWN)
        {
            searchProgressBar.setVisibility(View.VISIBLE);
            searchController.getSearchResponse(searchEditText.getText().toString());
            return true;
        }
        return false;
    }

    public SearchFragment()
    {
        //Mandatory constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);
        searchController = new SearchController();

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        searchDataAdapter = new SearchDataAdapter();
        recyclerView.setAdapter(searchDataAdapter);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        searchController.resume();
        if (!searchEditText.getText().toString().equals(""))
        {
            searchProgressBar.setVisibility(View.VISIBLE);
            searchController.getSearchResponse(searchEditText.getText().toString());
        }
        if (!App.getAppBus().isRegistered(this))
        {
            App.getAppBus().register(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchDataReadyEvent(SearchDataReadyEvent event)
    {
        if (event.getMyQuoteResultsListModel() != null)
        {
            searchDataAdapter.setList(event.getMyQuoteResultsListModel());
            searchDataAdapter.notifyDataSetChanged();
            searchProgressBar.setVisibility(View.GONE);
        }
        else
        {
            App.getAppBus().post(new SnackEvent("Error or no network"));
            searchProgressBar.setVisibility(View.GONE);
        }
    }
}
