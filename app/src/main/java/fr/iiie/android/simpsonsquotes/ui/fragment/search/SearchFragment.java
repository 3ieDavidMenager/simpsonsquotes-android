package fr.iiie.android.simpsonsquotes.ui.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.bus.SnackEvent;
import fr.iiie.android.simpsonsquotes.business.search.SearchController;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.SearchDataReadyEvent;
import fr.iiie.android.simpsonsquotes.data.model.QuoteResultModel;

public class SearchFragment extends Fragment
{
    // TODO 3ie naminng convention : meaningful name + suffix with classname -> searchEdittext
    @BindView(R.id.fragment_search_editText)
    EditText search;

    @BindView(R.id.fragment_search_tableLayout)
    TableLayout tableLayout;

    private SearchController searchController;

    @OnEditorAction(R.id.fragment_search_editText)
    protected boolean onSearchEnter(int actionId)
    {
        if (actionId == KeyEvent.ACTION_DOWN)
        {
            searchController.getSearchResponse(search.getText().toString());
            return true;
        }
        return false;
    }

    public SearchFragment()
    {
        // TODO When adding the "mandatory" empty constructor, do not leave it empty, put in a comment
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
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
            for (QuoteResultModel quote : event.getMyQuoteResultsListModel())
            {
                TableRow tableRow = new TableRow(getContext());
                tableRow.setLayoutParams(tableLayout.getLayoutParams());

                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        tableLayout.getWidth() / 4,
                        TableRow.LayoutParams.WRAP_CONTENT
                );

                TextView id_textView = new TextView(getContext());
                id_textView.setLayoutParams(params);
                id_textView.setGravity(Gravity.CENTER);
                String quote_id = Integer.toString(quote.getId());
                id_textView.setText(quote_id);

                TextView episode_textView = new TextView(getContext());
                episode_textView.setLayoutParams(params);
                episode_textView.setGravity(Gravity.CENTER);
                episode_textView.setText(quote.getEpisode());

                TextView timestamp_textView = new TextView(getContext());
                timestamp_textView.setLayoutParams(params);
                timestamp_textView.setGravity(Gravity.CENTER);
                String quote_timestamp = Integer.toString(quote.getTimestamp());
                timestamp_textView.setText(quote_timestamp);

                ImageView small_imageView = new ImageView(getContext());
                small_imageView.setLayoutParams(params);
                String image_url = "https://frinkiac.com/img/" + quote.getEpisode() + "/" + quote_timestamp + "/small.jpg";
                Glide.with(this).load(image_url).into(small_imageView);

                tableRow.addView(id_textView);
                tableRow.addView(episode_textView);
                tableRow.addView(timestamp_textView);
                tableRow.addView(small_imageView);

                tableLayout.addView(tableRow);
            }
        }
        else
        {
            App.getAppBus().post(new SnackEvent("Error or no network"));
        }
    }
}
